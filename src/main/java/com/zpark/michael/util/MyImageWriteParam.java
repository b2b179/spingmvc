/**
 * com.zpark.michael.util
 * MyImageWriteParam.java
 * @Author：michael.Y
 * @date:2015年4月26日
 * @version 1.0
 * @desctiption:TODO
 * Copyright (c) 2015, apac.yang@gmail.com All Rights Reserved. 
 */
package com.zpark.michael.util;

import java.util.Locale;

import javax.imageio.plugins.jpeg.JPEGImageWriteParam;

public class MyImageWriteParam extends JPEGImageWriteParam {
	
	public MyImageWriteParam(){
		super(Locale.getDefault());
	}
	/**
	 * @param locale
	 */
	public MyImageWriteParam(Locale locale) {
		super(locale);
	}
	/**
	 * 自定义压缩比
	 */
	public void setCompressionQuality(float quality){
		if(quality<0.0F || quality>1.0F){
			throw new IllegalArgumentException("Quality out-of-bounds");
		}
		this.compressionQuality =256-(quality*256);
	}
	
}
