package com.pancake.monitorbe.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * 用户-系统映射
 *
 * @author PancakeCN
 * @link https://github.com/PancakeCN
 * @date 2022/3/12 17:06
 */
@NoArgsConstructor
@AllArgsConstructor
@Data
@ToString
public class UserSys {
    /**登录名*/
    private String loginName;
    /**系统识别码*/
    private String sysCode;

}
