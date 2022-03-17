package com.pancake.monitorbe.model;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * 用户信息（部分）
 *
 * @author PancakeCN
 * @link https://github.com/PancakeCN
 * @date 2022/3/15 1:07
 */
@Data
@AllArgsConstructor
public class UserResult {

    /**登录名*/
    private String loginName;
    /**用户名*/
    private String username;
    /**密码*/
    private String password;
    /**权限*/
    private int auth;
    /**权限描述*/
    private String authComment;
    /**电话号码*/
    private String phoneNumber;

}
