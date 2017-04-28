package com.aidansu.demo.model;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by aidan on 17-4-26.
 */
public class User implements Serializable{

    private Long id;
    private String username;
    private String password;
    private String telephone;
    private Date createTime;
    private Date lastLoginTime;
    private Date updateTime;

    public User() {
    }

    public User(String username, String password, String telephone, Date createTime, Date lastLoginTime, Date updateTime) {
        this.username = username;
        this.password = password;
        this.telephone = telephone;
        this.createTime = createTime;
        this.lastLoginTime = lastLoginTime;
        this.updateTime = updateTime;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getLastLoginTime() {
        return lastLoginTime;
    }

    public void setLastLoginTime(Date lastLoginTime) {
        this.lastLoginTime = lastLoginTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }
}
