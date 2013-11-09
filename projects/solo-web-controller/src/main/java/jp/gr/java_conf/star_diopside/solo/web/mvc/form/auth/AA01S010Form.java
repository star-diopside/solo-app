package jp.gr.java_conf.star_diopside.solo.web.mvc.form.auth;

import java.io.Serializable;

import javax.validation.constraints.NotNull;

import jp.gr.java_conf.star_diopside.solo.validation.constraints.NotBlank;
import jp.gr.java_conf.star_diopside.solo.validation.constraints.NotEmpty;

/**
 * AA01S010(ユーザログイン)画面フォーム
 */
@SuppressWarnings("serial")
public class AA01S010Form implements Serializable {

    /** ユーザ名 */
    @NotNull(message = "{jp.gr.java_conf.star_diopside.solo.validation.Required.username.message}")
    @NotBlank(message = "{jp.gr.java_conf.star_diopside.solo.validation.Required.username.message}")
    private String username;

    /** パスワード */
    @NotNull(message = "{jp.gr.java_conf.star_diopside.solo.validation.Required.password.message}")
    @NotEmpty(message = "{jp.gr.java_conf.star_diopside.solo.validation.Required.password.message}")
    private String password;

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
}
