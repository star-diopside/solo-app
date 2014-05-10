package jp.gr.java_conf.star_diopside.solo.data.entity;

import java.io.Serializable;

import lombok.Data;

/**
 * ユーザスケジュールエンティティクラス
 */
@Data
@SuppressWarnings("serial")
public class UserScheduleId implements Serializable {

    /** ユーザID */
    private String userId;

    /** スケジュールID */
    private String scheduleId;

}
