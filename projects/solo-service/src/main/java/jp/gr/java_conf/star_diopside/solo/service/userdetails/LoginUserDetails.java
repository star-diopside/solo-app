package jp.gr.java_conf.star_diopside.solo.service.userdetails;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;

/**
 * ログインユーザ情報詳細インタフェース
 */
public interface LoginUserDetails {

    /**
     * ユーザ権限情報を取得する。
     * 
     * @return ユーザ権限情報
     */
    Collection<? extends GrantedAuthority> getAuthorities();

    /**
     * ユーザIDを取得する。
     * 
     * @return ユーザID
     */
    String getUserId();

    /**
     * パスワードを取得する。
     * 
     * @return パスワード
     */
    String getPassword();

    /**
     * アカウントが有効期限切れであるかを示す値を取得する。
     * 
     * @return アカウントが有効な場合は<code>true</code>、無効な場合は<code>false</code>。
     */
    boolean isAccountNonExpired();

    /**
     * アカウント化ロックされているかを示す値を取得する。
     * 
     * @return アカウントがロックされていない場合は<code>true</code>、ロックされている場合は<code>false</code>
     *         。
     */
    boolean isAccountNonLocked();

    /**
     * 資格情報(パスワード)が有効期限切れであるかを示す値を取得する。
     * 
     * @return 資格情報が有効な場合は<code>true</code>、無効な場合は<code>false</code>。
     */
    boolean isCredentialsNonExpired();

    /**
     * ユーザが有効であるかを示す値を取得する。
     * 
     * @return ユーザが有効な場合は<code>true</code>、無効な場合は<code>false</code>。
     */
    boolean isEnabled();

}
