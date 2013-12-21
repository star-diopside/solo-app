package jp.gr.java_conf.star_diopside.solo.web.event;

import jp.gr.java_conf.star_diopside.solo.data.repository.UserRepository;
import jp.gr.java_conf.star_diopside.solo.service.logic.auth.UserManager;
import jp.gr.java_conf.star_diopside.solo.service.userdetails.LoginUserDetails;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.security.authentication.event.AuthenticationSuccessEvent;
import org.springframework.stereotype.Controller;

/**
 * ログイン成功時の処理を行うイベントリスナー
 */
@Controller
public class AuthenticationSuccessEventListener implements ApplicationListener<AuthenticationSuccessEvent> {

    @Autowired
    private UserManager userManager;

    @Autowired
    private UserRepository userRepository;

    @Override
    public void onApplicationEvent(AuthenticationSuccessEvent event) {

        // ログイン成功時の処理を行う。
        LoginUserDetails user = (LoginUserDetails) event.getAuthentication().getPrincipal();
        this.userManager.loginSuccess(user);

        // ログイン情報を更新する。
        user.update(user, userRepository.findOne(user.getUserId()));
    }
}
