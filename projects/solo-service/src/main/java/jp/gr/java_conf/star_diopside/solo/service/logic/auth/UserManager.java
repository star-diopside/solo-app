package jp.gr.java_conf.star_diopside.solo.service.logic.auth;

import java.sql.Timestamp;

import jp.gr.java_conf.star_diopside.solo.core.exception.BusinessException;
import jp.gr.java_conf.star_diopside.solo.data.entity.Authority;
import jp.gr.java_conf.star_diopside.solo.data.entity.User;
import jp.gr.java_conf.star_diopside.solo.data.repository.AuthorityRepository;
import jp.gr.java_conf.star_diopside.solo.data.repository.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
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
        user.setVersion(0);

        userRepository.save(user);

        // 権限情報の登録を行う。
        Authority authority = new Authority();

        authority.setUserId(userId);
        authority.setAuthority("ROLE_USER");
        authority.setRegisterTimestamp(current);
        authority.setRegisterUserId(userId);
        authority.setUpdatedTimestamp(current);
        authority.setUpdatedUserId(userId);
        authority.setVersion(0);

        authorityRepository.save(authority);
    }
}
