package jp.gr.java_conf.star_diopside.common.web.session;

import java.util.Map;
import java.util.WeakHashMap;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

/**
 * セッション破棄時に{@link StoredHttpSessionDestroyedEvent}イベントを発行するリスナー
 */
public class SessionStoreListener implements ServletContextListener, HttpSessionListener {

    static final String SESSION_MAP = SessionStoreListener.class.getName() + ".SESSION_MAP";

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        sce.getServletContext().setAttribute(SESSION_MAP, new WeakHashMap<HttpSession, StoredHttpSession>());
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
        getApplicationContext(session.getServletContext()).publishEvent(event);
    }

    private ApplicationContext getApplicationContext(ServletContext sc) {
        return WebApplicationContextUtils.getRequiredWebApplicationContext(sc);
    }

    @SuppressWarnings("unchecked")
    private Map<HttpSession, StoredHttpSession> getSessionMap(ServletContext sc) {
        return (Map<HttpSession, StoredHttpSession>) sc.getAttribute(SESSION_MAP);
    }
}
