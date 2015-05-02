/**
 * com.zpark.michael.dao.impl
 * GroupDAOImpl.java
 * @Author：michael.Y
 * @date:2015年4月26日
 * @version 1.0
 * @desctiption:TODO
 * Copyright (c) 2015, apac.yang@gmail.com All Rights Reserved. 
 */
package com.zpark.michael.dao.impl;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.zpark.michael.dao.IGroupDAO;
import com.zpark.michael.entity.GroupBean;
@Repository("groupDAO")
public class GroupDAOImpl implements IGroupDAO{

	/* (non-Javadoc)
	 * @see com.zpark.michael.dao.IBasicDAO#insertData(java.lang.Object)
	 */
	@Override
	public void insertData(GroupBean object) {
		this.getMapper().insertData(object);
	}

	/* (non-Javadoc)
	 * @see com.zpark.michael.dao.IBasicDAO#updateData(java.lang.Object)
	 */
	@Override
	public void updateData(GroupBean object) {
		this.getMapper().updateData(object);
	}

	/* (non-Javadoc)
	 * @see com.zpark.michael.dao.IBasicDAO#selectDataById(java.lang.Integer)
	 */
	@Override
	public GroupBean selectDataById(Integer pkId) {
		return this.getMapper().selectDataById(pkId);
	}

	/* (non-Javadoc)
	 * @see com.zpark.michael.dao.IBasicDAO#deleteDataById(java.lang.Integer)
	 */
	@Override
	public void deleteDataById(Integer pkId) {
		this.getMapper().deleteDataById(pkId);
	}

	/* (non-Javadoc)
	 * @see com.zpark.michael.dao.IBasicDAO#selectCountByName(java.lang.String)
	 */
	@Override
	public int selectCountByName(String search) {
		return this.getMapper().selectCountByName(search);
	}

	/* (non-Javadoc)
	 * @see com.zpark.michael.dao.IBasicDAO#selectCount()
	 */
	@Override
	public int selectCount() {
		return this.getMapper().selectCount();
	}

	/* (non-Javadoc)
	 * @see com.zpark.michael.dao.IBasicDAO#selectDataByName(int, int, java.lang.String)
	 */
	@Override
	public List<GroupBean> selectDataByName(int start, int end, String search) {
		return this.getMapper().selectDataByName(start, end, search);
	}

	/* (non-Javadoc)
	 * @see com.zpark.michael.dao.IBasicDAO#selectData(int, int)
	 */
	@Override
	public List<GroupBean> selectData(int start, int end) {
		return this.getMapper().selectData(start, end);
	}

	/* (non-Javadoc)
	 * @see com.zpark.michael.dao.IBasicDAO#selectAllData()
	 */
	@Override
	public List<GroupBean> selectAllData() {
		return this.getMapper().selectAllData();
	}
	
	private IGroupDAO getMapper(){
		return this.sessionTemplate.getMapper(IGroupDAO.class);
	}
	@Autowired
	private SqlSessionTemplate sessionTemplate;

}
