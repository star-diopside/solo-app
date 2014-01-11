package jp.gr.java_conf.star_diopside.common.web.session;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.jdbc.core.support.SqlLobValue;
import org.springframework.transaction.annotation.Transactional;

/**
 * セッション永続化にデータソースを使用する{@link SessionStoreService}クラス
 */
public class JdbcSessionStore extends JdbcDaoSupport implements SessionStoreService {

    private static final Log LOG = LogFactory.getLog(JdbcSessionStore.class);

    private String sessionTableName = "sessions";
    private String sessionIdColumnName = "id";
    private String sessionDataColumnName = "data";
    private String sessionTimestampColumnName = "modified_time";
    private String selectLockSqlOption = "for update";

    private String countSessionSql;
    private String selectSessionDataSql;
    private String selectSessionTimestampSql;
    private String insertSessionSql;
    private String updateSessionSql;
    private String deleteSessionSql;

    public String getSessionTableName() {
        return sessionTableName;
    }

    public String getSessionIdColumnName() {
        return sessionIdColumnName;
    }

    public String getSessionDataColumnName() {
        return sessionDataColumnName;
    }

    public String getSessionTimestampColumnName() {
        return sessionTimestampColumnName;
    }

    public void setSelectLockSqlOption(String selectLockSqlOption) {
        this.selectLockSqlOption = selectLockSqlOption;
    }

    @Override
    protected void initDao() throws Exception {
        super.initDao();
        countSessionSql = "select count(" + sessionIdColumnName + ") from " + sessionTableName + " where "
                + sessionIdColumnName + " = ?";
        selectSessionDataSql = "select " + sessionDataColumnName + " from " + sessionTableName + " where "
                + sessionIdColumnName + " = ?";
        selectSessionTimestampSql = "select " + sessionTimestampColumnName + " from " + sessionTableName + " where "
                + sessionIdColumnName + " = ? " + selectLockSqlOption;
        insertSessionSql = "insert into " + sessionTableName + " (" + sessionIdColumnName + ", "
                + sessionDataColumnName + ", " + sessionTimestampColumnName + ") values (?, ?, ?)";
        updateSessionSql = "update " + sessionTableName + " set " + sessionDataColumnName + " = ?, "
                + sessionTimestampColumnName + " = ? where " + sessionIdColumnName + " = ?";
        deleteSessionSql = "delete from " + sessionTableName + " where " + sessionIdColumnName + " = ?";
    }

    @Override
    @Transactional
    public void readSession(SessionStoreHttpServletRequest request) {

        String requestedSessionId = request.getRequestedSessionId();

        if (requestedSessionId == null) {
            return;
        }

        try {
            // データベースに保存されたセッション最終アクセス時刻を取得する。
            long timestamp = getJdbcTemplate().queryForObject(selectSessionTimestampSql,
                    new Object[] { requestedSessionId }, new int[] { Types.VARCHAR }, new RowMapper<Long>() {
                        @Override
                        public Long mapRow(ResultSet rs, int rowNum) throws SQLException {
                            return rs.getLong(1);
                        }
                    });

            StoredHttpSession session = (StoredHttpSession) request.getSession();

            if (session.getSerializedModifiedTime() != timestamp) {
                // データベースからセッション情報を取得する。
                byte[] data = getJdbcTemplate().queryForObject(selectSessionDataSql,
                        new Object[] { requestedSessionId }, new int[] { Types.VARCHAR }, new RowMapper<byte[]>() {
                            @Override
                            public byte[] mapRow(ResultSet rs, int rowNum) throws SQLException {
                                return rs.getBytes(1);
                            }
                        });

                // セッションをデシリアライズする。
                session.deserialize(data);
            }

            // データベースからセッション情報を削除する。
            if (!request.isRequestedSessionIdValid()) {
                getJdbcTemplate().update(deleteSessionSql, new Object[] { requestedSessionId },
                        new int[] { Types.VARCHAR });
            }

        } catch (EmptyResultDataAccessException e) {
            LOG.debug("Not found session for id = " + requestedSessionId, e);
        }
    }

    @Override
    @Transactional
    public void storeSession(SessionStoreHttpServletRequest request) {

        StoredHttpSession session = (StoredHttpSession) request.getSession(false);

        if (session == null) {
            return;
        }

        // セッションをシリアライズする。
        byte[] data = session.serialize();

        // データベースにセッション情報を登録する。
        String sessionId = session.getId();
        Integer count = getJdbcTemplate().queryForObject(countSessionSql, new Object[] { sessionId },
                new int[] { Types.VARCHAR }, Integer.class);

        if (count.intValue() == 0) {
            getJdbcTemplate().update(insertSessionSql,
                    new Object[] { sessionId, new SqlLobValue(data), session.getModifiedTime() },
                    new int[] { Types.VARCHAR, Types.BLOB, Types.BIGINT });
        } else {
            getJdbcTemplate().update(updateSessionSql,
                    new Object[] { new SqlLobValue(data), session.getModifiedTime(), sessionId },
                    new int[] { Types.BLOB, Types.BIGINT, Types.VARCHAR });
        }
    }

    @Override
    @Transactional
    public void removeSession(StoredHttpSession session) {

        String sessionId = session.getId();

        // データベースに保存されたセッション最終アクセス時刻を取得する。
        try {
            long timestamp = getJdbcTemplate().queryForObject(selectSessionTimestampSql, new Object[] { sessionId },
                    new int[] { Types.VARCHAR }, new RowMapper<Long>() {
                        @Override
                        public Long mapRow(ResultSet rs, int rowNum) throws SQLException {
                            return rs.getLong(1);
                        }
                    });

            // データベースからセッション情報を削除する。
            if (session.getSerializedModifiedTime() == timestamp) {
                getJdbcTemplate().update(deleteSessionSql, new Object[] { sessionId }, new int[] { Types.VARCHAR });
            }

        } catch (EmptyResultDataAccessException e) {
            LOG.debug("Not found session for id = " + sessionId, e);
        }
    }
}
