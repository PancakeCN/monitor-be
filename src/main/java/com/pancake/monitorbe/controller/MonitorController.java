package com.pancake.monitorbe.controller;

import com.pancake.monitorbe.model.RetResult;
import com.pancake.monitorbe.service.MonitorService;
import com.pancake.monitorbe.util.RetResultGenerator;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * 控制层-监控主页
 *
 * @author PancakeCN
 * @date 2022/3/2 0:28
 */
@RestController
@Api(value = "v1", tags = "监控主页（Monitor）相关接口")
@RequestMapping("/api/v1/monitor")
public class MonitorController {

    @Resource
    private MonitorService monitorService;

    @ApiOperation(value = "获取所有终端信息")
    @GetMapping("/getAllTerminals")
    public RetResult<Object> getAllTerminals(){
        return RetResultGenerator.genSuccessResult(monitorService.getAllTerminalList());
    }

    @ApiOperation(value = "获取监控主页所需的终端信息")
    @GetMapping("/getMonitorTerminals")
    public RetResult<Object> getMonitorTerminals(){
        return RetResultGenerator.genSuccessResult(monitorService.getMonitorTerminalList());
    }

    @ApiOperation(value = "获取所有系统信息")
    @GetMapping("/getAllSystems")
    public RetResult<Object> getAllSystems(){
        return RetResultGenerator.genSuccessResult(monitorService.getAllSystemList());
    }

    @ApiOperation(value = "获取终端总数")
    @GetMapping("/getTerminalsCount")
    public RetResult<Object> getTerminalsCount(){
        return RetResultGenerator.genSuccessResult(monitorService.getTerminalsCount());
    }

    @ApiOperation(value = "获取心跳状态统计数")
    @GetMapping("/getHeartStatCount")
    public RetResult<Object> getHeartStatCount(){
        return RetResultGenerator.genSuccessResult(monitorService.getHeartStatCount());
    }

    @ApiOperation(value = "获取数据状态统计数")
    @GetMapping("/getDataStatCount")
    public RetResult<Object> getDataStatCount(){
        return RetResultGenerator.genSuccessResult(monitorService.getDataStatCount());
    }

}
