package jp.gr.java_conf.star_diopside.common.web.session;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

/**
 * セッション破棄時に{@link StoredHttpSessionDestroyedEvent}イベントを発行するリスナー
 */
public class SessionStoreListener implements HttpSessionListener {

    @Override
    public void sessionCreated(HttpSessionEvent se) {
    }

    @Override
    public void sessionDestroyed(HttpSessionEvent se) {
        StoredHttpSession session = new StoredHttpSession(se.getSession());
        if (session.getModifiedTime() != null) {
            StoredHttpSessionDestroyedEvent event = new StoredHttpSessionDestroyedEvent(session);
            getApplicationContext(session.getServletContext()).publishEvent(event);
        }
    }

    private ApplicationContext getApplicationContext(ServletContext sc) {
        return WebApplicationContextUtils.getRequiredWebApplicationContext(sc);
    }
}
