package jp.gr.java_conf.star_diopside.common.web.session;

import java.util.concurrent.ConcurrentHashMap;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

import org.springframework.web.context.support.WebApplicationContextUtils;

/**
 * セッション破棄時に{@link StoredHttpSessionDestroyedEvent}イベントを発行するリスナー
 */
public class SessionStoreListener implements ServletContextListener, HttpSessionListener {

    private static final String SESSION_MAP = SessionStoreListener.class.getName() + ".SESSION_MAP";

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        sce.getServletContext().setAttribute(SESSION_MAP, new ConcurrentHashMap<HttpSession, StoredHttpSession>());
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        sce.getServletContext().removeAttribute(SESSION_MAP);
    }

    @Override
    public void sessionCreated(HttpSessionEvent se) {
        HttpSession session = se.getSession();
        getSessionMap(session.getServletContext()).put(session, new StoredHttpSession(session));
    }

    @Override
    public void sessionDestroyed(HttpSessionEvent se) {
        HttpSession session = se.getSession();
        StoredHttpSession storedHttpSession = getSessionMap(session.getServletContext()).remove(session);
        StoredHttpSessionDestroyedEvent event = new StoredHttpSessionDestroyedEvent(storedHttpSession);
        WebApplicationContextUtils.getRequiredWebApplicationContext(session.getServletContext()).publishEvent(event);
    }

    @SuppressWarnings("unchecked")
    private static ConcurrentHashMap<HttpSession, StoredHttpSession> getSessionMap(ServletContext sc) {
        return (ConcurrentHashMap<HttpSession, StoredHttpSession>) sc.getAttribute(SESSION_MAP);
    }

    /**
     * セッションオブジェクトをラッピングした{@link StoredHttpSession}オブジェクトを取得する。
     * 
     * @param session セッションオブジェクト
     * @return セッションオブジェクトをラッピングした{@link StoredHttpSession}オブジェクト
     */
    public static StoredHttpSession getStoredHttpSession(HttpSession session) {
        return getSessionMap(session.getServletContext()).get(session);
    }
}
