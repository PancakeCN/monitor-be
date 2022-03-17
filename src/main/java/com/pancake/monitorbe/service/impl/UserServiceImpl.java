package com.pancake.monitorbe.service.impl;

import com.pancake.monitorbe.controller.param.UserParam;
import com.pancake.monitorbe.dao.SysMapper;
import com.pancake.monitorbe.dao.UserSysMapper;
import com.pancake.monitorbe.entity.User;
import com.pancake.monitorbe.entity.UserSys;
import com.pancake.monitorbe.entity.UserToken;
import com.pancake.monitorbe.dao.UserMapper;
import com.pancake.monitorbe.dao.UserTokenMapper;
import com.pancake.monitorbe.model.UserResult;
import com.pancake.monitorbe.model.UserSysResult;
import com.pancake.monitorbe.service.UserService;
import com.pancake.monitorbe.util.NumberUtil;
import com.pancake.monitorbe.util.SystemUtil;
import com.sun.xml.internal.bind.v2.TODO;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.*;

/**
 * 业务层-用户-实现类
 *
 * @author PancakeCN
 * @link https://github.com/PancakeCN
 * @date 2022/3/7 17:38
 */
@Service
@Log4j2
public class UserServiceImpl implements UserService {

    @Resource
    private UserMapper userMapper;

    @Resource
    private UserTokenMapper userTokenMapper;

    @Resource
    private UserSysMapper userSysMapper;

    @Resource
    private SysMapper sysMapper;

    /**
     * 登录服务层实现（包括token）
     *
     * @param loginName 登录名
     * @param password 密码
     * @return java.lang.String
     * @author PancakeCN
     * @date 2022/3/12 14:54
     */
    @Override
    public String login(String loginName, String password) {
        User loginUser = userMapper.login(loginName, password);
        if (loginUser != null) {
            //登录后执行修改token操作
            String token = getNewToken(System.currentTimeMillis() + "", loginUser.getLoginName());
            UserToken userToken = userTokenMapper.selectByPrimaryKey(loginUser.getLoginName());
            //当前时间
            Date nowTime = new Date();
            //过期时间：48小时
            Date expireTime = new Date(nowTime.getTime() + 2 * 24 * 3600 * 1000);
            if (userToken == null){
                userToken = new UserToken();
                userToken.setLoginName(loginUser.getLoginName());
                userToken.setToken(token);
                userToken.setUpdateTime(nowTime);
                userToken.setExpireTime(expireTime);
                //新增一条token数据
                if (userTokenMapper.insertSelective(userToken)>0) {
                    return token;
                }
            }
        }

        return null;
    }

    @Override
    public ArrayList<UserParam> getUserListFull() {
        //获取原始部分userList
        ArrayList<UserResult> allUserList = userMapper.getAllUserList();
        //组装拼接（多个）
        return getUserParamList(allUserList);
    }

    @Override
    public UserParam getOneUserFullByLoginName(String loginName) {
        //获取原始部分user
        UserResult oneUser = userMapper.getOneUserByPrimaryKey(loginName);
        //组装拼接（单个）
        return getOneUserParam(oneUser);
    }

    @Override
    public int insertOneUserFull(UserParam userP) {
        boolean flag = false;
        HashMap<UserResult, ArrayList<UserSysResult>> splitRes = splitOneUserParam(userP);
        for (Map.Entry<UserResult, ArrayList<UserSysResult>> entry : splitRes.entrySet()) {
            if (entry.getValue() != null) {
                //当为普通用户时（ArrayList<UserSysResult>不为空）
                //检查表tb_sys中是否存在系统识别码
                if (checkIfExitsSysCode(entry.getValue())) {
                    flag = false;
                    break;
                }
                flag = userMapper.insertSelective(userResultToUser(entry.getKey())) > 0
                        && userSysMapper.insertBatch(userSysResultToUserSys(
                        entry.getValue(), entry.getKey().getLoginName())) > 0;
            }else {
                //当为管理员时（ArrayList<UserSysResult>为空）
                flag = userMapper.insertSelective(userResultToUser(entry.getKey())) > 0;
            }
        }
        if (flag){
            //插入成功
            return 1;
        }else {
            //插入失败
            return 0;
        }
    }


    @Override
    public int updateOneUserFull(UserParam userP) {
        //TODO 完善更新操作。注：更新tb_sys时会涉及删除操作。 同时删除tb_user_token中的值。
        //FIXME
        return 0;
    }

    @Override
    public int deleteOneUserFull(String loginName) {
        boolean flag = false;
        if (userTokenMapper.selectByPrimaryKey(loginName) != null) {
            flag = userMapper.deleteByPrimaryKey(loginName) > 0 &&
                    userSysMapper.deleteByPrimaryKey(loginName) > 0 &&
                    userTokenMapper.deleteByPrimaryKey(loginName) > 0;
        }else {
            flag = userMapper.deleteByPrimaryKey(loginName) > 0 &&
                    userSysMapper.deleteByPrimaryKey(loginName) > 0;
        }
        if (flag) {
            return 1;
        }else {
            return 0;
        }
    }

