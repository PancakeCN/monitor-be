package com.pancake.monitorbe.service.impl;

import com.pancake.monitorbe.controller.param.UserParam;
import com.pancake.monitorbe.dao.UserSysMapper;
import com.pancake.monitorbe.entity.User;
import com.pancake.monitorbe.entity.UserToken;
import com.pancake.monitorbe.dao.UserMapper;
import com.pancake.monitorbe.dao.UserTokenMapper;
import com.pancake.monitorbe.model.UserResult;
import com.pancake.monitorbe.service.UserService;
import com.pancake.monitorbe.util.NumberUtil;
import com.pancake.monitorbe.util.SystemUtil;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Date;

/**
 * 业务层-用户-实现类
 *
 * @author PancakeCN
 * @link https://github.com/PancakeCN
 * @date 2022/3/7 17:38
 */
@Service
public class UserServiceImpl implements UserService {

    @Resource
    private UserMapper userMapper;

    @Resource
    private UserTokenMapper userTokenMapper;

    @Resource
    private UserSysMapper userSysMapper;

    /**
     * 登录服务层实现（包括token）
     *
     * @param loginName
     * @param password
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
        //组装拼接
        return getUserParamList(allUserList);
    }

    @Override
    public UserParam getUserByLoginName(String loginName) {
        return null;
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
            UserParam eachUserParam;
            if (eachUserR.getAuth() == 1) {
                //当为普通用户
                eachUserParam = new UserParam(
                        eachUserR.getLoginName(),
                        eachUserR.getUsername(),
                        eachUserR.getPassword(),
                        eachUserR.getAuth(),
                        eachUserR.getAuthComment(),
                        userSysMapper.getAllUserSysListByLoginName(eachUserR.getLoginName()),
                        eachUserR.getPhoneNumber()
                );
            }else {
                //当为管理员
                eachUserParam = new UserParam(
                        eachUserR.getLoginName(),
                        eachUserR.getUsername(),
                        eachUserR.getPassword(),
                        eachUserR.getAuth(),
                        eachUserR.getAuthComment(),
                        null,
                        eachUserR.getPhoneNumber()
                );
            }
            userParamList.add(eachUserParam);
        }
        return userParamList;
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
