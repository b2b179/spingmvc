/**
 * com.zpark.michael.dao
 * UserDAOImpl.java
 * @Author：michael.Y
 * @date:2015年4月25日
 * @version 1.0
 * @desctiption:TODO
 * Copyright (c) 2015, apac.yang@gmail.com All Rights Reserved. 
 */
package com.zpark.michael.dao.impl;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.zpark.michael.dao.IUserDAO;
import com.zpark.michael.entity.UserBean;
@Repository("userDAO")
public class UserDAOImpl implements IUserDAO{

	/* (non-Javadoc)
	 * @see com.zpark.michael.dao.IUserDAO#insertUser(com.zpark.michael.entity.UserBean)
	 */
	@Override
	public void insertUser(UserBean object) {
		this.sessionTemplate.getMapper(IUserDAO.class).insertUser(object);
	}

	/* (non-Javadoc)
	 * @see com.zpark.michael.dao.IUserDAO#updateUser(com.zpark.michael.entity.UserBean)
	 */
	@Override
	public void updateUser(UserBean object) {
		this.sessionTemplate.getMapper(IUserDAO.class).updateUser(object);
	}
	
	/* (non-Javadoc)
	 * @see com.zpark.michael.dao.IUserDAO#deleteUserById(java.lang.Integer)
	 */
	@Override
	public void deleteUserById(Integer userId) {
		this.sessionTemplate.getMapper(IUserDAO.class).deleteUserById(userId);
	}

	/* (non-Javadoc)
	 * @see com.zpark.michael.dao.IUserDAO#selectUserById(java.lang.Integer)
	 */
	@Override
	public UserBean selectUserById(Integer userId) {
		return this.sessionTemplate.getMapper(IUserDAO.class).selectUserById(userId);
	}
	/* (non-Javadoc)
	 * @see com.zpark.michael.dao.IUserDAO#selectCountByUserName(java.lang.String)
	 */
	@Override
	public int selectCountByUserName(String userName) {
		return this.sessionTemplate.getMapper(IUserDAO.class).selectCountByUserName(userName);
	}

	/* (non-Javadoc)
	 * @see com.zpark.michael.dao.IUserDAO#selectCountByGroupName(java.lang.String)
	 */
	@Override
	public int selectCountByGroupName(String groupName) {
		return this.sessionTemplate.getMapper(IUserDAO.class).selectCountByGroupName(groupName);
	}

	/* (non-Javadoc)
	 * @see com.zpark.michael.dao.IUserDAO#selectCountByGroupId(java.lang.Integer)
	 */
	@Override
	public int selectCountByGroupId(Integer groupId) {
		return this.sessionTemplate.getMapper(IUserDAO.class).selectCountByGroupId(groupId);
	}

	/* (non-Javadoc)
	 * @see com.zpark.michael.dao.IUserDAO#selectUser(int, int)
	 */
	@Override
	public List<UserBean> selectUser(int page, int pageSize) {
		return this.sessionTemplate.getMapper(IUserDAO.class).selectUser(page, pageSize);
	}

	/* (non-Javadoc)
	 * @see com.zpark.michael.dao.IUserDAO#selectUserByUserName(int, int, java.lang.String)
	 */
	@Override
	public List<UserBean> selectUserByUserName(int page, int pageSize,
			String userName) {
		return this.sessionTemplate.getMapper(IUserDAO.class).selectUserByUserName(page,pageSize,userName);
	}

	/* (non-Javadoc)
	 * @see com.zpark.michael.dao.IUserDAO#selectUserByGroupName(int, int, java.lang.String)
	 */
	@Override
	public List<UserBean> selectUserByGroupName(int page, int pageSize,
			String groupName) {
		return this.sessionTemplate.getMapper(IUserDAO.class).selectUserByGroupName(page,pageSize,groupName);
	}

	/* (non-Javadoc)
	 * @see com.zpark.michael.dao.IUserDAO#selectUserByGroupId(int, int, java.lang.Integer)
	 */
	@Override
	public List<UserBean> selectUserByGroupId(int page, int pageSize,Integer groupId) {
		return this.sessionTemplate.getMapper(IUserDAO.class).selectUserByGroupId(page,pageSize,groupId);
	}
	/* (non-Javadoc)
	 * @see com.zpark.michael.dao.IUserDAO#selectCount()
	 */
	@Override
	public int selectCount() {
		return this.sessionTemplate.getMapper(IUserDAO.class).selectCount();
	}
	@Autowired
	private SqlSessionTemplate sessionTemplate;
	
}
