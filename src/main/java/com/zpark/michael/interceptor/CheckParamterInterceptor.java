/**
 * com.zpark.michael.filter
 * CheckRightInterceptor.java
 * @Author：michael.Y
 * @date:2015年4月3日
 * @version 1.0
 * @desctiption:方法权限验证拦截器
 * Copyright (c) 2015, apac.yang@gmail.com All Rights Reserved. 
 */
package com.zpark.michael.interceptor;

import java.net.URI;
import java.net.URLDecoder;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;


public class CheckParamterInterceptor  extends HandlerInterceptorAdapter  {

	@Override
	public boolean preHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler) throws Exception {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		System.out.println("-----------------CheckParamterInterceptor---------------");
		String search = request.getParameter("search");
		if(search!=null && !"".equals(search)){
			search = URLDecoder.decode(search, "UTF-8");
		}
		return true;
	}
}
