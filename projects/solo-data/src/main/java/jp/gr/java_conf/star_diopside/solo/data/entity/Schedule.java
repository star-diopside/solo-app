package jp.gr.java_conf.star_diopside.solo.data.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Version;

import lombok.Data;
import lombok.ToString;

/**
 * スケジュールエンティティクラス
 */
@Data
@ToString(exclude = "userSchedules")
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

    /** ユーザスケジュールエンティティ一覧 */
    @OneToMany(mappedBy = "schedule")
    private List<UserSchedule> userSchedules;

}
