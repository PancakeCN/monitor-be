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

    /**
     * 查询所有记录
     * @author PancakeCN
     * @date 2022/2/21 10:40
     * @return java.util.List<com.pancake.monitorbe.entity.User>
     */
    @ApiOperation(value = "查询所有记录（筛选后）")
    @GetMapping("/getUserListFull")
    public Result<Object> getUserListFull(){
        return ResultGenerator.genSuccessResult(userService.getUserListFull());
    }

    @ApiOperation(value = "按照登录名（loginName）筛选查询指定一条记录")
    @GetMapping("/getUserByLoginName")
    public Result<Object> getUserByLoginName(@RequestParam String loginName){
        if (loginName != null){
            return ResultGenerator.genSuccessResult(userService.getOneUserFullByLoginName(loginName));
        }
        return ResultGenerator.genFailResult("接口调用失败！请确认请求参数。");
    }


    /**
     * 新增一条记录
     *
     * @author PancakeCN
     * @date 2022/2/21 10:42
     * @param userP String 用户
     * @return java.lang.Boolean
     */
    @ApiOperation(value = "新增一条记录", notes = "")
    @ApiImplicitParam(name = "user", value = "用户", required = true, dataType = "com.pancake.monitorbe.entity.User")
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

//    /**
//     * 修改一条记录
//     * @author PancakeCN
//     * @date 2022/2/21 11:13
//     * @param user String 用户
//     * @return java.lang.Boolean
//     */
//    @ApiOperation(value = "修改一条记录", notes = "")
//    @ApiImplicitParams({
//            @ApiImplicitParam(name="user", value = "用户", required = true, dataType = "com.pancake.monitorbe.entity.User")
//    })
//    @PutMapping("/update")
//    public Boolean update(@RequestBody User user){
//        if (ObjectUtils.isEmpty(user)) {
//            return false;
//        }
//        return userService.updateUser(user) > 0;
//    }
//
//    /**
//     * 删除一条记录
//     *
//     * @author PancakeCN
//     * @date 2022/2/21 11:20
//     * @param loginName String 登录名
//     * @return java.lang.Boolean
//     */
//    @DeleteMapping("/delete/{loginName}")
//    public Boolean delete(@PathVariable String loginName){
//        if (!StringUtils.hasText(loginName)){
//            return false;
//        }
//        return userService.deleteUser(loginName) > 0;
//    }
}
