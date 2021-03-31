package com.zhang.practice.spring.importbean;

import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

/**
 * @author zhangzihao
 */
@Documented
@Inherited
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE, ElementType.FIELD, ElementType.METHOD, ElementType.PARAMETER})

public @interface Connector {

    String[] basePackages() default {};
}
