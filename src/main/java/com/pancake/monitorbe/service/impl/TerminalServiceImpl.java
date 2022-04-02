package com.pancake.monitorbe.service.impl;

import com.pancake.monitorbe.controller.param.TerminalParam;
import com.pancake.monitorbe.dao.*;
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

    @Resource
    WorkinfoMapper workinfoMapper;

    @Resource
    UpdateHourCountMapper updateHourCountMapper;

    @Resource
    UpdateDayCountMapper updateDayCountMapper;

    @Resource
    UpdateMonthCountMapper updateMonthCountMapper;

    @Resource
    UpdateYearCountMapper updateYearCountMapper;

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
        if (!ObjectUtils.isEmpty(tmP)) {
            Terminal tm = terminalParamToTerminal(tmP);
            try {
                //判断tb_sys中是否存在待插入的sysCode信息；若不存在，则抛出错误。
                if (sysMapper.checkIfExitsSysCode(tm.getSysCode()) != null) {
                    if (terminalMapper.insertSelective(tm) > 0) {
                        retMsg = "新增一条终端记录成功！";
                    }
                } else {
                    log.debug("表tb_sys中不存在待插入的sysCode信息！sysCode = {}", tm.getSysCode());
                    throw new ErrorMsgException("表tb_sys中不存在待插入的sysCode信息！");
                }
            } catch (Exception e) {
                log.debug("插入一条终端列表出错：{}", e.getMessage());
                throw new ErrorMsgException("插入一条终端列表出错！");
            }
        } else {
            log.debug("传入的参数不正确：tmP = {}", tmP);
            throw new ErrorMsgException("传入的参数不正确！");
        }
        return retMsg;
    }

    @Override
    public String updateOneTerminal(TerminalParam tmP) {
        String retMsg = null;
        if (!ObjectUtils.isEmpty(tmP)) {
            Terminal tm = terminalParamToTerminal(tmP);
            try {
                if (terminalMapper.updateByPrimaryKeySelective(tm) > 0) {
                    retMsg = "更新一条终端记录成功！";
                }
            } catch (Exception e) {
                log.debug("更新一条终端列表出错：{}", e.getMessage());
                throw new ErrorMsgException("插入一条终端列表出错！");
            }
        } else {
            log.debug("传入的参数不正确：tmP = {}", tmP);
            throw new ErrorMsgException("传入的参数不正确！");
        }
        return retMsg;
    }

    @Override
    public String deleteOneTerminal(String sysCodeIn, String tmCodeIn) {
        String retMsg = null;
        if (StringUtils.hasText(sysCodeIn) && StringUtils.hasText(tmCodeIn)) {
            try {
                if (sysMapper.checkIfExitsSysCode(sysCodeIn) != null) {
                    //TODO 删除操作时，还应当考虑tb_workinfo tb_update_hour_count tb_update_day_count tb_update_month_count tb_update_year_count中是否存在tmCode
                    //删除终端逻辑待完善
                    if (workinfoMapper.getListByPrimaryKey(sysCodeIn ,tmCodeIn) == null && !checkIfExistsUpdateCount(sysCodeIn, tmCodeIn)) {
                        terminalMapper.deleteByPrimaryKey(tmCodeIn);
                    } else {
                        log.debug("表tb_workinfo tb_update_hour_count tb_update_day_count tb_update_month_count tb_update_year_count已存在记录！sysCode = {}, tmCode = {}", sysCodeIn, tmCodeIn);
                        throw new ErrorMsgException("表tb_sys中不存在待删除的sysCode信息！");
                    }
                } else {
                    log.debug("表tb_sys中不存在待删除的tmCode信息！tmCode = {}", tmCodeIn);
                    throw new ErrorMsgException("表tb_sys中不存在待删除的sysCode信息！");
                }
            } catch (Exception e) {
                log.debug("删除一条终端列表出错：{}", e.getMessage());
                throw new ErrorMsgException("删除一条终端列表出错！");
            }
        } else {
            log.debug("传入的参数不正确：sysCodeIn = {}, tmCodeIn = {}", sysCodeIn, tmCodeIn);
            throw new ErrorMsgException("传入的参数不正确！");
        }
        return retMsg;
    }

    /**
     * 检查tb_update_hour_count tb_update_day_count tb_update_month_count tb_update_year_count中是否存在记录
     *
     * @param sysCodeIn
     * @param tmCodeIn
     * @return boolean
     * @author PancakeCN
     * @date 2022/4/2 11:28
     */
    public boolean checkIfExistsUpdateCount(String sysCodeIn, String tmCodeIn) {
        if (updateHourCountMapper.getListByPrimaryKey(sysCodeIn, tmCodeIn) != null &&
            updateDayCountMapper.getListByPrimaryKey(sysCodeIn, tmCodeIn) != null &&
            updateMonthCountMapper.getListByPrimaryKey(sysCodeIn, tmCodeIn) != null &&
            updateYearCountMapper.getListByPrimaryKey(sysCodeIn, tmCodeIn) != null) {
            return true;
        }
        return false;
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
