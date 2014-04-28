package jp.gr.java_conf.star_diopside.solo.service.logic.auth;

import jp.gr.java_conf.star_diopside.solo.data.entity.User;
import jp.gr.java_conf.star_diopside.solo.service.userdetails.LoginUserDetails;

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
     * @param user ユーザエンティティ
     * @return 有効なユーザ情報と判定した場合はtrue、無効なユーザと判定した場合はfalse
     */
    boolean checkValid(User user);

    /**
     * ユーザを削除する。
     * 
     * @param user ユーザエンティティ
     */
    void removeUser(User user);

    /**
     * ユーザ状態を判定し、無効ユーザの場合は削除する。
     * 
     * @param loginUser ユーザ情報
     * @return 無効なユーザと判定して削除した場合はtrue、それ以外の場合はfalse
     */
    boolean removeInvalidUser(LoginUserDetails loginUser);

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
