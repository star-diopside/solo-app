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
    @Column(name = "password_updated_timestamp")
    @Temporal(TemporalType.TIMESTAMP)
    private Date passwordUpdatedTimestamp;

    /** 有効フラグ */
    private Boolean enabled;

    /** 仮登録フラグ */
    @Column(name = "interim_register")
    private Boolean interimRegister;

    /** ログインエラー回数 */
    @Column(name = "login_error_count")
    private Integer loginErrorCount;

    /** ロックアウト日時 */
    @Column(name = "lockout_timestamp")
    @Temporal(TemporalType.TIMESTAMP)
    private Date lockoutTimestamp;

    /** 最終ログイン日時 */
    @Column(name = "last_login_timestamp")
    @Temporal(TemporalType.TIMESTAMP)
    private Date lastLoginTimestamp;

    /** ログアウト日時 */
    @Column(name = "logout_timestamp")
    @Temporal(TemporalType.TIMESTAMP)
    private Date logoutTimestamp;

    /** 登録日時 */
    @Column(name = "register_timestamp")
    @Temporal(TemporalType.TIMESTAMP)
    private Date registerTimestamp;

    /** 登録ユーザID */
    @Column(name = "register_user_id")
    private String registerUserId;

    /** 更新日時 */
    @Column(name = "updated_timestamp")
    @Temporal(TemporalType.TIMESTAMP)
    private Date updatedTimestamp;

    /** 更新ユーザID */
    @Column(name = "updated_user_id")
    private String updatedUserId;

    /** バージョン */
    @Version
    private Integer version;

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
        this.passwordUpdatedTimestamp = user.passwordUpdatedTimestamp;
        this.enabled = user.enabled;
        this.interimRegister = user.interimRegister;
        this.loginErrorCount = user.loginErrorCount;
        this.lockoutTimestamp = user.lockoutTimestamp;
        this.lastLoginTimestamp = user.lastLoginTimestamp;
        this.logoutTimestamp = user.logoutTimestamp;
        this.registerTimestamp = user.registerTimestamp;
        this.registerUserId = user.registerUserId;
        this.updatedTimestamp = user.updatedTimestamp;
        this.updatedUserId = user.updatedUserId;
        this.version = user.version;
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
    public Date getPasswordUpdatedTimestamp() {
        return passwordUpdatedTimestamp;
    }

    /**
     * パスワード更新日時を設定する。
     * 
     * @param passwordUpdatedTimestamp パスワード更新日時
     */
    public void setPasswordUpdatedTimestamp(Date passwordUpdatedTimestamp) {
        this.passwordUpdatedTimestamp = passwordUpdatedTimestamp;
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
    public Date getLockoutTimestamp() {
        return lockoutTimestamp;
    }

    /**
     * ロックアウト日時を設定する。
     * 
     * @param lockoutTimestamp ロックアウト日時
     */
    public void setLockoutTimestamp(Date lockoutTimestamp) {
        this.lockoutTimestamp = lockoutTimestamp;
    }

    /**
     * 最終ログイン日時を取得する。
     * 
     * @return 最終ログイン日時
     */
    public Date getLastLoginTimestamp() {
        return lastLoginTimestamp;
    }

    /**
     * 最終ログイン日時を設定する。
     * 
     * @param lastLoginTimestamp 最終ログイン日時
     */
    public void setLastLoginTimestamp(Date lastLoginTimestamp) {
        this.lastLoginTimestamp = lastLoginTimestamp;
    }

    /**
     * ログアウト日時を取得する。
     * 
     * @return ログアウト日時
     */
    public Date getLogoutTimestamp() {
        return logoutTimestamp;
    }

    /**
     * ログアウト日時を設定する。
     * 
     * @param logoutTimestamp ログアウト日時
     */
    public void setLogoutTimestamp(Date logoutTimestamp) {
        this.logoutTimestamp = logoutTimestamp;
    }

    /**
     * 登録日時を取得する。
     * 
     * @return 登録日時
     */
    public Date getRegisterTimestamp() {
        return registerTimestamp;
    }

    /**
     * 登録日時を設定する。
     * 
     * @param registerTimestamp 登録日時
     */
    public void setRegisterTimestamp(Date registerTimestamp) {
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
    public Date getUpdatedTimestamp() {
        return updatedTimestamp;
    }

    /**
     * 更新日時を設定する。
     * 
     * @param updatedTimestamp 更新日時
     */
    public void setUpdatedTimestamp(Date updatedTimestamp) {
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
