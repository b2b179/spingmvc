/**
 * com.zpark.michael.service
 * IGroupService.java
 * @Author：michael.Y
 * @date:2015年4月26日
 * @version 1.0
 * @desctiption:TODO
 * Copyright (c) 2015, apac.yang@gmail.com All Rights Reserved. 
 */
package com.zpark.michael.service;

import java.util.List;

import com.zpark.michael.entity.GroupBean;

public interface IGroupService extends IBasicService<GroupBean>{
	/**
	 * 查询所有的组别
	 * @return
	 */
	List<GroupBean> queryAll();

}
