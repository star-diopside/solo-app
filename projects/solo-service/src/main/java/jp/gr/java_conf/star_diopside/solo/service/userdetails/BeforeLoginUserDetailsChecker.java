package jp.gr.java_conf.star_diopside.solo.service.userdetails;

import jp.gr.java_conf.star_diopside.solo.service.logic.auth.UserManager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsChecker;

/**
 * ログイン前ユーザ情報チェック処理クラス
 */
public class BeforeLoginUserDetailsChecker implements UserDetailsChecker {

    @Autowired
    private UserManager userManager;

    @Override
    public void check(UserDetails toCheck) {
        userManager.checkValid((LoginUserDetails) toCheck);
    }
}
