package jp.gr.java_conf.star_diopside.solo.data.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Version;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

/**
 * ユーザスケジュールエンティティクラス
 */
@Entity
@Table(name = "user_schedule")
@IdClass(UserScheduleId.class)
@SuppressWarnings("serial")
public class UserSchedule implements Serializable {

    /** ユーザID */
    @Id
    @Column(name = "user_id")
    private String userId;

    /** スケジュールID */
    @Id
    @Column(name = "schedule_id")
    private String scheduleId;

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

    /** ユーザエンティティ */
    @ManyToOne
    @JoinColumn(name = "user_id", insertable = false, updatable = false)
    private User user;

    /** スケジュールエンティティ */
    @ManyToOne
    @JoinColumn(name = "schedule_id", insertable = false, updatable = false)
    private Schedule schedule;

    /**
     * ユーザIDを取得する。
     * 
     * @return ユーザID
     */
    public String getUserId() {
        return userId;
    }

    /**
     * ユーザIDを設定する。
     * 
     * @param userId ユーザID
     */
    public void setUserId(String userId) {
        this.userId = userId;
    }

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

    /**
     * ユーザエンティティを取得する。
     * 
     * @return ユーザエンティティ
     */
    public User getUser() {
        return user;
    }

    /**
     * ユーザエンティティを設定する。
     * 
     * @param user ユーザエンティティ
     */
    public void setUser(User user) {
        this.user = user;
    }

    /**
     * スケジュールエンティティを取得する。
     * 
     * @return スケジュールエンティティ
     */
    public Schedule getSchedule() {
        return schedule;
    }

    /**
     * スケジュールエンティティを設定する。
     * 
     * @param schedule スケジュールエンティティ
     */
    public void setSchedule(Schedule schedule) {
        this.schedule = schedule;
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
