package com.pancake.monitorbe.dao;

import com.pancake.monitorbe.entity.UpdateHourCount;

import java.util.List;

/**
 * 按照 小时 更新统计
 *
 * @author PancakeCN
 * @link https://github.com/PancakeCN
 * @date 2022/3/31 21:25
 */
public interface UpdateHourCountMapper {
    /**
     * 按照主键筛选
     *
     * @param sysCodeIn
     * @param tmCodeIn
     * @return java.util.List<com.pancake.monitorbe.entity.UpdateHourCount>
     * @author PancakeCN
     * @date 2022/4/2 11:31
     */
    List<UpdateHourCount> getListByPrimaryKey(String sysCodeIn, String tmCodeIn);
}
