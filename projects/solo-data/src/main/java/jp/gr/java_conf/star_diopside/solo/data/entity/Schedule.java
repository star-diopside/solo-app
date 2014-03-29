package jp.gr.java_conf.star_diopside.solo.data.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Version;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

/**
 * スケジュールエンティティクラス
 */
@Entity
@Table(name = "schedules")
@SuppressWarnings("serial")
public class Schedule implements Serializable {

    /** スケジュールID */
    @Id
    @Column(name = "schedule_id")
    private String scheduleId;

    /** スケジュール日時 */
    @Column(name = "scheduled_on")
    @Temporal(TemporalType.DATE)
    private Date scheduledOn;

    /** 説明 */
    private String description;

    /** 登録日時 */
    @Column(name = "created_at")
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdAt;

    /** 登録ユーザID */
    @Column(name = "created_user_id")
    private String createdUserId;

    /** 更新日時 */
    @Column(name = "updated_at")
    @Temporal(TemporalType.TIMESTAMP)
    private Date updatedAt;

    /** 更新ユーザID */
    @Column(name = "updated_user_id")
    private String updatedUserId;

    /** バージョン */
    @Version
    private Integer version;

    /**
     * スケジュールIDを取得する。
     * 
     * @return スケジュールID
     */
    public String getScheduleId() {
        return scheduleId;
    }

    /**
     * スケジュールIDを設定する。
     * 
     * @param scheduleId スケジュールID
     */
    public void setScheduleId(String scheduleId) {
        this.scheduleId = scheduleId;
    }

    /**
     * スケジュール日時を取得する。
     * 
     * @return スケジュール日時
     */
    public Date getScheduledOn() {
        return scheduledOn;
    }

    /**
     * スケジュール日時を設定する。
     * 
     * @param scheduledOn スケジュール日時
     */
    public void setScheduledOn(Date scheduledOn) {
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

    /**
     * 登録日時を取得する。
     * 
     * @return 登録日時
     */
    public Date getCreatedAt() {
        return createdAt;
    }

    /**
     * 登録日時を設定する。
     * 
     * @param createdAt 登録日時
     */
    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    /**
     * 登録ユーザIDを取得する。
     * 
     * @return 登録ユーザID
     */
    public String getCreatedUserId() {
        return createdUserId;
    }

    /**
     * 登録ユーザIDを設定する。
     * 
     * @param createdUserId 登録ユーザID
     */
    public void setCreatedUserId(String createdUserId) {
        this.createdUserId = createdUserId;
    }

    /**
     * 更新日時を取得する。
     * 
     * @return 更新日時
     */
    public Date getUpdatedAt() {
        return updatedAt;
    }

    /**
     * 更新日時を設定する。
     * 
     * @param updatedAt 更新日時
     */
    public void setUpdatedAt(Date updatedAt) {
        this.updatedAt = updatedAt;
    }

    /**
     * 更新ユーザIDを取得する。
     * 
     * @return 更新ユーザID
     */
    public String getUpdatedUserId() {
        return updatedUserId;
    }

    /**
     * 更新ユーザIDを設定する。
     * 
     * @param updatedUserId 更新ユーザID
     */
    public void setUpdatedUserId(String updatedUserId) {
        this.updatedUserId = updatedUserId;
    }

    /**
     * バージョンを取得する。
     * 
     * @return バージョン
     */
    public Integer getVersion() {
        return version;
    }

    /**
     * バージョンを設定する。
     * 
     * @param version バージョン
     */
    public void setVersion(Integer version) {
        this.version = version;
    }

    @Override
    public boolean equals(Object obj) {
        return EqualsBuilder.reflectionEquals(this, obj);
    }

    @Override
    public int hashCode() {
        return HashCodeBuilder.reflectionHashCode(this);
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }
}
