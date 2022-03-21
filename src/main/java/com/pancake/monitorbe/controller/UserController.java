package com.pancake.monitorbe.controller;

import com.pancake.monitorbe.controller.param.LoginParam;
import com.pancake.monitorbe.common.Constants;
import com.pancake.monitorbe.controller.param.UserParam;
import com.pancake.monitorbe.service.UserService;
import com.pancake.monitorbe.model.Result;
import com.pancake.monitorbe.util.ResultGenerator;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;

/**
 * 用户相关接口层
 *
 * @author PancakeCN
 * @date 2022/2/12 3:28
 */
@RestController
@Api(value = "v1", tags = "系统用户（User）相关接口")
@RequestMapping("/api/v1/user")
public class UserController {

    @Resource
    private UserService userService;

    private static final Logger logger = LoggerFactory.getLogger(UserController.class);

    @ApiOperation(value = "登录验证")
    @ApiImplicitParam(name = "loginParam", value = "登录参数", required = true,
            dataType = "com.pancake.monitorbe.controller.param.LoginParam")
    @PostMapping("/login")
    public Result<Object> login(@RequestBody @Valid LoginParam loginParam) {
        if (loginParam == null || !StringUtils.hasText(loginParam.getLoginName())
                || !StringUtils.hasText(loginParam.getPasswordMd5())) {
            return ResultGenerator.genFailResult("用户名或密码不能为空");
        }
        String loginResult = userService.login(loginParam.getLoginName(), loginParam.getPasswordMd5());
        logger.info("login api, loinName = {}, loginResult = {}", loginParam.getLoginName(), loginResult);
        //登录成功
        if (StringUtils.hasText(loginResult) && loginResult.length() == Constants.TOKEN_LENGTH) {
            Result result = ResultGenerator.genSuccessResult();
            result.setData(loginResult);
            return result;
        }
        //登录失败
        return ResultGenerator.genFailResult(loginResult);
    }


    @ApiOperation(value = "查询所有记录（筛选后）")
    @GetMapping("/getUserListFull")
    public Result<Object> getUserListFull(){
        return ResultGenerator.genSuccessResult(userService.getUserListFull());
    }


    @ApiOperation(value = "按照用户名模糊查询一条/多条记录")
    @ApiImplicitParam(name = "usernameIn", value = "所要查找的用户名", required = true, dataTypeClass = String.class)
    @GetMapping("/getUserListByUsernameFuzzy/{usernameIn}")
    public Result<Object> getUserListByUsernameFuzzy(@PathVariable String usernameIn) {
        if (usernameIn != null) {
            return ResultGenerator.genSuccessResult(userService.getUserListByUsernameFuzzy(usernameIn));
        }
        return ResultGenerator.genFailResult("接口调用失败！请确认请求参数。");
    }


    @ApiOperation(value = "按照登录名（loginName）筛选查询指定一条记录")
    @ApiImplicitParam(name = "loginName", value = "登录名", required = true, dataTypeClass = String.class)
    @GetMapping("/getUserByLoginName")
    public Result<Object> getUserByLoginName(@RequestParam String loginName){
        if (loginName != null){
            return ResultGenerator.genSuccessResult(userService.getOneUserFullByLoginName(loginName));
        }
        return ResultGenerator.genFailResult("接口调用失败！请确认请求参数。");
    }


    @ApiOperation(value = "新增一条记录", notes = "")
    @ApiImplicitParam(name = "userP", value = "登录参数", required = true,
            dataType = "com.pancake.monitorbe.controller.param.UserParam")
    @PostMapping("/insertOneUser")
    public Result<Object> insertOneUser(@RequestBody UserParam userP) {
        if (ObjectUtils.isEmpty(userP)){
            return ResultGenerator.genFailResult("接口调用失败！请确认请求参数。");
        }else {
            if (userService.insertOneUserFull(userP) > 0){
                return ResultGenerator.genSuccessResult("记录新增成功！");
            }
            return ResultGenerator.genFailResult("内部错误！记录新增失败！");
        }
    }


    @ApiOperation(value = "修改一条记录", notes = "")
    @ApiImplicitParam(name="userP", value = "用户参数", required = true,
            dataType = "com.pancake.monitorbe.controller.param.UserParam")
    @PutMapping("/updateOneUser")
    public Result<Object> updateOneUser(@RequestBody UserParam userP){
        if (ObjectUtils.isEmpty(userP)) {
            return ResultGenerator.genFailResult("接口调用失败！请确认请求参数。");
        }else {
            if (userService.updateOneUserFull(userP) > 0){
                return ResultGenerator.genSuccessResult("记录修改成功！");
            }
            return ResultGenerator.genFailResult("内部错误！记录修改失败！");
        }
    }


    @ApiOperation(value = "删除一条记录", notes = "")
    @ApiImplicitParam(name = "loginName", value = "用户名", required = true, dataTypeClass = String.class)
    @DeleteMapping("/deleteOneUser/{loginName}")
    public Result<Object> deleteOneUser(@PathVariable String loginName){
        if (!StringUtils.hasText(loginName)){
            return ResultGenerator.genFailResult("接口调用失败！请确认请求参数。");
        }else {
            if (userService.deleteOneUserFull(loginName) > 0){
                return ResultGenerator.genSuccessResult("记录删除成功！");
            }
            return ResultGenerator.genFailResult("内部错误！记录删除失败！");
        }
    }
}
