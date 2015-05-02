/**
 * com.zpark.michael.dao
 * IUserDAO.java
 * @Author：michael.Y
 * @date:2015年4月24日
 * @version 1.0
 * @desctiption:TODO
 * Copyright (c) 2015, apac.yang@gmail.com All Rights Reserved. 
 */
package com.zpark.michael.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.zpark.michael.entity.UserBean;

public interface IUserDAO{
	
	/**
	 * 新增一条记录
	 * @param object
	 */
	void insertUser(UserBean object);
	/**
	 * 更新一条记录
	 * @param object
	 */
	void updateUser(UserBean object);
	/**
	 * 根据主键查询一条记录
	 * @param Integer userId
	 * @return UserBean
	 */
	UserBean selectUserById(@Param("userId") Integer userId);
	/**
	 * 删除一条记录
	 * @param UserBean object
	 */
	 void deleteUserById(@Param("userId") Integer userId);
	 /**
	  * 获取总记录数
	  * @return int
	  */
	 int selectCountByUserName(@Param("userName") String userName);
	 
	 /**
	  * 获取总记录数
	  * @return int
	  */
	 int selectCountByGroupName(@Param("groupName") String groupName);
	 
	 /**
	  * 获取总记录数
	  * @return int
	  */
	 int selectCountByGroupId(@Param("groupId") Integer groupId);
	 
	 /**
	  * 根据页数查询数据
	  * @param page
	  * @param pageSize
	  * @return
	  */
	 List<UserBean> selectUser(@Param("start")int start, @Param("end")int end);
	 
	 /**
	  * 根据姓名模糊查询数据
	  * @param page
	  * @param pageSize
	  * @param userName
	  * @return  List<UserBean> 
	  */
	 List<UserBean> selectUserByUserName(@Param("start")int start, @Param("end")int end,@Param("userName")String userName);
	 
	 /**
	  * 根据组别名称模糊查询数据
	  * @param page
	  * @param pageSize
	  * @param groupName
	  * @return  List<UserBean> 
	  */
	 List<UserBean> selectUserByGroupName(@Param("start")int start, @Param("end")int end,@Param("groupName") String groupName);
	 
	 /**
	  * 根据组别ID精确查询数据
	  * @param page
	  * @param pageSize
	  * @param groupName
	  * @return  List<UserBean> 
	  */
	 List<UserBean> selectUserByGroupId(@Param("start")int start, @Param("end")int end,@Param("groupId") Integer groupId);
	/**
	 * @return 获取所有的记录
	 */
	int selectCount();
	 
}
