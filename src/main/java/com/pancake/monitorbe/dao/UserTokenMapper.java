package com.pancake.monitorbe.dao;

import com.pancake.monitorbe.entity.UserToken;

/**
 * 用户token dao层
 * @author PancakeCN
 * @link https://github.com/PancakeCN
 * @date 2022/3/7 21:11
 */
public interface UserTokenMapper {
    /**
     * 按照主键获取token表的所有值
     *
     * @param loginName
     * @return com.pancake.monitorbe.entity.UserToken
     * @author PancakeCN
     * @date 2022/3/12 11:22
     */
    UserToken selectByPrimaryKey(String loginName);

    /**
     * 选择性插入token
     *
     * @param userToken
     * @return boolean
     * @author PancakeCN
     * @date 2022/3/12 11:45
     */
    int insertSelective(UserToken userToken);



    /**
     * 通过主键删除一条记录
     *
     * @param loginName 登录名
     * @return int
     * @author PancakeCN
     * @date 2022/3/18 1:46
     */
    int deleteByPrimaryKey(String loginName);
}
