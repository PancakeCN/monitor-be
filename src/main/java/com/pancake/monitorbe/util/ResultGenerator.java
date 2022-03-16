package com.pancake.monitorbe.util;

import com.pancake.monitorbe.model.Result;
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
public class ResultGenerator {
    private static final String DEFAULT_SUCCESS_MESSAGE = "SUCCESS";
    private static final String DEFAULT_FAIL_MESSAGE = "FAIL";
    private static final int RESULT_CODE_SUCCESS = 200;
    private static final int RESULT_CODE_SERVER_ERROR = 500;

    /**返回成功信息*/
    public static Result<Object> genSuccessResult() {
        Result<Object> result = new Result<>();
        result.setResultCode(RESULT_CODE_SUCCESS);
        result.setMessage(DEFAULT_SUCCESS_MESSAGE);
        log.info("Success. The result is:"+result);
        return result;
    }

    /**返回成功信息 重载1*/
    public static Result<Object> genSuccessResult(String message) {
        Result<Object> result = new Result<>();
        result.setResultCode(RESULT_CODE_SUCCESS);
        result.setMessage(message);
        log.info("Success. The result is:"+result);
        return result;
    }

    /**返回成功信息 重载2*/
    public static Result<Object> genSuccessResult(Object data) {
        Result<Object> result = new Result<>();
        result.setResultCode(RESULT_CODE_SUCCESS);
        result.setMessage(DEFAULT_SUCCESS_MESSAGE);
        result.setData(data);
        log.info("Success. The result is:"+result);
        return result;
    }
    /**返回失败信息*/
    public static Result<Object> genFailResult(String message) {
        Result<Object> result = new Result<>();
        result.setResultCode(RESULT_CODE_SERVER_ERROR);
        if (!StringUtils.hasText(message)) {
            result.setMessage(DEFAULT_FAIL_MESSAGE);
        }else {
            result.setMessage(message);
        }
        log.info("Failed. The result is:"+result);
        return result;
    }
    /**返回错误信息*/
    public static Result<Object> genErrorResult(int code, String message){
        Result<Object> result = new Result<>();
        result.setResultCode(code);
        result.setMessage(message);
        log.info("Error. The result is:"+result);
        return result;
    }
}
