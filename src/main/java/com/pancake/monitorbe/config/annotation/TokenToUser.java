package com.pancake.monitorbe.config.annotation;

import java.lang.annotation.*;

/**
 * 自定义用户注解
 *
 * @author PancakeCN
 * @link https://github.com/PancakeCN
 * @date 2022/4/22 21:20
 */
@Target(ElementType.PARAMETER)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface TokenToUser {

    /**
     * 当前用户在request中的名字
     *
     * @return
     */
    String value() default "user";
}
