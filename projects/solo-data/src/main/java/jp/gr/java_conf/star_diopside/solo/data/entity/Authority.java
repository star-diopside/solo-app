package jp.gr.java_conf.star_diopside.solo.data.entity;

import java.io.Serializable;
import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

/**
 * 権限エンティティクラス
 */
@Entity
@Table(name = "authorities")
@IdClass(AuthorityPk.class)
@SuppressWarnings("serial")
public class Authority implements Serializable {

    /** ユーザID */
    @Id
    @Column(name = "user_id")
    private String userId;

    /** 権限 */
    @Id
    private String authority;

    /** 登録日時 */
    @Column(name = "register_timestamp")
    private Timestamp registerTimestamp;

    /** 登録ユーザID */
    @Column(name = "register_user_id")
    private String registerUserId;

    /** 更新日時 */
    @Column(name = "updated_timestamp")
    private Timestamp updatedTimestamp;

    /** 更新ユーザID */
    @Column(name = "updated_user_id")
    private String updatedUserId;

    /** バージョン */
    private Integer version;

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
     * 権限を取得する。
     * 
     * @return 権限
     */
    public String getAuthority() {
        return authority;
    }

    /**
     * 権限を設定する。
     * 
     * @param authority 権限
     */
    public void setAuthority(String authority) {
        this.authority = authority;
    }

    /**
     * 登録日時を取得する。
     * 
     * @return 登録日時
     */
    public Timestamp getRegisterTimestamp() {
        return registerTimestamp;
    }

    /**
     * 登録日時を設定する。
     * 
     * @param registerTimestamp 登録日時
     */
    public void setRegisterTimestamp(Timestamp registerTimestamp) {
        this.registerTimestamp = registerTimestamp;
    }

    /**
     * 登録ユーザIDを取得する。
     * 
     * @return 登録ユーザID
     */
    public String getRegisterUserId() {
        return registerUserId;
    }

    /**
     * 登録ユーザIDを設定する。
     * 
     * @param registerUserId 登録ユーザID
     */
    public void setRegisterUserId(String registerUserId) {
        this.registerUserId = registerUserId;
    }

    /**
     * 更新日時を取得する。
     * 
     * @return 更新日時
     */
    public Timestamp getUpdatedTimestamp() {
        return updatedTimestamp;
    }

    /**
     * 更新日時を設定する。
     * 
     * @param updatedTimestamp 更新日時
     */
    public void setUpdatedTimestamp(Timestamp updatedTimestamp) {
        this.updatedTimestamp = updatedTimestamp;
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
