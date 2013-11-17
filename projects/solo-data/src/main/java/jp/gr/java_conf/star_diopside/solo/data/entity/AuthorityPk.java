package jp.gr.java_conf.star_diopside.solo.data.entity;

import java.io.Serializable;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

/**
 * 権限主キークラス
 */
@SuppressWarnings("serial")
public class AuthorityPk implements Serializable {

    /** ユーザ名 */
    private String username;

    /** 権限 */
    private String authority;

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
