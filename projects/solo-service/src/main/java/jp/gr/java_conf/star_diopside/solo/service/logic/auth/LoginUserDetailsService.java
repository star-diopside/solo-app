package jp.gr.java_conf.star_diopside.solo.service.logic.auth;

import java.util.ArrayList;
import java.util.List;

import jp.gr.java_conf.star_diopside.solo.data.entity.Authority;
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
        ArrayList<UserDetails> userDetails = new ArrayList<UserDetails>();

        if (user != null) {
            userDetails.add(new LoginUser(user));
        }

        return userDetails;
    }

    @Override
    protected List<GrantedAuthority> loadUserAuthorities(String username) {

        List<Authority> authorities = authorityRepository.findByUserId(username);
        List<GrantedAuthority> userAuthorities = new ArrayList<GrantedAuthority>(authorities.size());

        for (Authority authority : authorities) {
            String role = getRolePrefix() + authority.getAuthority();
            userAuthorities.add(new SimpleGrantedAuthority(role));
        }

        return userAuthorities;
    }

    @Override
    protected UserDetails createUserDetails(String username, UserDetails userFromUserQuery,
            List<GrantedAuthority> combinedAuthorities) {

        LoginUserDetails loginUser = (LoginUserDetails) userFromUserQuery;
        UserDetails returnUserDetails = super.createUserDetails(username, userFromUserQuery, combinedAuthorities);

        return new LoginUser(returnUserDetails, loginUser);
    }
}
