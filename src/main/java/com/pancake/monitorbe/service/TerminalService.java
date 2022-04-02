package com.pancake.monitorbe.service;

import com.pancake.monitorbe.controller.param.TerminalParam;
import com.pancake.monitorbe.entity.Terminal;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

/**
 * 业务层-终端-接口
 *
 * @author PancakeCN
 * @link https://github.com/PancakeCN
 * @date 2022/3/14 1:18
 */
@Service
public interface TerminalService {

    /**
     * 获取所有终端列表
     *
     * @return java.util.ArrayList<com.pancake.monitorbe.entity.Terminal>
     * @author PancakeCN
     * @date 2022/3/15 16:09
     */
    ArrayList<Terminal> getAllTerminalList();

    /**
     * 按照终端名模糊查询终端列表
     *
     * @param tmNameIn 待查找的终端名
     * @return java.util.ArrayList<com.pancake.monitorbe.entity.Terminal>
     * @author PancakeCN
     * @date 2022/3/27 11:34
     */
    ArrayList<Terminal> getTmListByTmNameFuzzy(String tmNameIn);

    /**
     * 插入一条终端信息
     *
     * @param tmP 终端参数
     * @return java.lang.String
     * @author PancakeCN
     * @date 2022/3/27 15:00
     */
    String insertOneTerminal(TerminalParam tmP);

    /**
     * 更新一条终端信息
     *
     * @param tmP 终端参数
     * @return java.lang.String
     * @author PancakeCN
     * @date 2022/3/29 15:57
     */
    String updateOneTerminal(TerminalParam tmP);
    
    /**
     * 删除一条终端信息
     *
     * @param sysCodeIn 待删除的系统号
     * @param tmCodeIn 待删除的终端号
     * @return java.lang.String
     * @author PancakeCN
     * @date 2022/3/31 19:13
     */
    String deleteOneTerminal(String sysCodeIn, String tmCodeIn);
}
