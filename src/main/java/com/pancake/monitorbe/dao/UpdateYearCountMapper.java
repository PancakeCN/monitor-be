package com.pancake.monitorbe.dao;

import com.pancake.monitorbe.entity.UpdateYearCount;

import java.util.List;

/**
 * 按照 年 更新统计
 *
 * @author PancakeCN
 * @link https://github.com/PancakeCN
 * @date 2022/3/31 21:28
 */
public interface UpdateYearCountMapper {
    /**
     * 通过主键筛选
     *
     * @param sysCodeIn
     * @param tmCodeIn
     * @return java.util.List<com.pancake.monitorbe.entity.UpdateYearCount>
     * @author PancakeCN
     * @date 2022/4/2 11:33
     */
    List<UpdateYearCount> getListByPrimaryKey(String sysCodeIn, String tmCodeIn);
}
