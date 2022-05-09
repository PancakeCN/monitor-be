package com.pancake.monitorbe.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * 工作信息状态
 *
 * @author PancakeCN
 * @link https://github.com/PancakeCN
 * @date 2022/3/12 17:09
 */
@NoArgsConstructor
@AllArgsConstructor
@Data
@ToString
public class WorkInfo {

    /**id值*/
    private Integer id;
    /**系统识别码*/
    private String sysCode;
    /**终端识别码*/
    private String tmCode;
    /**工作状态信息*/
    private String workInfo;
    /**工作IP*/
    private String workIp;
    /**更新时间*/
    private String updateTime;
}
