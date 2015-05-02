/**
 * com.zpark.michael.action
 * BasicAction.java
 * @Author：michael.Y
 * @date:2015年4月12日
 * @version 1.0
 * @desctiption:前端通用Action
 * Copyright (c) 2015, apac.yang@gmail.com All Rights Reserved. 
 */
package com.zpark.michael.action;

import java.io.BufferedWriter;
import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.zpark.michael.entity.PageBean;

public abstract class BasicAction<E>{
	
	Logger logger  = Logger.getLogger(getClass());
	/**
	 * 查询所有的数据
	 * @return
	 */
	@RequestMapping(value="/queryAll")
	public abstract String queryAll(ModelMap modelMap,PageBean<E> page,String search);
	/**
	 * 进入添加页面
	 * @return ADD_PAGE.JSP
	 */
	@RequestMapping(value="/addData",method=RequestMethod.GET)
	public abstract String addData(ModelMap modelMap);
	/**
	 * 添加一条新的数据
	 * @return 跳转到查询所有数据
	 */
	@RequestMapping(value="/addData",method=RequestMethod.POST)
	public abstract String addData( E data);
	/**
	 * 更新一条数据
	 * @return 添加到更新页面
	 */
	@RequestMapping(value="/updateData/{id:^[\\d]+$}",method=RequestMethod.GET)
	public abstract String updateData(@PathVariable Integer id,ModelMap modelMap);
	/**
	 * 更新一条数据
	 * @return 跳转到全局查询 
	 */
	@RequestMapping(value="/updateData",method=RequestMethod.POST)
	public abstract String updateData( E data);
	/**
	 * 批量删除一条数据
	 * AJAX
	 */
	@RequestMapping(value="/removeData",method=RequestMethod.POST)
	public abstract String removeData(ModelMap modelMap,Integer[] ids);
	/**
	 * 通过Ajax操作一条数据
	 * 
	 */
	@RequestMapping(value="/operactorByAjax",method=RequestMethod.GET)
	@ResponseBody
	public abstract void operactorByAjax(HttpServletRequest request,HttpServletResponse response);

	/**
	 * 输出 json 数据
	 * @param source
	 */
	protected void printJsonResult(Object source,HttpServletResponse response) throws IOException{
		ObjectMapper om  = new ObjectMapper();
		String mess = om.writeValueAsString(source);//将json对象转换为字符串   
		response.setContentType("text/html;charset=utf-8");
		BufferedWriter  out = new BufferedWriter(response.getWriter());
		out.write(mess);
		System.out.println("json mess:"+mess);
		out.flush();
		out.close();
	}

	/**
	 * 对应structs.xml 跳转结果
	 * 可以自己扩展
	 */
	protected enum ResultType{
		/**
		 * ResultType:ERROR
		 */
		ERROR,//操作错误
		/**
		 * ResultType:QUERY_LIST
		 */
		QUERY_LIST,//查询所有
		/**
		 * ResultType:ADD_PAGE
		 */
		ADD_PAGE,//新增成功
		/**
		 * ResultType:UPDATE_PAGE
		 */
		UPDATE_PAGE,//更新页面
	}
	/**
	 * 
	 * @param result
	 * @return String 跳转页面
	 */
	protected String getResultPage(String directory,ResultType result){
		return directory+result.name().toLowerCase();
	}
}
