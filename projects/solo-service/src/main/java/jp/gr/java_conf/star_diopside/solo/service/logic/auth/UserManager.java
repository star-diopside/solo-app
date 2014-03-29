package jp.gr.java_conf.star_diopside.solo.service.logic.auth;

import jp.gr.java_conf.star_diopside.solo.service.userdetails.LoginUserDetails;

import org.springframework.security.core.AuthenticationException;

/**
 * ユーザ管理インタフェース
 */
public interface UserManager {

    /**
     * ユーザを作成する。
     * 
     * @param userId ユーザID
     * @param username ユーザ名
     * @param password パスワード
     */
    void createUser(String userId, String username, String password);

    /**
     * 取得したユーザ情報が有効かどうか判定する。
     * 
     * @param loginUser ユーザ情報
     * @throws AuthenticationException 無効なユーザ情報と判定した場合
     */
    void checkValid(LoginUserDetails loginUser) throws AuthenticationException;

    /**
     * ログイン成功時の処理を行う。
     * 
     * @param loginUser ユーザ情報
     */
    void loginSuccess(LoginUserDetails loginUser);

    /**
     * ログイン失敗時の処理を行う。
     * 
     * @param userId ユーザID
     */
    void loginFailure(String userId);

    /**
     * ログアウト処理を行う。
     * 
     * @param loginUser ユーザ情報
     */
    void logout(LoginUserDetails loginUser);

    /**
     * 二重ログインチェックを行う。
     * 
     * @param loginUser ユーザ情報
     * @return 二重ログインエラーの場合はtrue、それ以外の場合はfalse。
     */
    boolean checkDualLogin(LoginUserDetails loginUser);

}
