package jp.gr.java_conf.star_diopside.solo.service.logic;

import java.util.Arrays;

import jp.gr.java_conf.star_diopside.solo.core.exception.BusinessException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserService {

    @Autowired
    private UserDetailsManager userDetailsManager;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Transactional
    public void createUser(String username, String nickname, String password) {

        if (userDetailsManager.userExists(username)) {
            throw new BusinessException("Error.UserExists", true);
        }

        User user = new User(username, passwordEncoder.encode(password),
                Arrays.asList(new SimpleGrantedAuthority("ROLE_USER")));
        userDetailsManager.createUser(user);
    }
}
