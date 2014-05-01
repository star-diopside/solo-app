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

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

/**
 * ユーザエンティティクラス
 */
@Entity
@Table(name = "users")
@SuppressWarnings("serial")
public class User implements Serializable {

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

    /**
     * デフォルトコンストラクタ
     */
    public User() {
    }

    /**
     * コピーコンストラクタ
     * 
     * @param user コピー元インスタンス
     */
    public User(User user) {
        this.userId = user.userId;
        this.username = user.username;
        this.password = user.password;
        this.passwordUpdatedAt = user.passwordUpdatedAt;
        this.enabled = user.enabled;
        this.interimRegister = user.interimRegister;
        this.loginErrorCount = user.loginErrorCount;
        this.lockoutAt = user.lockoutAt;
        this.lastLoginAt = user.lastLoginAt;
        this.logoutAt = user.logoutAt;
        this.createdAt = user.createdAt;
        this.createdUserId = user.createdUserId;
        this.updatedAt = user.updatedAt;
        this.updatedUserId = user.updatedUserId;
        this.version = user.version;
        this.authorities = user.authorities;
        this.userSchedules = user.userSchedules;
    }

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
     * パスワードを取得する。
     * 
     * @return パスワード
     */
    public String getPassword() {
        return password;
    }

    /**
     * パスワードを設定する。
     * 
     * @param password パスワード
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * パスワード更新日時を取得する。
     * 
     * @return パスワード更新日時
     */
    public Date getPasswordUpdatedAt() {
        return passwordUpdatedAt;
    }

    /**
     * パスワード更新日時を設定する。
     * 
     * @param passwordUpdatedAt パスワード更新日時
     */
    public void setPasswordUpdatedAt(Date passwordUpdatedAt) {
        this.passwordUpdatedAt = passwordUpdatedAt;
    }

    /**
     * 有効フラグを取得する。
     * 
     * @return 有効フラグ
     */
    public Boolean getEnabled() {
        return enabled;
    }

    /**
     * 有効フラグを設定する。
     * 
     * @param enabled 有効フラグ
     */
    public void setEnabled(Boolean enabled) {
        this.enabled = enabled;
    }

    /**
     * 仮登録フラグを取得する。
     * 
     * @return 仮登録フラグ
     */
    public Boolean getInterimRegister() {
        return interimRegister;
    }

    /**
     * 仮登録フラグを設定する。
     * 
     * @param interimRegister 仮登録フラグ
     */
    public void setInterimRegister(Boolean interimRegister) {
        this.interimRegister = interimRegister;
    }

    /**
     * ログインエラー回数を取得する。
     * 
     * @return ログインエラー回数
     */
    public Integer getLoginErrorCount() {
        return loginErrorCount;
    }

    /**
     * ログインエラー回数を設定する。
     * 
     * @param loginErrorCount ログインエラー回数
     */
    public void setLoginErrorCount(Integer loginErrorCount) {
        this.loginErrorCount = loginErrorCount;
    }

    /**
     * ロックアウト日時を取得する。
     * 
     * @return ロックアウト日時
     */
    public Date getLockoutAt() {
        return lockoutAt;
    }

    /**
     * ロックアウト日時を設定する。
     * 
     * @param lockoutAt ロックアウト日時
     */
    public void setLockoutAt(Date lockoutAt) {
        this.lockoutAt = lockoutAt;
    }

    /**
     * 最終ログイン日時を取得する。
     * 
     * @return 最終ログイン日時
     */
    public Date getLastLoginAt() {
        return lastLoginAt;
    }

    /**
     * 最終ログイン日時を設定する。
     * 
     * @param lastLoginAt 最終ログイン日時
     */
    public void setLastLoginAt(Date lastLoginAt) {
        this.lastLoginAt = lastLoginAt;
    }

    /**
     * ログアウト日時を取得する。
     * 
     * @return ログアウト日時
     */
    public Date getLogoutAt() {
        return logoutAt;
    }

    /**
     * ログアウト日時を設定する。
     * 
     * @param logoutAt ログアウト日時
     */
    public void setLogoutAt(Date logoutAt) {
        this.logoutAt = logoutAt;
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
     * 権限エンティティ一覧を取得する。
     * 
     * @return 権限エンティティ一覧
     */
    public List<Authority> getAuthorities() {
        return authorities;
    }

    /**
     * 権限エンティティ一覧を設定する。
     * 
     * @param authorities 権限エンティティ一覧
     */
    public void setAuthorities(List<Authority> authorities) {
        this.authorities = authorities;
    }

    /**
     * ユーザスケジュールエンティティ一覧を取得する。
     * 
     * @return ユーザスケジュールエンティティ一覧
     */
    public List<UserSchedule> getUserSchedules() {
        return userSchedules;
    }

    /**
     * ユーザスケジュールエンティティ一覧を設定する。
     * 
     * @param userSchedules ユーザスケジュールエンティティ一覧
     */
    public void setUserSchedules(List<UserSchedule> userSchedules) {
        this.userSchedules = userSchedules;
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
