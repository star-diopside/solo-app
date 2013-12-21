package jp.gr.java_conf.star_diopside.solo.web.servlet;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jp.gr.java_conf.star_diopside.solo.service.exception.DualLoginException;
import jp.gr.java_conf.star_diopside.solo.service.logic.auth.UserManager;
import jp.gr.java_conf.star_diopside.solo.service.userdetails.LoginUserDetails;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.WebAttributes;
import org.springframework.web.filter.OncePerRequestFilter;
import org.springframework.web.servlet.DispatcherServlet;
import org.springframework.web.servlet.FlashMap;
import org.springframework.web.servlet.FlashMapManager;

/**
 * 二重ログインチェックを行うサーブレットフィルタ
 */
public class DualLoginCheckFilter extends OncePerRequestFilter {

    @Autowired
    private UserManager userManager;

    @Autowired
    @Qualifier(DispatcherServlet.FLASH_MAP_MANAGER_BEAN_NAME)
    private FlashMapManager flashMapManager;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {

        // 認証情報を取得する。
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        // 認証が行われている場合、二重ログインチェックを行う。
        if (principal instanceof LoginUserDetails) {

            // ログイン情報が更新されている場合
            if (userManager.checkDualLogin((LoginUserDetails) principal)) {

                // 二重ログイン例外をフラッシュスコープに設定する。
                DualLoginException exception = new DualLoginException();

                FlashMap flashMap = new FlashMap();
                flashMap.put(WebAttributes.AUTHENTICATION_EXCEPTION, exception);
                flashMapManager.saveOutputFlashMap(flashMap, request, response);

                // 二重ログイン例外をスローする。
                throw exception;
            }
        }

        filterChain.doFilter(request, response);
    }
}
