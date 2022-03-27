package com.pancake.monitorbe.service.impl;

import com.pancake.monitorbe.controller.param.SysParam;
import com.pancake.monitorbe.dao.SysMapper;
import com.pancake.monitorbe.dao.TerminalMapper;
import com.pancake.monitorbe.dao.UserSysMapper;
import com.pancake.monitorbe.entity.Sys;
import com.pancake.monitorbe.service.SysService;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

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
@Log4j2
public class SysServiceImpl implements SysService {

    @Resource
    SysMapper sysMapper;

    @Resource
    TerminalMapper terminalMapper;

    @Resource
    UserSysMapper userSysMapper;

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
        //初始化创建系统，所属的终端总数应当默认为0
        sysP.setTmCount(0);
        return sysMapper.insertSelective(sysParamToSys(sysP));
    }

    @Override
    public int updateOneSys(SysParam sysP) {
        return sysMapper.updateOneSystemSelective(sysParamToSys(sysP));
    }

    @Override
    public int deleteOneSys(String sysCodeIn) {
        boolean flag = false;
        if (!ObjectUtils.isEmpty(terminalMapper.getTerminalListBySysCode(sysCodeIn)) ||
                !ObjectUtils.isEmpty(userSysMapper.getUserSysListBySysCode(sysCodeIn))) {
            log.error("删除失败！终端表 或 用户-系统映射表 中已存在此系统记录值，请删除这两个表中数据后重试。");
            return 0;
        }
        flag = sysMapper.deleteOneSystem(sysCodeIn) > 0;
        if (flag) {
            return 1;
        }else {
            return 0;
        }
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
