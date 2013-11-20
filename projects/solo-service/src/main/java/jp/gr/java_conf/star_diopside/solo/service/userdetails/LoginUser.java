package jp.gr.java_conf.star_diopside.solo.service.userdetails;

import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;

/**
 * ログインユーザ情報クラス
 */
@SuppressWarnings("serial")
public class LoginUser extends User implements LoginUserDetails {

    /**
     * コンストラクタ
     * 
     * @param user ユーザエンティティ
     */
    public LoginUser(jp.gr.java_conf.star_diopside.solo.data.entity.User user) {
        super(user.getUserId(), user.getPassword(), user.getEnabled(), true, true, true, AuthorityUtils.NO_AUTHORITIES);
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
    }

    @Override
    public String getUserId() {
        return getUsername();
    }
}
