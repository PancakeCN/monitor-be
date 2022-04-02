package com.pancake.monitorbe.dao;

import com.pancake.monitorbe.entity.UpdateDayCount;

import java.util.List;

/**
 * 按照 天 更新统计
 *
 * @author PancakeCN
 * @link https://github.com/PancakeCN
 * @date 2022/3/31 21:24
 */
public interface UpdateDayCountMapper {
    /**
     * 通过主键筛选
     *
     * @param sysCodeIn
     * @param tmCodeIn
     * @return java.util.List<com.pancake.monitorbe.entity.UpdateDayCount>
     * @author PancakeCN
     * @date 2022/4/2 11:30
     */
    List<UpdateDayCount> getListByPrimaryKey(String sysCodeIn, String tmCodeIn);
}
