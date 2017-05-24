package com.github.wenbo2018.jconf.client.annotation;

import java.lang.annotation.*;

/**
 * Created by wenbo.shen on 2017/5/24.
 */
@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Jcg {
    String value() default "";
}
