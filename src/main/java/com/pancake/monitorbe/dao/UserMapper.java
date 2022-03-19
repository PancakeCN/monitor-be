package com.pancake.monitorbe.dao;

import com.pancake.monitorbe.entity.User;
import com.pancake.monitorbe.model.UserResult;

import java.util.ArrayList;

/**
 * 用户表dao层
 * @author PancakeCN
 * @date 2022/2/12 2:31
 */
public interface UserMapper {
    /**
     * 登录验证
     *
     * @param loginName 登录名
     * @param password 密码
     * @return com.pancake.monitorbe.entity.User
     * @author PancakeCN
     * @date 2022/3/12 10:54
     */
    User login(String loginName, String password);

    /**
     * 查询所有用户（筛选后）
     *
     * @author PancakeCN
     * @date 2022/2/12 3:07
     * @return ArrayList<UserResult>
     */
    ArrayList<UserResult> getAllUserList();

    /**
     * 通过主键获取一条用户信息
     *
     * @param loginName 登录名
     * @return com.pancake.monitorbe.model.UserResult
     * @author PancakeCN
     * @date 2022/3/16 17:25
     */
    UserResult getOneUserByPrimaryKey(String loginName);

    /**
     * 获取所有登录名列表（当为普通用户）
     *
     * @return java.util.ArrayList<java.lang.String>
     * @author PancakeCN
     * @date 2022/3/15 1:52
     */
    ArrayList<String> getNormalLoginNameList();

    /**
     * 通过username模糊查询用户列表
     *
     * @param usernameIn 待查询的用户名
     * @return java.util.ArrayList<com.pancake.monitorbe.model.UserResult>
     * @author PancakeCN
     * @date 2022/3/18 16:37
     */
    ArrayList<UserResult> getUserListByUsernameFuzzy(String usernameIn);

    /**
     * 插入一个用户
     * @author PancakeCN
     * @date 2022/2/12 3:07
     * @param user User
     * @return int
     */
    int insert(User user);

    /**
     * 选择性插入一条用户数据
     *
     * @param user User
     * @return int
     * @author PancakeCN
     * @date 2022/3/16 16:51
     */
    int insertSelective(User user);

    /**
     * 通过主键修改一个用户
     * @author PancakeCN
     * @date 2022/2/12 3:09
     * @param user User
     * @return int
     **/
    int updateByPrimaryKey(User user);

    /**
     * 通过主键 选择性 修改一个用户
     *
     * @param user User
     * @return int
     * @author PancakeCN
     * @date 2022/3/16 17:14
     */
    int updateByPrimaryKeySelective(User user);

    /**
     * 通过主键删除一条用户数据
     *
     * @author PancakeCN
     * @date 2022/2/12 3:10
     * @param loginName String
     * @return int
     */
    int deleteByPrimaryKey(String loginName);
}
