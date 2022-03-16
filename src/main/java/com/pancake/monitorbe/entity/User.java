package com.pancake.monitorbe.entity;

import lombok.Data;

@Data
public class User {
    /**用户登录名*/
    private String loginName;
    /**用户名*/
    private String username;
    /**密码*/
    private String password;
    /**权限*/
    private int auth;
    /**电话号码*/
    private String phoneNumber;

    public User(String loginName, String username, String password, int auth, String phoneNumber) {
        this.loginName = loginName;
        this.username = username;
        this.password = password;
        this.auth = auth;
        this.phoneNumber = phoneNumber;
    }
}
