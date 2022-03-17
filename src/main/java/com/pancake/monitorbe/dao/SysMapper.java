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
     * 检查是否存在某一 sysCode（用于新建/修改用户信息UserParam）
     *
     * @param sysCode 系统识别码
     * @return java.lang.String
     * @author PancakeCN
     * @date 2022/3/17 23:19
     */
    String checkIfExitsSysCode(String sysCode);

    int insertOneSystem();

    int updateOneSystem();

    int deleteOneSystem();
}
