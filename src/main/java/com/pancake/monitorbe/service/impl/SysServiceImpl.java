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
    public ArrayList<Sys> getSysListBySysNameFuzzy(String sysNameIn) {
        return sysMapper.getSysListBySysNameFuzzy(sysNameIn);
    }

    @Override
    public int insertOneSys(SysParam sysP) {
        //初始化时候，tmCount为0
        sysP.setTmCount(0);
        return sysMapper.insertSelective(sysParamToSys(sysP));
    }

    @Override
    public int updateOneSys(SysParam sysP) {
        return sysMapper.updateOneSystemSelective(sysParamToSys(sysP));
    }

    @Override
    public int deleteOneSys(String sysCodeIn) {
        //TODO 删除系统待完善。需要多多个表进行操作！
        return 0;
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
                //初始化创建系统，所属的终端总数应当默认为0
                sysP.getSysNameAbbr(), sysP.getTmCount());
    }
}
