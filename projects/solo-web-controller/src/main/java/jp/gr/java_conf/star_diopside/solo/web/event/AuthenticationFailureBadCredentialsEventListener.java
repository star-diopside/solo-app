package jp.gr.java_conf.star_diopside.solo.web.event;

import jp.gr.java_conf.star_diopside.solo.service.logic.auth.UserManager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.security.authentication.event.AuthenticationFailureBadCredentialsEvent;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Controller;

/**
 * ログイン認証エラー時の処理を行うイベントリスナー
 */
@Controller
public class AuthenticationFailureBadCredentialsEventListener implements
        ApplicationListener<AuthenticationFailureBadCredentialsEvent> {

    @Autowired
    private UserManager userManager;

    @Override
    public void onApplicationEvent(AuthenticationFailureBadCredentialsEvent event) {

        // ユーザが存在しない場合、何も処理しない。
        if (event.getException() instanceof UsernameNotFoundException) {
            return;
        }

        // 認証エラー処理を行う。
        String userId = event.getAuthentication().getName();
        this.userManager.loginFailure(userId);
    }
}
