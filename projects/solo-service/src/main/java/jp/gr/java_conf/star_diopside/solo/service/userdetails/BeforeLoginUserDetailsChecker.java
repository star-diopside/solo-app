package jp.gr.java_conf.star_diopside.solo.service.userdetails;

import jp.gr.java_conf.star_diopside.solo.service.logic.auth.UserManager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.support.MessageSourceAccessor;
import org.springframework.security.authentication.AccountExpiredException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsChecker;

/**
 * ログイン前ユーザ情報チェック処理クラス
 */
public class BeforeLoginUserDetailsChecker implements UserDetailsChecker {

    @Autowired
    private UserManager userManager;

    @Autowired
    @Qualifier("messages")
    private MessageSourceAccessor messages;

    @Override
    public void check(UserDetails toCheck) {
        if (userManager.removeInvalidUser((LoginUserDetails) toCheck)) {
            throw new AccountExpiredException(messages.getMessage("Error.UserInvalid"));
        }
    }
}
