package com.albert.toolkit.db.annotation;

import com.albert.toolkit.db.CommonConstants;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 通过该注解指定数据源
 */
@Target({ElementType.METHOD, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface DbSource {
    String value() default CommonConstants.DATASOURCE_SAKILA;
}
