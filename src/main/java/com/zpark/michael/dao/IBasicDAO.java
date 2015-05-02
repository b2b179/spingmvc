/**
 * com.zpark.michael.dao
 * IBasicDAO.java
 * @Author：michael.Y
 * @date:2015年4月26日
 * @version 1.0
 * @desctiption:TODO
 * Copyright (c) 2015, apac.yang@gmail.com All Rights Reserved. 
 */
package com.zpark.michael.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

public interface IBasicDAO<E> {
	/**
	 * 新增一条记录
	 * @param object
	 */
	void insertData(E object);
	/**
	 * 更新一条记录
	 * @param object
	 */
	void updateData(E object);
	/**
	 * 根据主键查询一条记录
	 * @param Integer DataId
	 * @return DataBean
	 */
	E selectDataById(@Param("pkId") Integer pkId);
	/**
	 * 删除一条记录
	 * @param DataBean object
	 */
	 void deleteDataById(@Param("pkId") Integer pkId);
	 
	 /**
	  * 获取总记录数
	  * @return int
	  */
	 int selectCountByName(@Param("search") String search);
	 /**
	  * 获取总记录数
	  * @return int
	  */
	 int selectCount();
	 
	 /**
	  * 模糊查询数据
	  * @param page
	  * @param pageSize
	  * @param DataName
	  * @return  List<E> 
	  */
	 List<E> selectDataByName(@Param("start")int start, @Param("end")int end,@Param("search")String search);
	 /**
	  * 查询所有记录
	  * @param start
	  * @param end
	  * @return  List<E>
	  */
	 List<E> selectData(@Param("start")int start, @Param("end")int end);
	 /**
	  * 查询所有记录
	  * @return List<E>
	  */
	 List<E> selectAllData();
}
