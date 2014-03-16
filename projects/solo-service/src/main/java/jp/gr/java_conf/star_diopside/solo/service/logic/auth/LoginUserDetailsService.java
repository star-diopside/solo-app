package jp.gr.java_conf.star_diopside.solo.service.logic.auth;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import jp.gr.java_conf.star_diopside.solo.data.entity.User;
import jp.gr.java_conf.star_diopside.solo.data.repository.AuthorityRepository;
import jp.gr.java_conf.star_diopside.solo.data.repository.UserRepository;
import jp.gr.java_conf.star_diopside.solo.service.userdetails.LoginUser;
import jp.gr.java_conf.star_diopside.solo.service.userdetails.LoginUserDetails;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.jdbc.JdbcDaoImpl;

/**
 * ログインユーザ情報サービスクラス
 */
public class LoginUserDetailsService extends JdbcDaoImpl {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private AuthorityRepository authorityRepository;

    @Override
    protected List<UserDetails> loadUsersByUsername(String username) {

        User user = userRepository.findOne(username);
        ArrayList<UserDetails> userDetails = new ArrayList<>();

        if (user != null) {
            userDetails.add(new LoginUser(user));
        }

        return userDetails;
    }

    @Override
    protected List<GrantedAuthority> loadUserAuthorities(String username) {
        return authorityRepository.findByUserId(username).stream()
                .map(authority -> new SimpleGrantedAuthority(getRolePrefix() + authority.getAuthority()))
                .collect(Collectors.toList());
    }

    @Override
    protected UserDetails createUserDetails(String username, UserDetails userFromUserQuery,
            List<GrantedAuthority> combinedAuthorities) {

        LoginUserDetails loginUser = (LoginUserDetails) userFromUserQuery;
        UserDetails returnUserDetails = super.createUserDetails(username, userFromUserQuery, combinedAuthorities);

        return new LoginUser(returnUserDetails, loginUser);
    }
}
