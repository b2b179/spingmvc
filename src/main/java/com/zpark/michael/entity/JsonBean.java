/**
 * com.zpark.michael.entity
 * JsonBean.java
 * @Author：michael.Y
 * @date:2015年4月2日
 * @version 1.0
 * @desctiption:用于像前台传人json格式数据
 * Copyright (c) 2015, apac.yang@gmail.com All Rights Reserved. 
 */
package com.zpark.michael.entity;

import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public final class JsonBean implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 2819683236761740424L;
	/**
	 * 名称
	 */
	private String name;
	/**
	 * 页面接受的格式
	 */
	private String pageType ="json";
	/**
	 * 是否正常
	 */
	private boolean result;
	/**
	 * 输出的页面错误信息
	 */
	private String msg;
	/**
	 * 前台传人的命令
	 */
	private String command;
	/**
	 * 要展示的数据集
	 */
	private List<?> data;
	/**
	 * 要操作的id集
	 */
	private Integer[] ids;
	/**
	 *创建时间
	 */
	private Date createDate = Calendar.getInstance().getTime();

	public Date getCreateDate() {
		return createDate;
	}
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPageType() {
		return pageType;
	}
	public void setPageType(String pageType) {
		this.pageType = pageType;
	}
	public boolean isResult() {
		return result;
	}
	public void setResult(boolean result) {
		this.result = result;
	}
	
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
	public List<?> getData() {
		return data;
	}
	public void setData(List<?> data) {
		this.data = data;
	}
	public String getCommand() {
		return command;
	}
	public void setCommand(String command) {
		this.command = command;
	}
	public Integer[] getIds() {
		return ids;
	}
	public void setIds(Integer[] ids) {
		this.ids = ids;
	}
}
