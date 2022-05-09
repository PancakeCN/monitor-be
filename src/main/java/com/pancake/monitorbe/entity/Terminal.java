package com.pancake.monitorbe.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * 终端实体类
 *
 * @author PancakeCN
 * @date 2022/3/2 1:35
 */
@NoArgsConstructor
@AllArgsConstructor
@Data
@ToString
public class Terminal {
    /**系统识别码*/
    private String sysCode;
    /**系统识别码*/
    private String tmCode;
    /**终端名称*/
    private String tmName;
    /**IP地址*/
    private String tmIp;
    /**最近更新时间*/
    private String lastUpdateTime;
    /**红色告警间隔*/
    private int redWarnPeriod;
    /**黄色告警间隔*/
    private int yellowWarnPeriod;
    /**心跳时间*/
    private long heartbeat;
    /**心跳状态标识*/
    private int heartStat;
    /**心跳间隔*/
    private int heartPeriod;
    /**数据状态标识*/
    private int dataStat;
}
