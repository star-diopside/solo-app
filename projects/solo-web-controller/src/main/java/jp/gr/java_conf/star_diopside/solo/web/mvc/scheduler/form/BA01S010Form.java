package jp.gr.java_conf.star_diopside.solo.web.mvc.scheduler.form;

import java.io.Serializable;

import javax.validation.constraints.NotNull;

import jp.gr.java_conf.star_diopside.solo.validation.constraints.NotBlank;

import org.maru.m4hv.extensions.constraints.ActualDate;

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
     * スケジュール日時を取得する。
     * 
     * @return スケジュール日時
     */
    public String getScheduledOn() {
        return scheduledOn;
    }

    /**
     * スケジュール日時を設定する。
     * 
     * @param scheduledOn スケジュール日時
     */
    public void setScheduledOn(String scheduledOn) {
        this.scheduledOn = scheduledOn;
    }

    /**
     * 説明を取得する。
     * 
     * @return 説明
     */
    public String getDescription() {
        return description;
    }

    /**
     * 説明を設定する。
     * 
     * @param description 説明
     */
    public void setDescription(String description) {
        this.description = description;
    }
}
