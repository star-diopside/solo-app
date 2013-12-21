package jp.gr.java_conf.star_diopside.solo.web.event;

import jp.gr.java_conf.star_diopside.solo.service.logic.auth.UserManager;
import jp.gr.java_conf.star_diopside.solo.service.userdetails.LoginUserDetails;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.web.session.HttpSessionDestroyedEvent;
import org.springframework.stereotype.Controller;

/**
 * セッション破棄時の処理を行うイベントリスナー
 */
@Controller
public class HttpSessionDestroyedEventListener implements ApplicationListener<HttpSessionDestroyedEvent> {

    @Autowired
    private UserManager userManager;

    @Override
    public void onApplicationEvent(HttpSessionDestroyedEvent event) {

        for (SecurityContext context : event.getSecurityContexts()) {

            // セキュリティコンテキストから認証情報を取得する。
            Object principal = context.getAuthentication().getPrincipal();

            // 認証情報をもとにログアウト処理を行う。
            if (principal instanceof LoginUserDetails) {
                userManager.logout((LoginUserDetails) principal);
            }
        }
    }
}
