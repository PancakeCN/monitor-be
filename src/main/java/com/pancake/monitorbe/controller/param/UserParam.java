package com.pancake.monitorbe.controller.param;

import com.pancake.monitorbe.model.UserSysResult;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * 用户参数
 *
 * @author PancakeCN
 * @link https://github.com/PancakeCN
 * @date 2022/3/14 2:20
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserParam implements Serializable {

    /**用户登录名*/
    private String loginName;
    /**用户名*/
    private String username;
    /**密码（前端不应该明文显示）*/
    private String password;
    /**权限标识符*/
    private int auth;
    /**权限描述*/
    private String authComment;
    /**
     * 用户-系统映射 使用object原因：类型不确定。
     * 当auth为0（管理员）时，userSys为null；auth不为0时，Object为UserSysResult类
     */
    private ArrayList<UserSysResult> userSys;
    /**电话号码*/
    private String phoneNumber;
}
