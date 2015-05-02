/**
 * com.zpark.michael.action
 * IndexAction.java
 * @Author：michael.Y
 * @date:2015年4月28日
 * @version 1.0
 * @desctiption:TODO
 * Copyright (c) 2015, apac.yang@gmail.com All Rights Reserved. 
 */
package com.zpark.michael.action;

import java.io.BufferedWriter;
import java.io.IOException;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.zpark.michael.entity.JsonBean;
import com.zpark.michael.quartz.CreateIndexQuartz;

@Controller
@RequestMapping("/index")
public class IndexAction {
	@RequestMapping(value="/all")
	public String index(){
		
		return "index";
	}
	@RequestMapping(value="/test")
	public String testImage(){
				return "image";
	}
	/**
	 * 更新所有索引
	 */
	@RequestMapping(value="/updateIndex")
	@ResponseBody
	public void createIndex(HttpServletResponse response){
		JsonBean json = new JsonBean();
		json.setName("createIndex");
		indexQuartz.createAllIndex();
		json.setResult(true);
		json.setCommand("createIndex");
		try {
			printJsonResult(json,response);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	/**
	 * 更新所有索引
	 */
	@RequestMapping(value="/deleteIndex")
	public void deleteIndex(HttpServletResponse response){
		JsonBean json = new JsonBean();
		json.setName("createIndex");
		indexQuartz.deleteAllIndex();
		json.setResult(true);
		json.setCommand("createIndex");
		try {
			printJsonResult(json,response);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
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
	@Autowired
	private CreateIndexQuartz indexQuartz;
}
