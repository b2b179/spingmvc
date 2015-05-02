/**
 * com.zpark.michael.action
 * GroupAction.java
 * @Author：michael.Y
 * @date:2015年4月26日
 * @version 1.0
 * @desctiption:TODO
 * Copyright (c) 2015, apac.yang@gmail.com All Rights Reserved. 
 */
package com.zpark.michael.action;

import java.io.IOException;
import java.net.URLDecoder;
import java.sql.Date;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.zpark.michael.entity.GroupBean;
import com.zpark.michael.entity.JsonBean;
import com.zpark.michael.entity.PageBean;
import com.zpark.michael.service.IGroupService;
@RequestMapping(value="/groups")
@Controller
public class GroupAction extends BasicAction<GroupBean> {
	/* (non-Javadoc)
	 * @see com.zpark.michael.action.BasicAction#queryAll(org.springframework.ui.ModelMap, com.zpark.michael.entity.PageBean, java.lang.String)
	 */
	@Override
	public String queryAll(ModelMap modelMap, PageBean<GroupBean> pageBean,String search) {
		logger.debug("【                        METHOD：queryAll         】");
		if(pageBean ==null)pageBean  = new PageBean<GroupBean>();
		if(search !=null && !"".equals(search.replaceAll(" ", ""))){
			pageBean   = this.groupService.queryAll(pageBean.getPage(), pageBean.getPageSize(), search);
		}else{
			pageBean   = this.groupService.queryAll(pageBean.getPage(), pageBean.getPageSize());
		}
		modelMap.put("pageBean", pageBean);
		modelMap.put("search", search);
		return this.getResultPage(VIEW_DIRECTORY,ResultType.QUERY_LIST);
	}
	/* (non-Javadoc)
	 * @see com.zpark.michael.action.BasicAction#addData(org.springframework.ui.ModelMap, java.lang.Object)
	 */
	@Override
	public String addData(GroupBean data) {
		logger.debug("【                        addData：POST         】");
		if(data ==null){
			throw new NullPointerException(" NO Data Selected !");
		}
		data.setCreateDate(new Date(Calendar.getInstance().getTimeInMillis()));
		data.setParent(null);
		this.groupService.addData(data);
		String search = data.getGroupName();
		return queryAll(new ModelMap(),new PageBean<GroupBean>(),search);

	}
	/* (non-Javadoc)
	 * @see com.zpark.michael.action.BasicAction#addData()
	 */
	@Override
	public String addData(ModelMap modelMap) {
		logger.debug("【                        addData：GET         】");
		return getResultPage(VIEW_DIRECTORY, ResultType.ADD_PAGE);
	}
	/* (non-Javadoc)
	 * @see com.zpark.michael.action.BasicAction#updateData(java.lang.Integer)
	 */
	@Override
	public String updateData(@PathVariable Integer id,ModelMap modelMap) {
		logger.debug("【                        updateData：GET         】");
		GroupBean data=this.groupService.queryByPK(id);
		modelMap.put("data", data);
		return getResultPage(VIEW_DIRECTORY, ResultType.UPDATE_PAGE);
	}
	/* (non-Javadoc)
	 * @see com.zpark.michael.action.BasicAction#updateData(java.lang.Object)
	 */
	@Override
	public String updateData(GroupBean data) {
		logger.debug("【                        updateData：POST         】");
		if(data ==null || data.getGroupId() ==null || data.getGroupId()<1){
			throw new NullPointerException("NO Data Selected!");
		}
		GroupBean sourceData = this.groupService.queryByPK(data.getGroupId());
		sourceData.setGroupName(data.getGroupName());
		sourceData.setParent(data.getParent());
		this.groupService.modifyData(sourceData);
		String search = data.getGroupName();
		return queryAll(new ModelMap(),new PageBean<GroupBean>(),search);
		
	}
	/* (non-Javadoc)
	 * @see com.zpark.michael.action.BasicAction#removeData(java.lang.Integer[])
	 */
	@Override
	public String removeData(ModelMap modelMap,Integer[] ids) {
		logger.debug("【                        removeData：POST         】");
		if(ids ==null || ids.length<1){
			throw new NullPointerException();
		}else{
			GroupBean data =null;
			for(Integer id:ids){
				logger.debug("DELETE ID:"+id);
				data = new GroupBean();
				data.setGroupId(id);
				this.groupService.removeData(data);
			}
			return queryAll(new ModelMap(),new PageBean<GroupBean>(),null);
		}
	}
	/**
	 * 批量删除
	 */
	private void removeDataByAjax(Integer[] ids) {
		logger.debug("【                        removeDataByAjax：AJAX         】");
		if(ids ==null || ids.length<1){
			throw new NullPointerException();
		}else{
			GroupBean data =null;
			for(Integer id:ids){
				logger.debug("DELETE ID:"+id);
				data = new GroupBean();
				data.setGroupId(id);
				this.groupService.removeData(data);
			}
		}
	}

	/**
	 * Ajax 获取所有的值
	 * @param pageBean
	 * @param search
	 * @return
	 */
	private List<PageBean<GroupBean>> queryAllByAjax(PageBean<GroupBean> pageBean,String search) {
		if(search !=null && !"".equals(search.replaceAll(" ", ""))){
			pageBean   = this.groupService.queryAll(pageBean.getPage(), pageBean.getPageSize(), search);
		}else{
			pageBean   = this.groupService.queryAll(pageBean.getPage(), pageBean.getPageSize());
		}
		List<PageBean<GroupBean>> list = new ArrayList<PageBean<GroupBean>>();
		list.add(pageBean);
		return list;
	}
	/* (non-Javadoc)
	 * @see com.zpark.michael.action.BasicAction#operactorByAjax(com.zpark.michael.entity.JsonBean, javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
	 */
	@Override
	public void operactorByAjax(HttpServletRequest request,HttpServletResponse response) {
		logger.debug("【                        operactorByAjax：AJAX         】");
		JsonBean json = new JsonBean();
		json.setName("operactorByAjax");
		String command  = request.getParameter("command");
		if( command!=null && !"".equals(command)){
			try {
				logger.debug("【                       Command："+command+"         】");
				json.setCommand(command);
				switch(command){
					case "removeData":
						removeDataByAjax(json.getIds());break;//删除一条参数
					case "queryAll":
						PageBean<GroupBean> pageBean = new PageBean<GroupBean>();
						String search  = request.getParameter("search");
						System.out.println("search:"+search);
						String pageStr = request.getParameter("page");
						String pageSizeStr = request.getParameter("pageSize");
						int page  = pageStr ==null || "".equals(pageStr) ?1:Integer.parseInt(pageStr);
						int pageSize  = pageSizeStr ==null || "".equals(pageSizeStr) ?1:Integer.parseInt(pageSizeStr);
						pageBean.setPage(page);
						pageBean.setPageSize(pageSize);
						search = search ==null ||"".equals(search) ? "" :search;
						search = URLDecoder.decode(search, "UTF-8");
 						json.setData(queryAllByAjax(pageBean,search));break;//异步查询所有信息
				}
				json.setMsg(" Successful operation ");
				json.setResult(true);
			} catch (Exception e) {
				e.printStackTrace();
				logger.error(e.getMessage());
				json.setMsg(e.getMessage());
			}
		}else{
			json.setMsg(" no command found ");
			json.setResult(false);
		}
		try {
			printJsonResult(json,response);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 跳转页面文件夹
	 */
	private static final String VIEW_DIRECTORY = "/group/";
	@Autowired
	private IGroupService groupService;
}
