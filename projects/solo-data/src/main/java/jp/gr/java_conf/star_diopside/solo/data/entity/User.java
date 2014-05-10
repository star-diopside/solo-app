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
 * ユーザエンティティクラス
 */
@Data
@ToString(exclude = { "authorities", "userSchedules" })
@Entity
@Table(name = "users")
@SuppressWarnings("serial")
public class User implements Serializable, Cloneable {

    /** ユーザID */
    @Id
    @Column(name = "user_id")
    private String userId;

    /** ユーザ名 */
    private String username;

    /** パスワード */
    private String password;

    /** パスワード更新日時 */
    @Column(name = "password_updated_at")
    @Temporal(TemporalType.TIMESTAMP)
    private Date passwordUpdatedAt;

    /** 有効フラグ */
    private Boolean enabled;

    /** 仮登録フラグ */
    @Column(name = "interim_register")
    private Boolean interimRegister;

    /** ログインエラー回数 */
    @Column(name = "login_error_count")
    private Integer loginErrorCount;

    /** ロックアウト日時 */
    @Column(name = "lockout_at")
    @Temporal(TemporalType.TIMESTAMP)
    private Date lockoutAt;

    /** 最終ログイン日時 */
    @Column(name = "last_login_at")
    @Temporal(TemporalType.TIMESTAMP)
    private Date lastLoginAt;

    /** ログアウト日時 */
    @Column(name = "logout_at")
    @Temporal(TemporalType.TIMESTAMP)
    private Date logoutAt;

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

    /** 権限エンティティ一覧 */
    @OneToMany(mappedBy = "user")
    private List<Authority> authorities;

    /** ユーザスケジュールエンティティ一覧 */
    @OneToMany(mappedBy = "user")
    private List<UserSchedule> userSchedules;

    @Override
    public User clone() {
        try {
            return (User) super.clone();
        } catch (CloneNotSupportedException e) {
            throw new UnsupportedOperationException(e);
        }
    }
}
