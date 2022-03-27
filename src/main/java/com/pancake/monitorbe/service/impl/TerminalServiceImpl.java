package com.pancake.monitorbe.service.impl;

import com.pancake.monitorbe.controller.param.TerminalParam;
import com.pancake.monitorbe.dao.SysMapper;
import com.pancake.monitorbe.dao.TerminalMapper;
import com.pancake.monitorbe.entity.Terminal;
import com.pancake.monitorbe.service.TerminalService;
import com.pancake.monitorbe.util.ErrorMsgException;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.util.ArrayList;

/**
 * 业务层-终端-实现类
 *
 * @author PancakeCN
 * @link https://github.com/PancakeCN
 * @date 2022/3/14 1:19
 */
@Service
@Log4j2
public class TerminalServiceImpl implements TerminalService {

    @Resource
    TerminalMapper terminalMapper;

    @Resource
    SysMapper sysMapper;

    @Override
    public ArrayList<Terminal> getAllTerminalList() {
        ArrayList<Terminal> tmList = null;
        try {
            tmList = (ArrayList<Terminal>) terminalMapper.getAllTerminals();
        } catch (Exception e) {
            log.debug("获取所有终端列表时出现错误：{}", e.getMessage());
            throw new ErrorMsgException("获取所有终端列表时出现错误！");
        }
        return tmList;
    }

    @Override
    public ArrayList<Terminal> getTmListByTmNameFuzzy(String tmNameIn) {
        ArrayList<Terminal> tmList = null;
        if (StringUtils.hasText(tmNameIn)) {
            try {
                tmList = terminalMapper.getTmListByTmNameFuzzy(tmNameIn);
            } catch (Exception e) {
                log.debug("按照终端名模糊查询终端列表出错：{}", e.getMessage());
                throw new ErrorMsgException("按照终端名模糊查询终端列表出错！");
            }
        } else {
            log.debug("传入的参数不正确：tmNameIn = {}", tmNameIn);
            throw new ErrorMsgException("传入的参数不正确");
        }
        return tmList;
    }

    @Override
    public String insertOneTerminal(TerminalParam tmP) {
        String retMsg = null;
        if (ObjectUtils.isEmpty(tmP)) {
            Terminal tm = terminalParamToTerminal(tmP);
            try {
                //TODO 待修改：1.需判断tb_sys中是否存在待插入的sysName信息；若不存在，则抛出错误。 2.同时，应该在tb_sys的tm_count字段中加一。
            } catch (Exception e) {
                log.debug("插入一条终端列表出错：{}", e.getMessage());
                throw new ErrorMsgException("插入一条终端列表出错！");
            }
        } else {
            log.debug("传入的参数不正确：tmNameIn = {}", tmP);
            throw new ErrorMsgException("传入的参数不正确！");
        }
        return retMsg;
    }

    /**
     * TerminalParam转Terminal
     *
     * @param tmP 终端参数
     * @return com.pancake.monitorbe.entity.Terminal
     * @author PancakeCN
     * @date 2022/3/27 16:07
     */
    private Terminal terminalParamToTerminal(TerminalParam tmP) {
        return new Terminal(
                tmP.getSysCode(), tmP.getTmCode(), tmP.getTmName(),
                tmP.getTmIp(), tmP.getLastUpdateTime(), tmP.getRedWarnPeriod(),
                tmP.getYellowWarnPeriod(), tmP.getHeartStat(), tmP.getHeartPeriod(), tmP.getDataStat()
        );
    }
}
