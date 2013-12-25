package jp.gr.java_conf.star_diopside.common.web.session;

import javax.servlet.http.HttpSession;

/**
 * セッションの永続化に必要な属性を追加したセッションラッパークラス
 */
public class StoredHttpSession extends HttpSessionWrapper {

    /** セッション属性変更時刻タイムスタンプ (volatile変数とする) */
    private volatile long modifiedTime;

    /**
     * コンストラクタ
     * 
     * @param session セッションオブジェクト
     */
    public StoredHttpSession(HttpSession session) {
        super(session);
        modifiedTime = System.currentTimeMillis();
    }

    /**
     * セッション属性の変更時刻のタイムスタンプを取得する。
     * 
     * @return セッション属性の変更時刻のタイムスタンプ
     */
    public final long getModifiedTime() {
        return modifiedTime;
    }

    @Override
    public Object getAttribute(String name) {
        Object attribute = super.getAttribute(name);
        if (isMutable(attribute)) {
            modifiedTime = System.currentTimeMillis();
        }
        return attribute;
    }

    @Override
    public void setAttribute(String name, Object value) {
        super.setAttribute(name, value);
        modifiedTime = System.currentTimeMillis();
    }

    @Override
    public void removeAttribute(String name) {
        super.removeAttribute(name);
        modifiedTime = System.currentTimeMillis();
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
