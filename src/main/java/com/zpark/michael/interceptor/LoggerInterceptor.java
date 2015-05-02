/**
 * com.zpark.michael.interceptor
 * LoggerInterceptor.java
 * @Author：michael.Y
 * @date:2015年4月3日
 * @version 1.0
 * @desctiption:日志切入
 * Copyright (c) 2015, apac.yang@gmail.com All Rights Reserved. 
 */
package com.zpark.michael.interceptor;

import java.lang.reflect.Method;

import org.apache.log4j.Logger;
import org.springframework.cglib.proxy.InvocationHandler;

public class LoggerInterceptor implements InvocationHandler {
	private Logger logger  = Logger.getLogger(this.getClass());
	@Override
	public Object invoke(Object object, Method method, Object[] args)
			throws Throwable {
		logger.info(" the method ");
		Object result  = method.invoke(object, args);
		return result;
	}

}