    /**
     * 检查tb_sys中是否存在传入的sysCode
     *
     * @param eachUserSys 每一个UserSysResult类
     * @return boolean
     * @author PancakeCN
     * @date 2022/3/18 0:30
     */
    private boolean checkIfExitsSysCode(ArrayList<UserSysResult> eachUserSys) {
        //检查tb_sys中是否存在这个sysCode
        for (UserSysResult usr : eachUserSys) {
            if (sysMapper.checkIfExitsSysCode(usr.getSysCode()) == null) {
                //存在，抛出错误
                log.error("数据库中不存在名为{}的sysCode，请重试。", usr.getSysCode());
                return true;
            }
        }
        //不存在
        return false;
    }

    /**
     * UserSysResult转UserSys类
     *
     * @param userSysR UserSysResult
     * @param loginName 登录名
     * @return java.util.ArrayList<com.pancake.monitorbe.entity.UserSys>
     * @author PancakeCN
     * @date 2022/3/17 20:05
     */
    private ArrayList<UserSys> userSysResultToUserSys(ArrayList<UserSysResult> userSysR, String loginName) {

        ArrayList<UserSys> userSys = new ArrayList<>();
        for (UserSysResult usr : userSysR) {
            userSys.add(new UserSys(loginName, usr.getSysCode()));
        }
        return userSys;
    }

    /**
     * UserResult转User类
     *
     * @param userR UserResult
     * @return com.pancake.monitorbe.entity.User
     * @author PancakeCN
     * @date 2022/3/17 2:19
     */
    private User userResultToUser(UserResult userR) {
        return new User(
                userR.getLoginName(), userR.getUsername(), userR.getPassword(),
                userR.getAuth(), userR.getPhoneNumber()
        );
    }

    /**
     * 将UserParam拆分为UserSysResult和UserResult
     *
     * @param userP 用户参数
     * @return HashMap<UserResult, ArrayList<UserSysResult>>
     * @author PancakeCN
     * @date 2022/3/17 1:44
     */
    private HashMap<UserResult, ArrayList<UserSysResult>> splitOneUserParam(UserParam userP) {
        HashMap<UserResult, ArrayList<UserSysResult>> res = new HashMap<>(1);
        if (userP.getAuth() == 1){
            //当为普通用户
            res.put(new UserResult(
                    userP.getLoginName(), userP.getUsername(), userP.getPassword(),
                    userP.getAuth(), userP.getAuthComment(), userP.getPhoneNumber()
            ), userP.getUserSys());
        }else {
            //当为管理员
            res.put(new UserResult(
                    userP.getLoginName(), userP.getUsername(), userP.getPassword(),
                    userP.getAuth(), userP.getAuthComment(), userP.getPhoneNumber()
            ), null);
        }
        return res;
    }

    /**
     * 拼接前端界面所需要的一个UserParam
     *
     * @param oneUser UserResult
     * @return com.pancake.monitorbe.controller.param.UserParam
     * @author PancakeCN
     * @date 2022/3/17 0:40
     */
    private UserParam getOneUserParam(UserResult oneUser) {
        return assembleUserParam(oneUser);
    }

    /**
     * 拼接前端界面所需要的UserParamList
     *
     * @param allUserList ArrayList<UserResult>
     * @return java.util.ArrayList<com.pancake.monitorbe.controller.param.UserParam>
     * @author PancakeCN
     * @date 2022/3/15 13:42
     */
    private ArrayList<UserParam> getUserParamList(ArrayList<UserResult> allUserList) {
        ArrayList<UserParam> userParamList = new ArrayList<>();
        for (UserResult eachUserR : allUserList) {
            UserParam eachUserParam = assembleUserParam(eachUserR);
            userParamList.add(eachUserParam);
        }
        return userParamList;
    }

    /**
     * 将UserSysResult和UserResult组装为UserParam
     *
     * @param userR UserResult
     * @return com.pancake.monitorbe.controller.param.UserParam
     * @author PancakeCN
     * @date 2022/3/17 0:50
     */
    private UserParam assembleUserParam(UserResult userR){
        UserParam userP;
        if (userR.getAuth() == 1) {
            //当为普通用户
            userP = new UserParam(
                    userR.getLoginName(), userR.getUsername(), userR.getPassword(),
                    userR.getAuth(), userR.getAuthComment(),
                    //获取一个用户的UserSysResult结果
                    userSysMapper.getAllUserSysListByLoginName(userR.getLoginName()),
                    userR.getPhoneNumber()
            );
        }else {
            //当为管理员
            userP = new UserParam(
                    userR.getLoginName(), userR.getUsername(), userR.getPassword(),
                    userR.getAuth(), userR.getAuthComment(),
                    //留空
                    null,
                    userR.getPhoneNumber()
            );
        }
        return userP;
    }

    /**
     * 获取Token值
     *
     * @param timeStr 时间
     * @param loginName 登录名
     * @return java.lang.String
     * @author PancakeCN
     * @date 2022/3/12 10:16
     */
    private String getNewToken(String timeStr, String loginName) {
        String src = timeStr + loginName + NumberUtil.genRandomNum(6);
        return SystemUtil.genToken(src);
    }
}
