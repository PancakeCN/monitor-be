package com.pancake.monitorbe.controller;

import com.pancake.monitorbe.controller.param.SysParam;
import com.pancake.monitorbe.model.Result;
import com.pancake.monitorbe.service.SysService;
import com.pancake.monitorbe.util.ResultGenerator;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * 系统相关接口层
 *
 * @author PancakeCN
 * @link https://github.com/PancakeCN
 * @date 2022/3/14 2:49
 */
@Api(value = "v1", tags = "系统（System）相关接口")
@RestController
@RequestMapping("/api/v1/sys")
public class SysController {

    private static final Logger logger = LoggerFactory.getLogger(SysController.class);

    @Resource
    SysService sysService;

    @ApiOperation(value = "获取所有系统列表")
    @GetMapping("/getAllSystemList")
    public Result<Object> getAllSystemList(){
        return ResultGenerator.genSuccessResult(sysService.getAllSystemList());
    }

    @ApiOperation(value = "按照系统名（sysName）模糊查询系统列表")
    @GetMapping("/")
    public Result<Object> getSysListBySysNameFuzzy(@RequestParam String sysNameIn) {
        if (sysNameIn != null) {
            return ResultGenerator.genSuccessResult(sysService.getSysListBySysNameFuzzy(sysNameIn));
        }
        return ResultGenerator.genFailResult("接口调用失败！请确认请求参数。");
    }

    @ApiOperation(value = "增加一条系统记录")
    @PostMapping("/insertOneSys")
    public Result<Object> insertOneSys(@RequestBody SysParam sysP) {
        if (ObjectUtils.isEmpty(sysP)) {
            return ResultGenerator.genFailResult("接口调用失败！请确认请求参数。");
        }else {
            if (sysService.insertOneSys(sysP) > 0) {
                return ResultGenerator.genSuccessResult("记录新增成功！");
            }else {
                return ResultGenerator.genFailResult("内部错误！记录新增失败！");
            }
        }
    }

    @ApiOperation(value = "修改一条系统记录")
    @PutMapping("/updateOneSys")
    public Result<Object> updateOneSys(@RequestBody SysParam sysP) {
        if (ObjectUtils.isEmpty(sysP)) {
            return ResultGenerator.genFailResult("接口调用失败！请确认请求参数。");
        }else {
            if (sysService.updateOneSys(sysP) > 0) {
                return ResultGenerator.genSuccessResult("记录修改成功！");
            }else {
                return ResultGenerator.genFailResult("内部错误！记录新增失败！");
            }
        }
    }

    @ApiOperation(value = "删除一条系统记录")
    @DeleteMapping("/deleteOneSys")
    public Result<Object> deleteOneSys(@RequestParam String sysCodeIn) {
        if (!StringUtils.hasText(sysCodeIn)){
            return ResultGenerator.genFailResult("接口调用失败！请确认请求参数。");
        }else {
            if (sysService.deleteOneSys(sysCodeIn) > 0){
                return ResultGenerator.genSuccessResult("记录删除成功！");
            }
            return ResultGenerator.genFailResult("内部错误！记录删除失败！");
        }
    }

}
