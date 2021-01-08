package com.project.spring.board.common;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import org.springframework.context.annotation.ComponentScan;

/*
 * 로그 어노테이션
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.CLASS)
@ComponentScan(basePackages="com.project.spring")
public @interface Logging {

}
