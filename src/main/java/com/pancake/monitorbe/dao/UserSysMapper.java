package com.pancake.monitorbe.dao;

import com.pancake.monitorbe.entity.UserSys;
import com.pancake.monitorbe.model.UserSysResult;
import org.apache.ibatis.annotations.Param;

import java.util.ArrayList;

/**
 * 用户-系统映射
 *
 * @author PancakeCN
 * @link https://github.com/PancakeCN
 * @date 2022/3/14 18:21
 */
public interface UserSysMapper {

    /**
     * 通过loginName查询sysCode
     *
     * @param loginName 登录名
     * @return java.util.ArrayList<java.lang.String>
     * @author PancakeCN
     * @date 2022/3/14 18:46
     */
    ArrayList<UserSysResult> getAllUserSysListByLoginName(String loginName);

    /**
     * 选择性批量插入数据
     *
     * @param list 待插入数据列表
     * @return int
     * @author PancakeCN
     * @date 2022/3/17 19:35
     */
    int insertBatch(@Param("list") ArrayList<UserSys> list);



    /**
     * 通过主键删除一条记录
     *
     * @param loginName 登录名
     * @return int
     * @author PancakeCN
     * @date 2022/3/18 1:41
     */
    int deleteByPrimaryKey(String loginName);
}
