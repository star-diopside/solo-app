package jp.gr.java_conf.star_diopside.solo.web.mvc.scheduler.form;

import java.io.Serializable;

import javax.validation.constraints.NotNull;

import jp.gr.java_conf.star_diopside.solo.validation.constraints.NotBlank;
import lombok.Data;

import org.maru.m4hv.extensions.constraints.ActualDate;

@Data
@SuppressWarnings("serial")
public class BA01S010Form implements Serializable {

    /** ユーザ名 */
    @NotNull(message = "{jp.gr.java_conf.star_diopside.solo.validation.Required.username.message}")
    @NotBlank(message = "{jp.gr.java_conf.star_diopside.solo.validation.Required.username.message}")
    private String username;

    /** スケジュール日時 */
    @NotNull(message = "{jp.gr.java_conf.star_diopside.solo.validation.Required.scheduledOn.message}")
    @ActualDate(patterns = "yyyy-MM-dd", from = "1970-01-01", message = "{jp.gr.java_conf.star_diopside.solo.validation.ActualDate.scheduledOn.message}")
    private String scheduledOn;

    /** 説明 */
    private String description;

}
