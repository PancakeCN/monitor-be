package com.pancake.monitorbe.dao;

import com.pancake.monitorbe.entity.WorkInfo;

import java.util.List;

/**
 * 工作状态
 *
 * @author PancakeCN
 * @link https://github.com/PancakeCN
 * @date 2022/4/2 9:50
 */
public interface WorkinfoMapper {

    /**
     * 按照sysCode和tmCode两个字段查询
     *
     * @param sysCodeIn sysCode
     * @param tmCodeIn tmCode
     * @return java.util.List<com.pancake.monitorbe.entity.WorkInfo>
     * @author PancakeCN
     * @date 2022/4/2 10:05
     */
    List<WorkInfo> getListByPrimaryKey(String sysCodeIn, String tmCodeIn);
    /**
     * 按照sysCode字段查询
     *
     * @param sysCodeIn sysCode
     * @return java.util.List<com.pancake.monitorbe.entity.WorkInfo>
     * @author PancakeCN
     * @date 2022/4/2 10:07
     */
    List<WorkInfo> getListBySysCode(String sysCodeIn);
}
