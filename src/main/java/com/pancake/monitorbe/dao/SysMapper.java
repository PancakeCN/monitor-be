package com.pancake.monitorbe.dao;

import com.pancake.monitorbe.entity.Sys;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

/**
 * @author PancakeCN
 * @date 2022/3/2 2:03
 */
public interface SysMapper {

    /**
     * 获取所有系统列表
     *
     * @return java.util.ArrayList<com.pancake.monitorbe.entity.Sys>
     * @author PancakeCN
     * @date 2022/3/14 15:56
     */
    ArrayList<Sys> getAllSystemList();

    /**
     * 通过系统名模糊查找系统
     *
     * @param sysNameIn 待查找的系统名
     * @return java.util.ArrayList<com.pancake.monitorbe.entity.Sys>
     * @author PancakeCN
     * @date 2022/3/22 16:08
     */
    ArrayList<Sys> getSysListBySysNameFuzzy(String sysNameIn);

    /**
     * 检查是否存在某一 sysCode（用于新建/修改用户信息UserParam）
     *
     * @param sysCode 系统识别码
     * @return java.lang.String
     * @author PancakeCN
     * @date 2022/3/17 23:19
     */
    String checkIfExitsSysCode(String sysCode);

    /**
     * 选择性插入一条数据
     *
     * @param sysIn Sys
     * @return int
     * @author PancakeCN
     * @date 2022/3/21 13:02
     */
    int insertSelective(Sys sysIn);

    /**
     * 更新一条数据
     *
     * @param sysIn Sys
     * @return int
     * @author PancakeCN
     * @date 2022/3/23 20:28
     */
    int updateOneSystemSelective(Sys sysIn);

    /**
     * 删除一个系统
     *
     * @param sysCodeIn 待删除的sysCode
     * @return int
     * @author PancakeCN
     * @date 2022/3/23 23:52
     */
    int deleteOneSystem(String sysCodeIn);
}
