package com.pancake.monitorbe.dao;

import com.pancake.monitorbe.controller.param.MonitorTerminalParam;
import com.pancake.monitorbe.entity.Terminal;

import java.util.ArrayList;
import java.util.List;

/**
 * @author PancakeCN
 * @date 2022/3/2 1:52
 */
public interface TerminalMapper {
    /**
     * 查询所有终端
     *
     * @author PancakeCN
     * @date 2022/3/2 1:54
     * @return java.util.List<com.pancake.monitorbe.entity.Terminal>
     */
    List<Terminal> getAllTerminals();

    /**
     * 查询所有终端表（双表联合查询）
     *
     * @author PancakeCN
     * @date 2022/3/2 10:26
     * @return java.util.List<com.pancake.monitorbe.entity.Terminal>
     */
    List<MonitorTerminalParam> getMonitorTerminals();

    Integer getTerminalsCount();

    Integer getGreenHeartStatCount();

    Integer getYellowHeartStatCount();

    Integer getRedHeartStatCount();

    Integer getGreenDataStatCount();

    Integer getYellowDataStatCount();

    Integer getRedDataStatCount();

    /**
     * 通过sysCode查找终端列表
     *
     * @param sysCodeIn  待查询的sysCode
     * @return java.util.ArrayList<com.pancake.monitorbe.entity.Terminal>
     * @author PancakeCN
     * @date 2022/3/23 23:58
     */
    ArrayList<Terminal> getTerminalListBySysCode(String sysCodeIn);

    /**
     * 通过终端名模糊查询终端列表
     *
     * @param tmNameIn 待查询的终端名
     * @return java.util.ArrayList<com.pancake.monitorbe.entity.Terminal>
     * @author PancakeCN
     * @date 2022/3/27 11:54
     */
    ArrayList<Terminal> getTmListByTmNameFuzzy(String tmNameIn);

    /**
     * 新增一条终端记录
     *
     * @param tm 待新增的终端
     * @return int
     * @author PancakeCN
     * @date 2022/3/28 17:23
     */
    int insertSelective(Terminal tm);

    int updateByPrimaryKeySelective(Terminal tm);

    int deleteByPrimaryKey(String tmCode);
}
