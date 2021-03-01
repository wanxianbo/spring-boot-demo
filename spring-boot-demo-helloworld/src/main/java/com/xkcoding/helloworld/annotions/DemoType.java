package com.xkcoding.helloworld.annotions;

import java.lang.annotation.*;

/**
 * @author wanxianbo
 * @description Demo 类型注解
 * @date 2021/3/1 21:56
 */
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface DemoType {
    String value() default "";
}
