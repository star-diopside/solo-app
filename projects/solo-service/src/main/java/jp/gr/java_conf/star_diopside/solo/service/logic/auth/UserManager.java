package jp.gr.java_conf.star_diopside.solo.service.logic.auth;

import java.sql.Timestamp;
import java.util.concurrent.TimeUnit;

import jp.gr.java_conf.star_diopside.solo.core.exception.BusinessException;
import jp.gr.java_conf.star_diopside.solo.data.entity.Authority;
import jp.gr.java_conf.star_diopside.solo.data.entity.User;
import jp.gr.java_conf.star_diopside.solo.data.repository.AuthorityRepository;
import jp.gr.java_conf.star_diopside.solo.data.repository.UserRepository;
import jp.gr.java_conf.star_diopside.solo.service.userdetails.LoginUserDetails;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.support.MessageSourceAccessor;
import org.springframework.security.authentication.AccountExpiredException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * ユーザ管理クラス
 */
@Service
public class UserManager {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private AuthorityRepository authorityRepository;

    @Autowired
    @Qualifier("messages")
    private MessageSourceAccessor messages;

    @Autowired
    @Qualifier("passwordEncoder")
    private PasswordEncoder passwordEncoder;

    /**
     * ユーザを作成する。
     * 
     * @param userId ユーザID
     * @param username ユーザ名
     * @param password パスワード
     */
    @Transactional
    public void createUser(String userId, String username, String password) {

        // ユーザの存在チェックを行う。
        if (userRepository.exists(userId)) {
            throw new BusinessException("Error.UserExists", true);
        }

        // 現在時刻を取得する。
        Timestamp current = new Timestamp(System.currentTimeMillis());

        // ユーザ情報の登録を行う。
        User user = new User();

        user.setUserId(userId);
        user.setUsername(username);
        user.setPassword(passwordEncoder.encode(password));
        user.setPasswordUpdatedTimestamp(current);
        user.setEnabled(true);
        user.setInterimRegister(true);
        user.setLoginErrorCount(0);
        user.setLockoutTimestamp(null);
        user.setLastLoginTimestamp(null);
        user.setLogoutTimestamp(null);
        user.setRegisterTimestamp(current);
        user.setRegisterUserId(userId);
        user.setUpdatedTimestamp(current);
        user.setUpdatedUserId(userId);

        userRepository.save(user);

        // 権限情報の登録を行う。
        Authority authority = new Authority();

        authority.setUserId(userId);
        authority.setAuthority("ROLE_USER");
        authority.setRegisterTimestamp(current);
        authority.setRegisterUserId(userId);
        authority.setUpdatedTimestamp(current);
        authority.setUpdatedUserId(userId);

        authorityRepository.save(authority);
    }

    /**
     * 取得したユーザ情報が有効かどうか判定する。
     * 
     * @param loginUser ユーザ情報
     * @throws AuthenticationException 無効なユーザ情報と判定した場合
     */
    @Transactional(noRollbackFor = AuthenticationException.class)
    public void checkValid(LoginUserDetails loginUser) throws AuthenticationException {

        // ユーザ情報を取得する。
        User user = loginUser.convertUserEntity();

        // ユーザの有効チェックを行う
        boolean valid;

        if (Boolean.FALSE.equals(user.getInterimRegister())) {
            // 本登録済みの場合、有効ユーザとする。
            valid = true;
        } else {
            // 仮登録中の場合、登録後１日経過すると無効ユーザとする。
            long duration = System.currentTimeMillis() - user.getRegisterTimestamp().getTime();
            valid = (duration <= TimeUnit.DAYS.toMillis(1));
        }

        // 無効ユーザの削除を行う
        if (!valid) {
            authorityRepository.deleteByUserId(user.getUserId());
            userRepository.delete(user);
            throw new AccountExpiredException(this.messages.getMessage("Error.UserInvalid"));
        }
    }
}
