/**
 * com.zpark.michael.service.impl
 * UserServiceImpl.java
 * @Author：michael.Y
 * @date:2015年4月25日
 * @version 1.0
 * @desctiption:TODO
 * Copyright (c) 2015, apac.yang@gmail.com All Rights Reserved. 
 */
package com.zpark.michael.service.impl;

import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zpark.michael.dao.ILuceneDataDAO;
import com.zpark.michael.dao.IUserDAO;
import com.zpark.michael.entity.PageBean;
import com.zpark.michael.entity.UserBean;
import com.zpark.michael.service.IUserService;
import com.zpark.michael.util.CommonMethodUtil;
@Service("userService")
public class UserServiceImpl implements IUserService{

	/* (non-Javadoc)
	 * @see com.zpark.michael.service.IBasicService#addData(java.lang.Object)
	 */
	@Override
	public boolean addData(UserBean user) {
		try {
			if(user !=null)this.userDAO.insertUser(user);
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
	public boolean modifyData(UserBean user) {
		try {
			if(user !=null && user.getUserId() !=null && user.getUserId()>0)this.userDAO.updateUser(user);
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
	public boolean removeData(UserBean user) {
		try {
			if(user !=null && user.getUserId() !=null && user.getUserId()>0)this.userDAO.deleteUserById(user.getUserId());
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
	public UserBean queryByPK(Integer userId) {
		return this.userDAO.selectUserById(userId);
	}

	/* (non-Javadoc)
	 * @see com.zpark.michael.service.IBasicService#queryAll(int, int)
	 */
	@Override
	public PageBean<UserBean> queryAll(int page, int pageSize) {
		PageBean<UserBean> pb = new PageBean<UserBean>();
		pb.setPageSize(pageSize);
		pb.setPage(page);
		try{
			int start = page>1 ? (page-1)*pageSize :0;
			int end  = page>1 ? page*pageSize :pageSize;
			List<UserBean> data = this.userDAO.selectUser(start, end);
			int count  = this.userDAO.selectCount();
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
	public PageBean<UserBean> queryAll(int page, int pageSize, String searchVal) {
		PageBean<UserBean> pb = new PageBean<UserBean>();
		pb.setPageSize(pageSize);
		pb.setPage(page);
		try{
			int start = page>1 ? (page-1)*pageSize :0;
			int end  = page>1 ? page*pageSize :pageSize;
			searchVal = searchVal!=null && !"".equals(searchVal) ? "%"+searchVal+"%" : "%";
			List<UserBean> data = this.userDAO.selectUserByUserName(start, end, searchVal);
			int count  = this.userDAO.selectCountByUserName(searchVal);
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
	 * @see com.zpark.michael.service.IUserService#queryAllByNameWithIndex(int, int, java.lang.String)
	 */
	@Override
	public PageBean<UserBean> queryAllByNameWithIndex(int page, int pageSize,String search) {
		PageBean<UserBean> pb =null;
		try {
			pb = this.luceneDataDAO.queryAllUser(page, pageSize, search);
			if(pb.getData()==null || pb.getData().isEmpty()) return pb;//没有查询对应的索引
			List<UserBean> data = pb.getData();
			for(UserBean user: data){
				UserBean result = this.queryByPK(user.getUserId());
				user.setDescription(result.getDescription().replace(search, "<span style='color:red;font-weight:bold'>"+search+"</span>"));
				user.setSalt(result.getSalt());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return pb;
	}

	/* (non-Javadoc)
	 * @see com.zpark.michael.service.IUserService#queryAllByNameWithIndex(int, int, java.util.Map)
	 */
	@Override
	public PageBean<UserBean> queryAllByNameWithIndex(int page, int pageSize,
			Map<String, String> condition) {
		PageBean<UserBean> pb =null;
		try {
			pb = this.luceneDataDAO.queryAllUser(page, pageSize, condition);
			if(pb.getData()==null || pb.getData().isEmpty()) return pb;//没有查询对应的索引
			List<UserBean> data = pb.getData();
			for(UserBean user: data){
				UserBean result = this.queryByPK(user.getUserId());
				user.setDescription(result.getDescription().replace(condition.get("description"), "<span style='color:red;font-weight:bold'>"+condition.get("description")+"</span>"));
				user.setDescription(result.getDescription().replace(condition.get("userName"), "<span style='color:red;font-weight:bold'>"+condition.get("userName")+"</span>"));
				user.setSalt(result.getSalt());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return pb;
	}
	
	/* (non-Javadoc)
	 * @see com.zpark.michael.service.IBasicService#queryAll(int, int, java.lang.String)
	 */
	@Override
	public PageBean<UserBean> queryAllByGroupName(int page, int pageSize, String groupName) {
		PageBean<UserBean> pb = new PageBean<UserBean>();
		pb.setPageSize(pageSize);
		pb.setPage(page);
		try{
			int start = page>1 ? (page-1)*pageSize :0;
			int end  = page>1 ? page*pageSize :pageSize;
			groupName = groupName!=null && !"".equals(groupName) ? "%"+groupName+"%" : null;
			List<UserBean> data = this.userDAO.selectUserByGroupName(start, end, groupName);
			int count  = this.userDAO.selectCountByGroupName(groupName);
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
	 * @see com.zpark.michael.service.IUserService#queryAllByGroupId(int, int, java.lang.Integer)
	 */
	@Override
	public PageBean<UserBean> queryAllByGroupId(int page, int pageSize,
			Integer groupId) {
		PageBean<UserBean> pb = new PageBean<UserBean>();
		pb.setPageSize(pageSize);
		pb.setPage(page);
		try{
			int start = page>1 ? (page-1)*pageSize :0;
			int end  = page>1 ? page*pageSize :pageSize;
			List<UserBean> data = this.userDAO.selectUserByGroupId(start, end, groupId);
			int count  = this.userDAO.selectCountByGroupId(groupId);
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
	@Autowired
	private IUserDAO userDAO;
	@Autowired
	private ILuceneDataDAO luceneDataDAO;
	private Logger logger  = Logger.getLogger(getClass());
}
