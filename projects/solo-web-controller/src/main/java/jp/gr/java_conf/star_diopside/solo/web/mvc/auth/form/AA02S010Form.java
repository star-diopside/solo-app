package jp.gr.java_conf.star_diopside.solo.web.mvc.auth.form;

import java.io.Serializable;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import jp.gr.java_conf.star_diopside.solo.validation.constraints.NotBlank;
import jp.gr.java_conf.star_diopside.solo.validation.constraints.NotEmpty;
import lombok.Data;
import lombok.ToString;

/**
 * AA02S010(ユーザ新規登録)画面フォーム
 */
@Data
@ToString(exclude = { "password", "passwordConfirm" })
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

}
