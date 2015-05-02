/**
 * com.zpark.michael.quartz
 * CreateIndexQuartz.java
 * @Author：michael.Y
 * @date:2015年4月30日
 * @version 1.0
 * @desctiption:TODO
 * Copyright (c) 2015, apac.yang@gmail.com All Rights Reserved. 
 */
package com.zpark.michael.quartz;

import java.io.IOException;
import java.util.List;

import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.apache.lucene.document.Field.Index;
import org.apache.lucene.document.Field.Store;
import org.apache.lucene.document.NumericField;
import org.apache.lucene.index.CorruptIndexException;
import org.apache.lucene.index.IndexWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.zpark.michael.entity.GroupBean;
import com.zpark.michael.entity.PageBean;
import com.zpark.michael.entity.UserBean;
import com.zpark.michael.service.IGroupService;
import com.zpark.michael.service.IUserService;
import com.zpark.michael.util.LuceneUitl;

@Component("indexQuartz")
public class CreateIndexQuartz {
	
	/**
	 * 第一次调用此方法
	 */
	public void createAllIndex() {
		PageBean<UserBean> data = this.userService.queryAll(1, 10);
		createIndex(data.getData());
		for(int i=1;i<data.getCountPages();i++){
			List<UserBean> list = this.userService.queryAll(i+1, 10).getData();
			createIndex(list);
		}
	}
	/**
	 * 创建索引
	 * @param data
	 */
	private void createIndex(List<UserBean> datas){
		Document document =null ;
		for(UserBean user:datas){
			document = new Document();//创建单个数据
			document.add(new Field("userName", user.getUserName()==null? "":user.getUserName(),Store.YES,Index.ANALYZED));
			document.add(new Field("description",user.getDescription()==null? "":user.getDescription(),Store.NO,Index.ANALYZED));
			document.add(new Field("email",user.getEmail()==null?"":user.getEmail(),Store.YES,Index.ANALYZED_NO_NORMS));
			document.add(new Field("image",user.getImage()==null? "":user.getImage(),Store.YES,Index.NO));
			NumericField userId=new NumericField("userId", Store.YES, true);
			userId.setIntValue(user.getUserId());
			document.add(userId);
			NumericField age  = new NumericField("age",Store.YES, true) ;
			age.setIntValue(user.getAge()==null?0:user.getAge());
			document.add(age);
			GroupBean group  = user.getGroup();
			if(group!=null){
				NumericField groupId  = new NumericField("groupId",Store.YES, true) ;
				groupId.setIntValue(group.getGroupId());
				document.add(groupId);
				document.add(new Field("groupName", group.getGroupName()==null? "":group.getGroupName(),Store.YES,Index.ANALYZED));
			}
			try {
				System.out.println("document:"+document);
				System.out.println("IndexWriter:"+LuceneUitl.getIndexWriter());
				LuceneUitl.getIndexWriter().addDocument(document);
			} catch (CorruptIndexException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		LuceneUitl.close(LuceneUitl.getIndexWriter(), null, null);
	}
	/**
	 * 删除所有的索引
	 */
	public void deleteAllIndex() {
		IndexWriter indexWriter = LuceneUitl.getIndexWriter();
		try {
			indexWriter.deleteAll();
		} catch (IOException e) {
			e.printStackTrace();
		}
		LuceneUitl.close(LuceneUitl.getIndexWriter(), null, null);
	}
	
	public void deleteIndex(Integer userId){
		
		
	}
	
	@Autowired
	private IUserService userService;
	@Autowired
	private IGroupService groupService;
	
}
