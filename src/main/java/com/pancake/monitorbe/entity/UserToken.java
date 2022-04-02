package com.pancake.monitorbe.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.Date;

/**
 * @author PancakeCN
 * @date 2022/3/3 21:03
 */
@NoArgsConstructor
@AllArgsConstructor
@Data
@ToString
public class UserToken {

    /**用户登录名*/
    private String loginName;
    /**用户token*/
    private String token;
    /**更新时间*/
    private Date updateTime;
    /**过期时间*/
    private Date expireTime;
}
