package jp.gr.java_conf.star_diopside.solo.service.logic.auth;

import java.util.Date;
import java.util.Objects;
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
        Date current = new Date();

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
            throw new AccountExpiredException(messages.getMessage("Error.UserInvalid"));
        }
    }

    /**
     * ログイン成功時の処理を行う。
     * 
     * @param loginUser ユーザ情報
     */
    @Transactional
    public void loginSuccess(LoginUserDetails loginUser) {

        User user = loginUser.convertUserEntity();
        Date current = new Date();

        user.setLoginErrorCount(0);
        user.setLastLoginTimestamp(current);
        user.setLogoutTimestamp(null);
        user.setUpdatedTimestamp(current);
        user.setUpdatedUserId(user.getUserId());

        userRepository.save(user);
    }

    /**
     * ログイン失敗時の処理を行う。
     * 
     * @param userId ユーザID
     */
    @Transactional
    public void loginFailure(String userId) {

        User user = userRepository.findOne(userId);
        Date current = new Date();

        user.setLoginErrorCount(user.getLoginErrorCount() + 1);
        user.setUpdatedTimestamp(current);
        user.setUpdatedUserId(userId);

        userRepository.save(user);
    }

    /**
     * ログアウト処理を行う。
     * 
     * @param loginUser ユーザ情報
     */
    @Transactional
    public void logout(LoginUserDetails loginUser) {

        String userId = loginUser.getUserId();
        User user = userRepository.findOne(userId);

        // ログイン情報が更新されていない場合、ログアウト処理を行う。
        if (!checkLoginInfo(loginUser, user)) {
            Date current = new Date();
            user.setLogoutTimestamp(current);
            user.setUpdatedTimestamp(current);
            user.setUpdatedUserId(userId);
            userRepository.save(user);
        }
    }

    /**
     * 二重ログインチェックを行う。
     * 
     * @param loginUser ユーザ情報
     * @return 二重ログインエラーの場合はtrue、それ以外の場合はfalse。
     */
    @Transactional
    public boolean checkDualLogin(LoginUserDetails loginUser) {

        User user = userRepository.findOne(loginUser.getUserId());
        return checkLoginInfo(loginUser, user);
    }

    /**
     * ログイン情報の不変チェックを行う。
     * 
     * @param loginUser ログインユーザ情報
     * @param user ユーザテーブルから取得したユーザ情報
     * @return ログイン後にログイン情報が更新されている場合はtrue、それ以外の場合はfalse。
     */
    private boolean checkLoginInfo(LoginUserDetails loginUser, User user) {

        // 最終ログイン日時、ログアウト日時の判定を行う。
        return !Objects.equals(loginUser.getLastLoginTimestamp(), user.getLastLoginTimestamp())
                || !Objects.equals(loginUser.getLogoutTimestamp(), user.getLogoutTimestamp());
    }
}
