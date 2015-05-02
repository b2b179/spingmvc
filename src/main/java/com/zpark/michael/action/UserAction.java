/**
 * com.zpark.michael.action
 * UserAction.java
 * @Author：michael.Y
 * @date:2015年4月24日
 * @version 1.0
 * @desctiption:TODO
 * Copyright (c) 2015, apac.yang@gmail.com All Rights Reserved. 
 */
package com.zpark.michael.action;

import java.io.IOException;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
import com.zpark.michael.entity.UserBean;
import com.zpark.michael.service.IGroupService;
import com.zpark.michael.service.IUserService;
@Controller
@RequestMapping(value="/users")
public class UserAction extends BasicAction<UserBean> {
	/* (non-Javadoc)
	 * @see com.zpark.michael.action.BasicAction#queryAll(org.springframework.ui.ModelMap, com.zpark.michael.entity.PageBean, java.lang.String)
	 */
	@Override
	public String queryAll(ModelMap modelMap, PageBean<UserBean> pageBean,
			String search) {
		logger.debug("【                        METHOD：queryAll         】");
		if(pageBean ==null)pageBean  = new PageBean<UserBean>();
		System.out.println("search:"+search);
		if(search !=null && !"".equals(search.replaceAll(" ", ""))){
			Map<String,String> condition = new HashMap<String,String>();
			condition.put("description", search);
			condition.put("userName", search);
			pageBean   = this.userService.queryAllByNameWithIndex(pageBean.getPage(), pageBean.getPageSize(), condition);
		}else{
			pageBean   = this.userService.queryAll(pageBean.getPage(), pageBean.getPageSize());
		}
		modelMap.put("pageBean", pageBean);
		modelMap.put("search", search);
		return this.getResultPage(VIEW_DIRECTORY,ResultType.QUERY_LIST);
	}
	/* (non-Javadoc)
	 * @see com.zpark.michael.action.BasicAction#addData(org.springframework.ui.ModelMap, java.lang.Object)
	 */
	@Override
	public String addData(UserBean data) {
		logger.debug("【                        addData：POST         】");
		if(data ==null){
			throw new NullPointerException(" NO Data Selected !");
		}
		this.userService.addData(data);
		return "redirect:/users/queryAll?search="+data.getUserName();

	}
	/* (non-Javadoc)
	 * @see com.zpark.michael.action.BasicAction#addData()
	 */
	@Override
	public String addData(ModelMap modelMap) {
		logger.debug("【                        addData：GET         】");
		List<GroupBean> groupList  = this.groupService.queryAll();
		modelMap.put("groupList", groupList);
		return getResultPage(VIEW_DIRECTORY, ResultType.ADD_PAGE);
	}
	/* (non-Javadoc)
	 * @see com.zpark.michael.action.BasicAction#updateData(java.lang.Integer)
	 */
	@Override
	public String updateData(@PathVariable Integer id,ModelMap modelMap) {
		logger.debug("【                        updateData：GET ID:"+id+"        】");
		UserBean data=this.userService.queryByPK(id);
		List<GroupBean> groupList  = this.groupService.queryAll();
		modelMap.put("data", data);
		modelMap.put("groupList", groupList);
		return getResultPage(VIEW_DIRECTORY, ResultType.UPDATE_PAGE);
	}
	/* (non-Javadoc)
	 * @see com.zpark.michael.action.BasicAction#updateData(java.lang.Object)
	 */
	@Override
	public String updateData(UserBean data) {
		logger.debug("【                        updateData：POST         】");
		if(data ==null || data.getUserId() ==null || data.getUserId()<1){
			throw new NullPointerException(" NO Data Selected !");
		}
		UserBean sourceUser  = this.userService.queryByPK(data.getUserId());
		sourceUser.setAge(data.getAge());
		sourceUser.setDescription(data.getDescription());
		sourceUser.setEmail(data.getEmail());
		sourceUser.setGroup(data.getGroup());
		sourceUser.setImage(data.getImage());
		sourceUser.setUserName(data.getUserName());
		this.userService.modifyData(sourceUser);
		return "redirect:/users/queryAll?search="+data.getUserName();
	}
	
	@RequestMapping(value="updateDataTest")
	public void updateDataTest(UserBean data) {
		System.out.println(data);
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
			UserBean data =null;
			for(Integer id:ids){
				logger.debug("DELETE ID:"+id);
				data = new UserBean();
				data.setUserId(id);
				this.userService.removeData(data);
			}
			return queryAll(new ModelMap(),new PageBean<UserBean>(),null);
		}
	}
	/* (non-Javadoc)
	 * @see com.zpark.michael.action.BasicAction#operactorByAjax(com.zpark.michael.entity.JsonBean, javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
	 */
	@Override
	public void operactorByAjax(HttpServletRequest request,
			HttpServletResponse response) {
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
						PageBean<UserBean> pageBean = new PageBean<UserBean>();
						String search  = request.getParameter("search");
						String pageStr = request.getParameter("page");
						String pageSizeStr = request.getParameter("pageSize");
						int page  = pageStr ==null || "".equals(pageStr) ?1:Integer.parseInt(pageStr);
						int pageSize  = pageSizeStr ==null || "".equals(pageSizeStr) ?1:Integer.parseInt(pageSizeStr);
						pageBean.setPage(page);
						pageBean.setPageSize(pageSize);
						search = search ==null ||"".equals(search) ? "" :search;
						System.out.println("search:"+search);
						search = URLDecoder.decode(search, "UTF-8");
						System.out.println("search:"+search);
 						json.setData(queryAllByAjax(pageBean,search));break;//异步查询所有信息
					case "queryGroup":
						List<GroupBean> lists  = this.groupService.queryAll();
						json.setData(lists);
						break;
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
	 * 批量删除
	 */
	private void removeDataByAjax(Integer[] ids) {
		logger.debug("【                        removeDataByAjax：AJAX         】");
		if(ids ==null || ids.length<1){
			throw new NullPointerException();
		}else{
			UserBean data =null;
			for(Integer id:ids){
				logger.debug("DELETE ID:"+id);
				data = new UserBean();
				data.setUserId(id);
				this.userService.removeData(data);
			}
		}
	}
	/**
	 * @return
	 */
	private List<?> queryAllByAjax(PageBean<UserBean> pageBean,String search) {
		if(search !=null && !"".equals(search.replaceAll(" ", ""))){
			pageBean   = this.userService.queryAllByNameWithIndex(pageBean.getPage(), pageBean.getPageSize(), search);
		}else{
			pageBean   = this.userService.queryAll(pageBean.getPage(), pageBean.getPageSize());
		}
		List<PageBean<UserBean>> list = new ArrayList<PageBean<UserBean>>();
		list.add(pageBean);
		return list;
	}
	/**
	 * 跳转页面文件夹
	 */
	private static final String VIEW_DIRECTORY = "/user/";
	@Autowired
	private IUserService userService;
	@Autowired
	private IGroupService groupService;
}
