/**
 * com.zpark.michael.entity
 * GroupBean.java
 * @Author：michael.Y
 * @date:2015年4月24日
 * @version 1.0
 * @desctiption:组别实体类
 * Copyright (c) 2015, apac.yang@gmail.com All Rights Reserved. 
 */
package com.zpark.michael.entity;

import java.io.Serializable;
import java.sql.Date;
import java.util.HashSet;
import java.util.Set;

import org.apache.ibatis.type.Alias;
@Alias("GroupBean")
public class GroupBean implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -8384278260613200971L;
	
	private Integer groupId;
	private String groupName;
	private GroupBean parent;
	private Date createDate;
	private Set<UserBean> users = new HashSet<UserBean>();
	public Integer getGroupId() {
		return groupId;
	}
	public void setGroupId(Integer groupId) {
		this.groupId = groupId;
	}
	public String getGroupName() {
		return groupName;
	}
	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}
	public GroupBean getParent() {
		return parent;
	}
	public void setParent(GroupBean parent) {
		this.parent = parent;
	}
	public Date getCreateDate() {
		return createDate;
	}
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
	public Set<UserBean> getUsers() {
		return users;
	}
	public void setUsers(Set<UserBean> users) {
		this.users = users;
	}
	@Override
	public String toString() {
		return "GroupBean [groupId=" + groupId + ", groupName=" + groupName
				+ ", createDate=" + createDate + "]";
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((createDate == null) ? 0 : createDate.hashCode());
		result = prime * result + ((groupId == null) ? 0 : groupId.hashCode());
		result = prime * result
				+ ((groupName == null) ? 0 : groupName.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		GroupBean other = (GroupBean) obj;
		if (createDate == null) {
			if (other.createDate != null)
				return false;
		} else if (!createDate.equals(other.createDate))
			return false;
		if (groupId == null) {
			if (other.groupId != null)
				return false;
		} else if (!groupId.equals(other.groupId))
			return false;
		if (groupName == null) {
			if (other.groupName != null)
				return false;
		} else if (!groupName.equals(other.groupName))
			return false;
		return true;
	}
}
