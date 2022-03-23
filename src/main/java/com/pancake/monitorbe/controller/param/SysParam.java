package com.pancake.monitorbe.controller.param;

import lombok.Data;

import java.io.Serializable;

/**
 * 系统参数
 *
 * @author PancakeCN
 * @link https://github.com/PancakeCN
 * @date 2022/3/14 2:44
 */
@Data
public class SysParam implements Serializable {
    /**系统识别码*/
    private String sysCode;
    /**系统名*/
    private String sysName;
    /**系统名缩写*/
    private String sysNameAbbr;
    /**终端数*/
    private int tmCount;
}
