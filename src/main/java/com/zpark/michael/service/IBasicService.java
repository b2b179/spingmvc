/**
 * com.zpark.michael.service
 * IBasicService.java
 * @Author：michael.Y
 * @date:2015年4月7日
 * @version 1.0
 * @desctiption:TODO
 * Copyright (c) 2015, apac.yang@gmail.com All Rights Reserved. 
 */
package com.zpark.michael.service;

import com.zpark.michael.entity.PageBean;

public interface IBasicService<DataBean> {
	/**
	 * save data
	 * 
	 * @param Object
	 *            o
	 * @return if save successful return true else return false
	 */
	boolean addData(DataBean data);

	/**
	 * modify data
	 * 
	 * @param Object
	 *            o
	 * @return if modify successful return true else return false
	 */
	boolean modifyData(DataBean data);

	/**
	 * remove data
	 * 
	 * @param Object
	 *            o
	 * @return if remove successful return true else return false
	 */
	boolean removeData(DataBean data);

	/**
	 * query a Object
	 * 
	 * @param pkId
	 * @return Object e
	 */
	DataBean queryByPK(Integer pkId);

	/**
	 * 
	 * @param page
	 * @param pageSize
	 * @return PageBean<E>
	 */
	PageBean<DataBean> queryAll(int page, int pageSize);

	/**
	 * eg: map<String key, Object value>
	 * eg UserBean 'username' ,'michael'
	 * @param page
	 * @param pageSize
	 * @param condition
	 * @return PageBean<E>
	 */
	PageBean<DataBean> queryAll(int page, int pageSize, String searchVal);
}
