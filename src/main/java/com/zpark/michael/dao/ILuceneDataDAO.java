/**
 * com.zpark.michael.service
 * ISearchLuenceData.java
 * @Author：michael.Y
 * @date:2015年4月30日
 * @version 1.0
 * @desctiption:通过索引查询所有的信息
 * Copyright (c) 2015, apac.yang@gmail.com All Rights Reserved. 
 */
package com.zpark.michael.dao;

import java.util.Map;

import com.zpark.michael.entity.GroupBean;
import com.zpark.michael.entity.PageBean;
import com.zpark.michael.entity.UserBean;

public interface ILuceneDataDAO {
	
	PageBean<UserBean> queryAllUser(int page,int pageSize,Map<String,String> condition)throws Exception;
	
	PageBean<UserBean> queryAllUser(int page,int pageSize,String search) throws Exception;
	
	PageBean<GroupBean> queryAllGroup(int page,int pageSize,Map<String,String> condition)throws Exception;
	
	PageBean<GroupBean> queryAllGroup(int page,int pageSize,String search) throws Exception;
}
