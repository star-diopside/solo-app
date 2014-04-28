package jp.gr.java_conf.star_diopside.solo.service.logic.auth;

import java.time.ZonedDateTime;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import jp.gr.java_conf.star_diopside.solo.core.exception.BusinessException;
import jp.gr.java_conf.star_diopside.solo.data.entity.Authority;
import jp.gr.java_conf.star_diopside.solo.data.entity.User;
import jp.gr.java_conf.star_diopside.solo.data.repository.AuthorityRepository;
import jp.gr.java_conf.star_diopside.solo.data.repository.UserRepository;
import jp.gr.java_conf.star_diopside.solo.data.repository.UserScheduleRepository;
import jp.gr.java_conf.star_diopside.solo.service.userdetails.LoginUserDetails;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * ユーザ管理クラス
 */
@Service
public class UserManagerImpl implements UserManager {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private AuthorityRepository authorityRepository;

    @Autowired
    private UserScheduleRepository userScheduleRepository;

    @Autowired
    @Qualifier("passwordEncoder")
    private PasswordEncoder passwordEncoder;

    @Override
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
        user.setPasswordUpdatedAt(current);
        user.setEnabled(true);
        user.setInterimRegister(true);
        user.setLoginErrorCount(0);
        user.setLockoutAt(null);
        user.setLastLoginAt(null);
        user.setLogoutAt(null);
        user.setCreatedAt(current);
        user.setCreatedUserId(userId);
        user.setUpdatedAt(current);
        user.setUpdatedUserId(userId);

        userRepository.save(user);

        // 権限情報の登録を行う。
        Authority authority = new Authority();

        authority.setUserId(userId);
        authority.setAuthority("ROLE_USER");
        authority.setCreatedAt(current);
        authority.setCreatedUserId(userId);
        authority.setUpdatedAt(current);
        authority.setUpdatedUserId(userId);

        authorityRepository.save(authority);
    }

    @Override
    public boolean checkValid(User user) {

        // ユーザの有効チェックを行う
        if (Boolean.FALSE.equals(user.getInterimRegister())) {
            // 本登録済みの場合、有効ユーザとする。
            return true;
        } else {
            // 仮登録中の場合、登録後１日経過すると無効ユーザとする。
            long duration = System.currentTimeMillis() - user.getCreatedAt().getTime();
            return duration <= TimeUnit.DAYS.toMillis(1);
        }
    }

    @Override
    @Transactional
    public void removeUser(User user) {

        user.getUserSchedules().forEach(userSchedule -> userScheduleRepository.delete(userSchedule));
        user.getAuthorities().forEach(authority -> authorityRepository.delete(authority));
        userRepository.delete(user);
    }

    @Override
    @Transactional
    public boolean removeInvalidUser(LoginUserDetails loginUser) {

        // ユーザ情報を取得する。
        User user = loginUser.convertUserEntity();

        // 無効ユーザの場合、削除を行う
        if (!checkValid(user)) {
            removeUser(user);
            return true;
        }

        return false;
    }

    @Override
    @Transactional
    public void loginSuccess(LoginUserDetails loginUser) {

        User user = loginUser.convertUserEntity();
        Date current = new Date();

        user.setLoginErrorCount(0);
        user.setLastLoginAt(current);
        user.setLogoutAt(null);
        user.setUpdatedAt(current);
        user.setUpdatedUserId(user.getUserId());

        userRepository.save(user);
    }

    @Override
    @Transactional
    public void loginFailure(String userId) {

        User user = userRepository.findOne(userId);
        Date current = new Date();

        user.setLoginErrorCount(user.getLoginErrorCount() + 1);
        user.setUpdatedAt(current);
        user.setUpdatedUserId(userId);

        userRepository.save(user);
    }

    @Override
    @Transactional
    public void logout(LoginUserDetails loginUser) {

        String userId = loginUser.getUserId();
        User user = userRepository.findOne(userId);

        // ログイン情報が更新されていない場合、ログアウト処理を行う。
        if (!checkLoginInfo(loginUser, user)) {
            Date current = new Date();
            user.setLogoutAt(current);
            user.setUpdatedAt(current);
            user.setUpdatedUserId(userId);
            userRepository.save(user);
        }
    }

    @Override
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
        return !equalsDateTime(loginUser.getLastLoginAt(), user.getLastLoginAt())
                || !equalsDateTime(loginUser.getLogoutAt(), user.getLogoutAt());
    }

    private static boolean equalsDateTime(ZonedDateTime dateTime, Date date) {

        if (dateTime == null) {
            return date == null;
        } else if (date == null) {
            return false;
        } else {
            return dateTime.toInstant().equals(date.toInstant());
        }
    }
}
