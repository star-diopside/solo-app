package jp.gr.java_conf.star_diopside.solo.service.userdetails;

import java.util.Collection;

import jp.gr.java_conf.star_diopside.solo.data.entity.User;

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
     * ニックネームを取得する。
     * 
     * @return nickname ニックネーム
     */
    String getNickname();

    /**
     * パスワードを取得する。
     * 
     * @return パスワード
     */
    String getPassword();

    /**
     * アカウントが有効期限切れであるかを示す値を取得する。
     * 
     * @return アカウントが有効な場合はtrue、無効な場合はfalse。
     */
    boolean isAccountNonExpired();

    /**
     * アカウント化ロックされているかを示す値を取得する。
     * 
     * @return アカウントがロックされていない場合はtrue、ロックされている場合はfalse。
     */
    boolean isAccountNonLocked();

    /**
     * 資格情報(パスワード)が有効期限切れであるかを示す値を取得する。
     * 
     * @return 資格情報が有効な場合はtrue、無効な場合はfalse。
     */
    boolean isCredentialsNonExpired();

    /**
     * ユーザが有効であるかを示す値を取得する。
     * 
     * @return ユーザが有効な場合はtrue、無効な場合はfalse。
     */
    boolean isEnabled();

    /**
     * ユーザエンティティに変換する。
     * 
     * @return ユーザエンティティ
     */
    User convertUserEntity();

}
