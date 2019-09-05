package com.code.repository.spring;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface Handler {

	/**
	 * handler名称
	 */
	String value();

	/**
	 * handler别名，避免同一个流程中，多个任务ID重复。
	 */
	String[] aliases() default {};

	/**
	 * handler处理描述
	 */
	String desc() default "";
	
}

