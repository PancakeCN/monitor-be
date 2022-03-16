package com.pancake.monitorbe.service;

import com.pancake.monitorbe.controller.param.UserParam;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

/**
 * 业务层-用户-接口
 *
 * @author PancakeCN
 * @date 2022/2/23 17:52
 */
@Service
public interface UserService {

    /**
     * 登录验证
     *
     * @param loginName
     * @param password
     * @return java.lang.String
     * @author PancakeCN
     * @date 2022/3/14 1:27
     */
    String login(String loginName, String password);

    /**
     * 获取所有用户列表（筛选后）
     *
     * @return java.util.ArrayList<com.pancake.monitorbe.controller.param.UserParam>
     * @author PancakeCN
     * @date 2022/3/14 13:19
     */
    ArrayList<UserParam> getUserListFull();

    /**
     * 按照登录名（loginName）筛选查询指定一条记录
     *
     * @param loginName
     * @return com.pancake.monitorbe.controller.param.UserParam
     * @author PancakeCN
     * @date 2022/3/16 17:05
     */
    UserParam getUserByLoginName(String loginName);
}
