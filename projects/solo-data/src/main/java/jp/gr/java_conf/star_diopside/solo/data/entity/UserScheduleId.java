package jp.gr.java_conf.star_diopside.solo.data.entity;

import java.io.Serializable;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

/**
 * ユーザスケジュールエンティティクラス
 */
@SuppressWarnings("serial")
public class UserScheduleId implements Serializable {

    /** ユーザID */
    private String userId;

    /** スケジュールID */
    private String scheduleId;

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
