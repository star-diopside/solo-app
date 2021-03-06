package jp.gr.java_conf.star_diopside.common.web.session.servlet;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import jp.gr.java_conf.star_diopside.common.web.session.service.SessionStoreService;

import org.springframework.web.filter.OncePerRequestFilter;

/**
 * セッション情報の永続化を行うサーブレットフィルタ
 */
public class SessionStoreFilter extends OncePerRequestFilter {

    private SessionStoreService sessionStoreService;

    public void setSessionStoreService(SessionStoreService sessionStoreService) {
        this.sessionStoreService = sessionStoreService;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {

        SessionStoreHttpServletRequest req = new SessionStoreHttpServletRequest(request);
        long beforeModifiedTime = 0;

        try {
            sessionStoreService.readSession(req);
            beforeModifiedTime = getSessionModifiedTime(req);

            filterChain.doFilter(req, response);

        } finally {
            long afterModifiedTime = getSessionModifiedTime(req);
            if (beforeModifiedTime != afterModifiedTime) {
                sessionStoreService.storeSession(req);
            }
        }
    }

    private static long getSessionModifiedTime(SessionStoreHttpServletRequest request) {

        HttpSession session = request.getSession(false);
        long modifiedTime = 0;

        if (session != null) {
            modifiedTime = ((StoredHttpSession) session).getModifiedTime();
        }

        return modifiedTime;
    }
}
