package jp.gr.java_conf.star_diopside.common.web.session;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import javax.servlet.http.HttpSession;

/**
 * セッションオブジェクトを{@link StoredHttpSession}にラッピングするサーブレットリクエスト
 */
public class SessionStoreHttpServletRequest extends HttpServletRequestWrapper {

    /**
     * コンストラクタ
     * 
     * @param request サーブレットリクエスト
     */
    public SessionStoreHttpServletRequest(HttpServletRequest request) {
        super(request);
    }

    @Override
    public HttpSession getSession(boolean create) {
        HttpSession session = super.getSession(create);
        return session == null ? null : getSessionInternal(session);
    }

    @Override
    public HttpSession getSession() {
        HttpSession session = super.getSession();
        return getSessionInternal(session);
    }

    private StoredHttpSession getSessionInternal(HttpSession session) {
        return SessionStoreListener.getStoredHttpSession(session);
    }
}
