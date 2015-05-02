/**
 * com.zpark.michael.service.impl
 * GroupServiceImpl.java
 * @Author：michael.Y
 * @date:2015年4月26日
 * @version 1.0
 * @desctiption:TODO
 * Copyright (c) 2015, apac.yang@gmail.com All Rights Reserved. 
 */
package com.zpark.michael.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zpark.michael.dao.IGroupDAO;
import com.zpark.michael.entity.GroupBean;
import com.zpark.michael.entity.PageBean;
import com.zpark.michael.service.IGroupService;
import com.zpark.michael.util.CommonMethodUtil;
@Service("groupService")
public class GroupServiceImpl implements IGroupService{

	/* (non-Javadoc)
	 * @see com.zpark.michael.service.IBasicService#addData(java.lang.Object)
	 */
	@Override
	public boolean addData(GroupBean group) {
		try {
			if(group !=null)this.groupDAO.insertData(group);
			return true;
		} catch (Exception e) {
			logger.error(e.getMessage());
			e.printStackTrace();
		}
		return false;
	}

	/* (non-Javadoc)
	 * @see com.zpark.michael.service.IBasicService#modifyData(java.lang.Object)
	 */
	@Override
	public boolean modifyData(GroupBean data) {
		try {
			if(data !=null)this.groupDAO.updateData(data);
			return true;
		} catch (Exception e) {
			logger.error(e.getMessage());
			e.printStackTrace();
		}
		return false;
	}

	/* (non-Javadoc)
	 * @see com.zpark.michael.service.IBasicService#removeData(java.lang.Object)
	 */
	@Override
	public boolean removeData(GroupBean data) {
		try {
			if(data !=null)this.groupDAO.deleteDataById(data.getGroupId());
			return true;
		} catch (Exception e) {
			logger.error(e.getMessage());
			e.printStackTrace();
		}
		return false;
	}

	/* (non-Javadoc)
	 * @see com.zpark.michael.service.IBasicService#queryByPK(java.lang.Integer)
	 */
	@Override
	public GroupBean queryByPK(Integer pkId) {
		try {
			return this.groupDAO.selectDataById(pkId);
		} catch (Exception e) {
			logger.error(e.getMessage());
			e.printStackTrace();
		}
		return null;
	}

	/* (non-Javadoc)
	 * @see com.zpark.michael.service.IBasicService#queryAll(int, int)
	 */
	@Override
	public PageBean<GroupBean> queryAll(int page, int pageSize) {
		PageBean<GroupBean> pb = new PageBean<GroupBean>();
		pb.setPageSize(pageSize);
		pb.setPage(page);
		try{
			int start = page>1 ? (page-1)*pageSize :0;
			int end  = page>1 ? page*pageSize :pageSize;
			List<GroupBean> data = this.groupDAO.selectData(start, end);
			int count  = this.groupDAO.selectCount();
			pb.setCountData(count);
			CommonMethodUtil.autoPage(pb);
			pb.setData(data);
			data =null;
		}catch(Exception e){
			logger.error(e.getMessage());
			e.printStackTrace();
		}
		return pb;
	}

	/* (non-Javadoc)
	 * @see com.zpark.michael.service.IBasicService#queryAll(int, int, java.lang.String)
	 */
	@Override
	public PageBean<GroupBean> queryAll(int page, int pageSize, String searchVal) {
		PageBean<GroupBean> pb = new PageBean<GroupBean>();
		pb.setPageSize(pageSize);
		pb.setPage(page);
		try{
			int start = page>1 ? (page-1)*pageSize :0;
			int end  = page>1 ? page*pageSize :pageSize;
			searchVal = searchVal!=null && !"".equals(searchVal) ? "%"+searchVal+"%" : "%";
			List<GroupBean> data = this.groupDAO.selectDataByName(start, end, searchVal);
			int count  = this.groupDAO.selectCountByName(searchVal);
			pb.setCountData(count);
			CommonMethodUtil.autoPage(pb);
			pb.setData(data);
			data =null;
		}catch(Exception e){
			logger.error(e.getMessage());
			e.printStackTrace();
		}
		return pb;
	}

	/* (non-Javadoc)
	 * @see com.zpark.michael.service.IGroupService#queryAll()
	 */
	@Override
	public List<GroupBean> queryAll() {
		try{
			return this.groupDAO.selectAllData();
		}catch(Exception e){
			logger.error(e.getMessage());
			e.printStackTrace();
		}
		return new ArrayList<GroupBean>();
	}
	
	@Autowired
	private IGroupDAO groupDAO;
	private Logger logger = Logger.getLogger(getClass());
}
