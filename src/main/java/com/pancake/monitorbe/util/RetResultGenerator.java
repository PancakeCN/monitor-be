package com.pancake.monitorbe.util;

import com.pancake.monitorbe.model.RetResult;
import lombok.extern.log4j.Log4j2;
import org.springframework.util.StringUtils;

/**
 * 响应结果生成工具
 *
 * @author PancakeCN
 * @link https://github.com/PancakeCN
 * @date 2022/3/7 19:19
 */
@Log4j2
public class RetResultGenerator {
    private static final String DEFAULT_SUCCESS_MESSAGE = "SUCCESS";
    private static final String DEFAULT_FAIL_MESSAGE = "FAIL";
    private static final int RESULT_CODE_SUCCESS = 200;
    private static final int RESULT_CODE_SERVER_ERROR = 500;

    /**返回成功信息*/
    public static <T> RetResult<T> genSuccessResult() {
        RetResult<T> retResult = new RetResult<>();
        retResult.setResultCode(RESULT_CODE_SUCCESS);
        retResult.setMessage(DEFAULT_SUCCESS_MESSAGE);
        log.info("Success. The result is:"+ retResult);
        return retResult;
    }

    /**返回成功信息 重载1*/
    public static <T> RetResult<T> genSuccessResult(String message) {
        RetResult<T> retResult = new RetResult<>();
        retResult.setResultCode(RESULT_CODE_SUCCESS);
        retResult.setMessage(message);
        log.info("Success. The result is:"+ retResult);
        return retResult;
    }

    /**返回成功信息 重载2*/
    public static <T> RetResult<T> genSuccessResult(T data) {
        RetResult<T> retResult = new RetResult<>();
        retResult.setResultCode(RESULT_CODE_SUCCESS);
        retResult.setMessage(DEFAULT_SUCCESS_MESSAGE);
        retResult.setData(data);
        log.info("Success. The result is:"+ retResult);
        return retResult;
    }
    /**返回失败信息*/
    public static <T> RetResult<T> genFailResult() {
        RetResult<T> retResult = new RetResult<>();
        retResult.setResultCode(RESULT_CODE_SERVER_ERROR);
        retResult.setMessage(DEFAULT_FAIL_MESSAGE);
        log.info("Failed. The result is:"+ retResult);
        return retResult;
    }
    /**返回失败信息 重载1*/
    public static <T> RetResult<T> genFailResult(String message) {
        RetResult<T> retResult = new RetResult<>();
        retResult.setResultCode(RESULT_CODE_SERVER_ERROR);
        retResult.setMessage(message);
        log.info("Failed. The result is:"+ retResult);
        return retResult;
    }
    /**返回自定义类型信息*/
    public static <T> RetResult<T> genResult(int code, String message){
        RetResult<T> retResult = new RetResult<>();
        retResult.setResultCode(code);
        retResult.setMessage(message);
        log.info("genResult. The result is:"+ retResult);
        return retResult;
    }
    /**返回自定义类型信息 重载1*/
    public static <T> RetResult<T> genResult(int code, String message, T data) {
        RetResult<T> retResult = new RetResult<>();
        retResult.setResultCode(code);
        retResult.setMessage(message);
        retResult.setData(data);
        log.info("genResult. The result is:"+ retResult);
        return retResult;
    }
}
