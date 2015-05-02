/**
 * com.zpark.michael.service.impl
 * IUserService.java
 * @Author：michael.Y
 * @date:2015年4月25日
 * @version 1.0
 * @desctiption:TODO
 * Copyright (c) 2015, apac.yang@gmail.com All Rights Reserved. 
 */
package com.zpark.michael.service;

import java.util.Map;

import com.zpark.michael.entity.PageBean;
import com.zpark.michael.entity.UserBean;

public interface IUserService extends IBasicService<UserBean>{

	/**
	 * 通过名称模糊查询
	 * @param page
	 * @param pageSize
	 * @param search
	 * @return PageBean<UserBean> 
	 */
	PageBean<UserBean> queryAllByNameWithIndex(int page, int pageSize,String search);
	/**
	 * 多条件模糊查询
	 * @param page
	 * @param pageSize
	 * @param condition
	 * @return PageBean<UserBean> 
	 */
	PageBean<UserBean> queryAllByNameWithIndex(int page, int pageSize,Map<String,String> condition);
	/**
	 * 通过组别名称模糊查询
	 * @param page
	 * @param pageSize
	 * @param groupName
	 * @return PageBean<UserBean> 
	 */
	PageBean<UserBean> queryAllByGroupName(int page, int pageSize,String groupName);
	/**
	 * 通过组别ID模糊查询
	 * @param page
	 * @param pageSize
	 * @param groupName
	 * @return PageBean<UserBean> 
	 */
	PageBean<UserBean> queryAllByGroupId(int page, int pageSize,Integer groupId);
	

}
