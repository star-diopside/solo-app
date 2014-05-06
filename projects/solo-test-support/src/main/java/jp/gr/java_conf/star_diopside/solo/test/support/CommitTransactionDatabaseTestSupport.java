package jp.gr.java_conf.star_diopside.solo.test.support;

import java.sql.SQLException;

import javax.sql.DataSource;

import jp.gr.java_conf.star_diopside.solo.test.exception.TestException;

import org.dbunit.DatabaseUnitException;
import org.dbunit.database.IDatabaseConnection;
import org.dbunit.dataset.CachedDataSet;
import org.dbunit.dataset.IDataSet;
import org.dbunit.operation.DatabaseOperation;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.DefaultTransactionDefinition;
import org.springframework.transaction.support.TransactionCallbackWithoutResult;
import org.springframework.transaction.support.TransactionTemplate;

/**
 * テストデータのセットアップ時にコミットを行うテストサポートクラス
 */
public class CommitTransactionDatabaseTestSupport extends AbstractDatabaseTestSupport {

    /** トランザクションテンプレート */
    private TransactionTemplate transactionTemplate;

    /** バックアップデータセット */
    private IDataSet backupDataSet;

    /**
     * コンストラクタ
     * 
     * @param tester テストクラスのインスタンス
     * @param dataSource データソース
     * @param transactionManager トランザクションマネージャ
     */
    public CommitTransactionDatabaseTestSupport(Object tester, DataSource dataSource,
            PlatformTransactionManager transactionManager) {
        super(tester, dataSource);
        this.transactionTemplate = new TransactionTemplate(transactionManager, new DefaultTransactionDefinition(
                TransactionDefinition.PROPAGATION_REQUIRES_NEW));
    }

    @Override
    public void onSetup() {
        transactionTemplate.execute(new TransactionCallbackWithoutResult() {
            @Override
            protected void doInTransactionWithoutResult(TransactionStatus status) {
                try {
                    IDatabaseConnection connection = getConnection();
                    IDataSet dataSet = getDataSet();
                    backupDataSet = new CachedDataSet(connection.createDataSet(dataSet.getTableNames()));
                    DatabaseOperation.CLEAN_INSERT.execute(connection, dataSet);
                } catch (DatabaseUnitException | SQLException e) {
                    throw new TestException(e);
                }
            }
        });
    }

    @Override
    public void onTearDown() {
        transactionTemplate.execute(new TransactionCallbackWithoutResult() {
            @Override
            protected void doInTransactionWithoutResult(TransactionStatus status) {
                try {
                    IDatabaseConnection connection = getConnection();
                    DatabaseOperation.CLEAN_INSERT.execute(connection, backupDataSet);
                } catch (DatabaseUnitException | SQLException e) {
                    throw new TestException(e);
                }
            }
        });
    }
}
