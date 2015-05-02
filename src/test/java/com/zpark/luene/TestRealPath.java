/**
 * com.zpark.luene
 * TestRealPath.java
 * @Author：michael.Y
 * @date:2015年4月30日
 * @version 1.0
 * @desctiption:获取当前文件的物理地址
 * Copyright (c) 2015, apac.yang@gmail.com All Rights Reserved. 
 */
package com.zpark.luene;

import org.junit.Test;

public class TestRealPath {
	
	@Test
	public void testRealPath0(){
		String realPath = TestRealPath.class.getClassLoader().getResource("").getPath();
		realPath = realPath.substring(1);
		System.out.println(realPath);
		System.out.println(ClassLoader.class.getResource("").getPath());
	}
	public static void main(String[] args) {
		System.out.println(TestRealPath.class.getClassLoader().getResource("").getPath());
	}
}
