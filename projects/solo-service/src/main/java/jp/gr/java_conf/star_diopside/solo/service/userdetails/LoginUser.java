package jp.gr.java_conf.star_diopside.solo.service.userdetails;

import jp.gr.java_conf.star_diopside.solo.data.entity.User;

import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;

/**
 * ログインユーザ情報クラス
 */
@SuppressWarnings("serial")
public class LoginUser extends org.springframework.security.core.userdetails.User implements LoginUserDetails {

    private User user;

    /**
     * コンストラクタ
     * 
     * @param user ユーザエンティティ
     */
    public LoginUser(User user) {
        super(user.getUserId(), user.getPassword(), user.getEnabled(), true, true, true, AuthorityUtils.NO_AUTHORITIES);
        this.user = new User(user);
    }

    /**
     * コンストラクタ
     * 
     * @param user ユーザ情報
     * @param loginUser ログインユーザ情報
     */
    public LoginUser(UserDetails user, LoginUserDetails loginUser) {
        super(user.getUsername(), user.getPassword(), user.isEnabled(), user.isAccountNonExpired(),
                user.isCredentialsNonExpired(), user.isAccountNonLocked(), user.getAuthorities());
        this.user = loginUser.convertUserEntity();
    }

    @Override
    public String getUserId() {
        return getUsername();
    }

    @Override
    public String getNickname() {
        return user.getUsername();
    }

    @Override
    public User convertUserEntity() {
        return new User(user);
    }
}
