package com.pancake.monitorbe.service.impl;

import com.pancake.monitorbe.controller.param.SysParam;
import com.pancake.monitorbe.dao.SysMapper;
import com.pancake.monitorbe.entity.Sys;
import com.pancake.monitorbe.service.SysService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;

/**
 * 业务层-系统-实现类
 *
 * @author PancakeCN
 * @link https://github.com/PancakeCN
 * @date 2022/3/14 1:17
 */
@Service
public class SysServiceImpl implements SysService {

    @Resource
    SysMapper sysMapper;

    @Override
    public ArrayList<Sys> getAllSystemList() {
        return sysMapper.getAllSystemList();
    }

    @Override
    public int insertOneSys(SysParam sysP) {
        return sysMapper.insertSelective(sysParamToSys(sysP));
    }

    /**
     * SysParam转Sys
     *
     * @param sysP SysParam
     * @return com.pancake.monitorbe.entity.Sys
     * @author PancakeCN
     * @date 2022/3/21 13:00
     */
    private Sys sysParamToSys(SysParam sysP) {
        return new Sys(sysP.getSysCode(), sysP.getSysName(),
                sysP.getSysNameAbbr(), sysP.getTmCount());
    }
}
