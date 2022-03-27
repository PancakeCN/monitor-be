package com.pancake.monitorbe.util;

/**
 * 自定义异常错误类
 *
 * @author PancakeCN
 * @link https://github.com/PancakeCN
 * @date 2022/3/26 21:10
 */
public class ErrorMsgException extends RuntimeException{
    public ErrorMsgException(String errorInfo) {
        super(errorInfo);
    }
}
