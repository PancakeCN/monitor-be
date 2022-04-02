package com.pancake.monitorbe.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * 日更新统计
 *
 * @author PancakeCN
 * @link https://github.com/PancakeCN
 * @date 2022/3/12 17:14
 */
@NoArgsConstructor
@AllArgsConstructor
@Data
@ToString
public class UpdateDayCount {
    /**
     * 系统识别码
     */
    private String sysCode;
    /**
     * 终端识别码
     */
    private String tmCode;
    /**
     * 年
     */
    private String year;
    /**
     * 月
     */
    private String month;
    /**
     * 日
     */
    private String day;
    /**
     * 统计数
     */
    private String count;
}
