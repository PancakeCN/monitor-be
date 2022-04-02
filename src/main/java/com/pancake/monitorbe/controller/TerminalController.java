package com.pancake.monitorbe.controller;

import com.pancake.monitorbe.controller.param.TerminalParam;
import com.pancake.monitorbe.entity.Terminal;
import com.pancake.monitorbe.model.RetResult;
import com.pancake.monitorbe.service.TerminalService;
import com.pancake.monitorbe.util.ErrorMsgException;
import com.pancake.monitorbe.util.RetResultGenerator;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.log4j.Log4j2;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.ArrayList;

/**
 * @author PancakeCN
 * @link https://github.com/PancakeCN
 * @date 2022/3/14 2:50
 */
@RestController
@Api(value = "v1", tags = "终端（Terminal）相关接口")
@RequestMapping("/api/v1/tm")
@Log4j2
public class TerminalController {

    @Resource
    TerminalService terminalService;

    @ApiOperation("获取所有终端信息")
    @GetMapping("/getAllTerminalList")
    public RetResult<ArrayList<Terminal>> getAllTerminalList() {
        ArrayList<Terminal> tmList = null;
        try {
            tmList = terminalService.getAllTerminalList();
        } catch (ErrorMsgException e) {
            log.error("获取终端信息时错误：{}", e.getMessage());
            return RetResultGenerator.genFailResult(e.getMessage());
        } catch (Exception e) {
            log.error("抛出异常：{}", e.getMessage());
            return RetResultGenerator.genFailResult(e.getMessage());
        }
        return RetResultGenerator.genSuccessResult(tmList);
    }

    @ApiOperation(value = "按照终端名（tmName）模糊查询系统列表")
    @GetMapping("/getTmListByTmNameFuzzy")
    public RetResult<ArrayList<Terminal>> getTmListByTmNameFuzzy(@RequestParam String tmNameIn) {
        ArrayList<Terminal> tmList = null;
        try {
            tmList = terminalService.getTmListByTmNameFuzzy(tmNameIn);
        } catch (ErrorMsgException e) {
            log.error("按照终端名（tmName）模糊查询错误：{}", e.getMessage());
            return RetResultGenerator.genFailResult(e.getMessage());
        } catch (Exception e) {
            log.error("抛出异常：{}", e.getMessage());
            return RetResultGenerator.genFailResult(e.getMessage());
        }
        return RetResultGenerator.genSuccessResult(tmList);
        //return RetResultGenerator.genFailResult("接口调用失败！请确认请求参数。");
    }

    @ApiOperation(value = "增加一条终端记录")
    @PostMapping("/insertOneTerminal")
    public RetResult<String> insertOneTerminal(@RequestBody TerminalParam tmP) {
        String retMsg;
        try {
            retMsg = terminalService.insertOneTerminal(tmP);
        } catch (ErrorMsgException e) {
            log.error("增加一条终端记录过程中错误：{}", e.getMessage());
            return RetResultGenerator.genFailResult(e.getMessage());
        } catch (Exception e) {
            log.error("抛出异常：{}", e.getMessage());
            return RetResultGenerator.genFailResult(e.getMessage());
        }
        return RetResultGenerator.genSuccessResult(retMsg);
    }

    @ApiOperation(value = "修改一条终端记录")
    @PutMapping("/updateOneTerminal")
    public RetResult<String> updateOneTerminal(@RequestBody TerminalParam tmP) {
        String retMsg;
        try {
            retMsg = terminalService.updateOneTerminal(tmP);
        } catch (ErrorMsgException e) {
            log.error("修改一条终端记录过程中错误：{}", e.getMessage());
            return RetResultGenerator.genFailResult(e.getMessage());
        } catch (Exception e) {
            log.error("抛出异常：{}", e.getMessage());
            return RetResultGenerator.genFailResult(e.getMessage());
        }
        return RetResultGenerator.genSuccessResult(retMsg);
    }

    @ApiOperation(value = "删除一条终端记录")
    @DeleteMapping("/deleteOneTerminal")
    public RetResult<String> deleteOneTerminal(@RequestParam String sysCodeIn, @RequestParam String tmCodeIn) {
        String retMsg;
        try {
            retMsg = terminalService.deleteOneTerminal(sysCodeIn, tmCodeIn);
        } catch (ErrorMsgException e) {
            log.error("删除一条终端记录过程中错误：{}", e.getMessage());
            return RetResultGenerator.genFailResult(e.getMessage());
        } catch (Exception e) {
            log.error("抛出异常：{}", e.getMessage());
            return RetResultGenerator.genFailResult(e.getMessage());
        }
        return RetResultGenerator.genSuccessResult(retMsg);
    }
}
