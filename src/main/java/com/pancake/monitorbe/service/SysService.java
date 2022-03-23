package com.pancake.monitorbe.service;

import com.pancake.monitorbe.controller.param.SysParam;
import com.pancake.monitorbe.entity.Sys;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

/**
 * 业务层-系统-接口
 *
 * @author PancakeCN
 * @link https://github.com/PancakeCN
 * @date 2022/3/14 1:16
 */
@Service
public interface SysService {

    /**
     * 业务层-获取所有终端
     *
     * @return java.util.ArrayList<com.pancake.monitorbe.entity.Sys>
     * @author PancakeCN
     * @date 2022/3/14 16:01
     */
    ArrayList<Sys> getAllSystemList();

    /**
     * 通过系统名模糊查找系统
     *
     * @param sysNameIn 待查找的系统名
     * @return java.util.ArrayList<com.pancake.monitorbe.entity.Sys>
     * @author PancakeCN
     * @date 2022/3/22 16:05
     */
    ArrayList<Sys> getSysListBySysNameFuzzy(String sysNameIn);


    /**
     * 新增一个系统
     *
     * @param sysP 系统参数
     * @return int
     * @author PancakeCN
     * @date 2022/3/21 12:50
     */
    int insertOneSys(SysParam sysP);

    /**
     * 修改一个系统
     *
     * @param sysP 系统参数
     * @return boolean
     * @author PancakeCN
     * @date 2022/3/22 17:24
     */
    int updateOneSys(SysParam sysP);

    /**
     * 按照系统识别码删除一条系统记录
     *
     * @param sysCodeIn 待删除的系统识别码
     * @return int
     * @author PancakeCN
     * @date 2022/3/23 21:16
     */
    int deleteOneSys(String sysCodeIn);
}
