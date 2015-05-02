/**
 * com.zpark.michael.dao.impl
 * SearchLuenceDataDAOImpl.java
 * @Author：michael.Y
 * @date:2015年4月30日
 * @version 1.0
 * @desctiption:TODO
 * Copyright (c) 2015, apac.yang@gmail.com All Rights Reserved. 
 */
package com.zpark.michael.dao.impl;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.lucene.document.Document;
import org.apache.lucene.index.CorruptIndexException;
import org.apache.lucene.index.Term;
import org.apache.lucene.search.BooleanClause.Occur;
import org.apache.lucene.search.BooleanQuery;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.ScoreDoc;
import org.apache.lucene.search.TermQuery;
import org.apache.lucene.search.TopDocs;
import org.springframework.stereotype.Component;

import com.zpark.michael.dao.ILuceneDataDAO;
import com.zpark.michael.entity.GroupBean;
import com.zpark.michael.entity.PageBean;
import com.zpark.michael.entity.UserBean;
import com.zpark.michael.util.CommonMethodUtil;
import com.zpark.michael.util.LuceneUitl;

@Component(value="luceneDataDAO")
public class LuceneDataDAOImpl implements ILuceneDataDAO{

	/* (non-Javadoc)
	 * @see com.zpark.michael.dao.ISearchLuenceDataDAO#queryAllUser(int, int, java.util.Map)
	 */
	@Override
	public PageBean<UserBean> queryAllUser(int page, int pageSize,
			Map<String, String> condition) throws Exception {
		PageBean<UserBean> pageBean = new PageBean<UserBean>();
		pageBean.setPage(page);
		pageBean.setPageSize(pageSize);
		IndexSearcher indexSearcher= LuceneUitl.getIndexSearcher();
		BooleanQuery query = new BooleanQuery();
		for(String key:condition.keySet()){
			if("description".equals(key))query.add(new TermQuery(new Term(key,condition.get(key))),Occur.MUST);
			else query.add(new TermQuery(new Term(key,condition.get(key))),Occur.SHOULD);
		}
		int start  =  page<1 ? 0 :(page-1)*pageSize;
		int end  =  page<1? pageSize:page*pageSize;
		TopDocs topDocs = indexSearcher.search(query, end);
		pageBean.setData(autoLoad(start,topDocs.scoreDocs,indexSearcher));
		LuceneUitl.close(null, indexSearcher, null);
		pageBean.setCountData(topDocs.totalHits);
		CommonMethodUtil.autoPage(pageBean);
		return pageBean;
	}

	/* (non-Javadoc)
	 * @see com.zpark.michael.dao.ISearchLuenceDataDAO#queryAllUser(int, int, java.lang.String)
	 */
	@Override
	public PageBean<UserBean> queryAllUser(int page, int pageSize, String search)
			throws Exception {
		PageBean<UserBean> pageBean = new PageBean<UserBean>();
		pageBean.setPage(page);
		pageBean.setPageSize(pageSize);
		IndexSearcher indexSearcher= LuceneUitl.getIndexSearcher();
		Term term=new Term("description",search);
		TermQuery 	query=new TermQuery(term);
		int start  =  page<1 ? 0 :(page-1)*pageSize;
		int end  =  page<1? pageSize:page*pageSize;
		TopDocs topDocs = indexSearcher.search(query, end);
		pageBean.setData(autoLoad(start,topDocs.scoreDocs,indexSearcher));
		LuceneUitl.close(null, indexSearcher, null);
		pageBean.setCountData(topDocs.totalHits);
		CommonMethodUtil.autoPage(pageBean);
		return pageBean;
	}
	private List<UserBean> autoLoad(int start,ScoreDoc[] scoreDocs,IndexSearcher indexSearcher) throws CorruptIndexException, IOException{
		List<UserBean>  data = new ArrayList<UserBean>();
		UserBean user = null;
		for (int i = start; i < scoreDocs.length; i++) {
			ScoreDoc scoreDoc = scoreDocs[i];
			int docID=scoreDoc.doc;
			Document doc = indexSearcher.doc(docID);
			user = new UserBean();
			String userId = doc.get("userId");
			String userName = doc.get("userName");
			String email = doc.get("email");
			String description = doc.get("description");
			String age = doc.get("age");
			String image = doc.get("image");
			String groupId = doc.get("groupId");
			String groupName = doc.get("groupName");
			System.out.println("[ ID:"+ i+" ; userId :"+doc.get("userId")+"]");
			System.out.println("[ ID:"+ i+" ; userName :"+doc.get("userName")+"]");
			System.out.println("[ ID:"+ i+" ; email :"+doc.get("email")+"]");
			System.out.println("[ ID:"+ i+" ; description :"+doc.get("description")+"]");
			System.out.println("[ ID:"+ i+" ; age :"+doc.get("age")+"]");
			user.setUserId(userId !=null ? Integer.parseInt(doc.get("userId")): -1);
			user.setUserName(userName);
			user.setAge(age !=null ?Integer.parseInt(doc.get("age")):0);
			user.setDescription(description);
			user.setEmail(email);
			user.setImage(image);
			GroupBean groupBean = new GroupBean();
			user.setGroup(groupBean);
			if(groupId!=null && !"".equals(groupId)){
				groupBean.setGroupId(Integer.parseInt(groupId));
			}
			if(groupName!=null && !"".equals(groupName)){
				user.getGroup().setGroupName(groupName);
			}
			data.add(user);
		}
			return data;
	}
	/* (non-Javadoc)
	 * @see com.zpark.michael.dao.ISearchLuenceDataDAO#queryAllGroup(int, int, java.util.Map)
	 */
	@Override
	public PageBean<GroupBean> queryAllGroup(int page, int pageSize,
			Map<String, String> condition) throws Exception {

		return null;
	}

	/* (non-Javadoc)
	 * @see com.zpark.michael.dao.ISearchLuenceDataDAO#queryAllGroup(int, int, java.lang.String)
	 */
	@Override
	public PageBean<GroupBean> queryAllGroup(int page, int pageSize,
			String search) throws Exception {
		return null;
	}

}
