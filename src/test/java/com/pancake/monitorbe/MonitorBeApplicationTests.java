package com.pancake.monitorbe;

import com.pancake.monitorbe.dao.*;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;

@SpringBootTest
class MonitorBeApplicationTests {

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

    @Test
    void sqlTest() {
        System.out.println(workinfoMapper.getListByPrimaryKey("110", "001"));
        System.out.println(updateHourCountMapper.getListByPrimaryKey("110", "001"));
        System.out.println(updateDayCountMapper.getListByPrimaryKey("110", "001"));
        System.out.println(updateMonthCountMapper.getListByPrimaryKey("110", "001"));
        System.out.println(updateYearCountMapper.getListByPrimaryKey("110", "001"));
    }

}
