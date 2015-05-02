/**
 * com.zpark.michael
 * DateConverter.java
 * @Author：michael.Y
 * @date:2015年4月28日
 * @version 1.0
 * @desctiption:TODO
 * Copyright (c) 2015, apac.yang@gmail.com All Rights Reserved. 
 */
package com.zpark.michael.converter;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.core.convert.converter.Converter;
/**
 * 日期转换函数
 */
public class DateConverter implements Converter<String, Date> {
	public Date convert(String str) {
		System.out.println("str:"+str);
		Date parse=null;
		try {
			SimpleDateFormat sf=new SimpleDateFormat("yyyy-MM-dd");
			parse = sf.parse(str);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return parse;
	}
}
