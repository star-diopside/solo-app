package jp.gr.java_conf.star_diopside.common.web.session;

import javax.servlet.http.HttpSession;

/**
 * セッションの永続化に必要な属性を追加したセッションラッパークラス
 */
public class StoredHttpSession extends HttpSessionWrapper {

    private static final String MODIFIED_TIME_KEY = StoredHttpSession.class.getName() + ".modifiedTime";

    /**
     * コンストラクタ
     * 
     * @param session セッションオブジェクト
     */
    public StoredHttpSession(HttpSession session) {
        super(session);
        synchronized (session) {
            if (getModifiedTime() == null) {
                updateModifiedTime();
            }
        }
    }

    /**
     * セッション属性の変更時刻のタイムスタンプを取得する。
     * 
     * @return セッション属性の変更時刻のタイムスタンプ
     */
    public final Long getModifiedTime() {
        return (Long) getSession().getAttribute(MODIFIED_TIME_KEY);
    }

    /**
     * セッション属性の変更時刻のタイムスタンプを更新する。
     */
    protected final void updateModifiedTime() {
        getSession().setAttribute(MODIFIED_TIME_KEY, System.currentTimeMillis());
    }

    @Override
    public Object getAttribute(String name) {
        Object attribute = super.getAttribute(name);
        if (isMutable(attribute)) {
            updateModifiedTime();
        }
        return attribute;
    }

    @Override
    public void setAttribute(String name, Object value) {
        super.setAttribute(name, value);
        updateModifiedTime();
    }

    @Override
    public void removeAttribute(String name) {
        super.removeAttribute(name);
        updateModifiedTime();
    }

    private boolean isMutable(Object obj) {
        if (obj == null || obj instanceof Boolean || obj instanceof Character || obj instanceof Byte
                || obj instanceof Short || obj instanceof Integer || obj instanceof Long || obj instanceof Float
                || obj instanceof Double || obj instanceof String) {
            return false;
        } else {
            return true;
        }
    }
}
