package jp.gr.java_conf.star_diopside.solo.web.mvc.auth.form;

import java.io.Serializable;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import jp.gr.java_conf.star_diopside.solo.validation.constraints.NotBlank;
import jp.gr.java_conf.star_diopside.solo.validation.constraints.NotEmpty;

/**
 * AA02S010(ユーザ新規登録)画面フォーム
 */
@SuppressWarnings("serial")
public class AA02S010Form implements Serializable {

    /** ユーザ名 */
    @NotNull(message = "{jp.gr.java_conf.star_diopside.solo.validation.Required.username.message}")
    @NotBlank(message = "{jp.gr.java_conf.star_diopside.solo.validation.Required.username.message}")
    @Size(min = 6, max = 50, message = "{jp.gr.java_conf.star_diopside.solo.validation.Size.username.message}")
    private String username;

    /** ニックネーム */
    @NotNull(message = "{jp.gr.java_conf.star_diopside.solo.validation.Required.nickname.message}")
    @NotBlank(message = "{jp.gr.java_conf.star_diopside.solo.validation.Required.nickname.message}")
    @Size(max = 50, message = "jp.gr.java_conf.star_diopside.solo.validation.Size.nickname.message")
    private String nickname;

    /** パスワード */
    @NotNull(message = "{jp.gr.java_conf.star_diopside.solo.validation.Required.password.message}")
    @NotEmpty(message = "{jp.gr.java_conf.star_diopside.solo.validation.Required.password.message}")
    @Size(min = 6, message = "{jp.gr.java_conf.star_diopside.solo.validation.Size.password.message}")
    private String password;

    /** パスワード(確認) */
    @NotNull(message = "{jp.gr.java_conf.star_diopside.solo.validation.Required.passwordConfirm.message}")
    @NotEmpty(message = "{jp.gr.java_conf.star_diopside.solo.validation.Required.passwordConfirm.message}")
    private String passwordConfirm;

    /** キャプチャ */
    @NotNull(message = "{jp.gr.java_conf.star_diopside.solo.validation.Required.captcha.message}")
    @NotEmpty(message = "{jp.gr.java_conf.star_diopside.solo.validation.Required.captcha.message}")
    private String captcha;

    /**
     * ユーザ名を取得する。
     * 
     * @return ユーザ名
     */
    public String getUsername() {
        return username;
    }

    /**
     * ユーザ名を設定する。
     * 
     * @param username ユーザ名
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * ニックネームを取得する。
     * 
     * @return ニックネーム
     */
    public String getNickname() {
        return nickname;
    }

    /**
     * ニックネームを設定する。
     * 
     * @param nickname ニックネーム
     */
    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    /**
     * パスワードを取得する。
     * 
     * @return パスワード
     */
    public String getPassword() {
        return password;
    }

    /**
     * パスワードを設定する。
     * 
     * @param password パスワード
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * パスワード(確認)を取得する。
     * 
     * @return パスワード(確認)
     */
    public String getPasswordConfirm() {
        return passwordConfirm;
    }

    /**
     * パスワード(確認)を設定する。
     * 
     * @param passwordConfirm パスワード(確認)
     */
    public void setPasswordConfirm(String passwordConfirm) {
        this.passwordConfirm = passwordConfirm;
    }

    /**
     * キャプチャを取得する。
     * 
     * @return キャプチャ
     */
    public String getCaptcha() {
        return captcha;
    }

    /**
     * キャプチャを設定する。
     * 
     * @param captcha キャプチャ
     */
    public void setCaptcha(String captcha) {
        this.captcha = captcha;
    }
}
