package jp.gr.java_conf.star_diopside.solo.web.mvc.auth.form;

import java.io.Serializable;

import javax.validation.constraints.NotNull;

import jp.gr.java_conf.star_diopside.solo.validation.constraints.NotBlank;
import jp.gr.java_conf.star_diopside.solo.validation.constraints.NotEmpty;
import lombok.Data;
import lombok.ToString;

/**
 * AA01S010(ユーザログイン)画面フォーム
 */
@Data
@ToString(exclude = "password")
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

}
