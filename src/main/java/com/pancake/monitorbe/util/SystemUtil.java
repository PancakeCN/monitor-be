package com.pancake.monitorbe.util;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * 系统工具类
 *
 * @author PancakeCN
 * @link https://github.com/PancakeCN
 * @date 2022/3/12 10:30
 */
public class SystemUtil {

    private SystemUtil(){
    }

    /**
     * 登录或注册成功后,生成保持用户登录状态会话token值
     *
     * @param src 为用户最新一次登录时的now()+user.id+random()
     * @return java.lang.String
     * @author PancakeCN
     * @date 2022/3/12 10:50
     */
    public static String genToken(String src){
        if (src == null || "".equals(src)){
            return null;
        }
        MessageDigest md5 = null;
        try {
            md5 = MessageDigest.getInstance("MD5");
            md5.update(src.getBytes());
            String result = new BigInteger(1, md5.digest()).toString(16);
            if (result.length() == 31){
                result = result + "-";
            }
            System.out.println("current new token is:"+result);
            return result;
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            return null;
        }

    }

    /**
     * 计算分页查询的初始偏移值
     *
     * @param pageIndex 第几页
     * @param pageNum 每页条数
     * @return java.lang.Integer
     * @author PancakeCN
     * @date 2022/5/3 23:47
     */
    public static Integer genPageOffset(Integer pageIndex, Integer pageNum) {
        return (pageIndex - 1) * pageNum;
    }

}
