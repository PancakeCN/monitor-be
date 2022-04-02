package com.pancake.monitorbe.dao;

import com.pancake.monitorbe.entity.UpdateMonthCount;

import java.util.List;

/**
 * 按照 月份 更新统计
 *
 * @author PancakeCN
 * @link https://github.com/PancakeCN
 * @date 2022/3/31 21:27
 */
public interface UpdateMonthCountMapper {
    /**
     * 通过主键筛选
     *
     * @param sysCodeIn
     * @param tmCodeIn
     * @return java.util.List<com.pancake.monitorbe.entity.UpdateMonthCount>
     * @author PancakeCN
     * @date 2022/4/2 11:32
     */
    List<UpdateMonthCount> getListByPrimaryKey(String sysCodeIn, String tmCodeIn);
}
