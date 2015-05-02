/**
 * com.zpark.michael.util
 * LuenceUtil.java
 * @Author：michael.Y
 * @date:2015年4月30日
 * @version 1.0
 * @desctiption:Luence 工具类
 * Copyright (c) 2015, apac.yang@gmail.com All Rights Reserved. 
 */
package com.zpark.michael.util;

import java.io.File;
import java.io.IOException;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.index.IndexReader;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;
import org.apache.lucene.util.Version;
import org.wltea.analyzer.lucene.IKAnalyzer;

public class LuceneUitl {
	private static final ThreadLocal<IndexWriter> THREADLOCAL=new ThreadLocal<IndexWriter>();
	  private static IndexWriter indexWriter;
	  private static IndexReader indexReader;
	  private static String indexPath;
	  static{
		  indexPath = LuceneUitl.class.getClassLoader().getResource("").getPath();
		  //indexPath = 
		  indexPath = indexPath.substring(1);
	  }
	  public  static IndexWriter getIndexWriter(){
		  indexWriter=THREADLOCAL.get();
		  if(indexWriter==null){
			  try {
				  Directory directory=FSDirectory.open(new File(indexPath));
				  System.out.println(directory);
				  Analyzer analyzer=new IKAnalyzer();
				  IndexWriterConfig indexWriterConfig=new IndexWriterConfig(Version.LUCENE_35, analyzer);
				  indexWriter=new IndexWriter(directory, indexWriterConfig);
				  THREADLOCAL.set(indexWriter);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		  }
		  return indexWriter;
	  }
	  public  static IndexSearcher getIndexSearcher(){
		  if(indexReader==null){
			  try {
				Directory directory=FSDirectory.open(new File(indexPath));
				  indexReader=IndexReader.open(directory);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		  }else{
			  try {
				 //检查索引库有没有被篡改
				IndexReader ir=IndexReader.openIfChanged(indexReader);
				  if(ir!=null){
					  indexReader.close();//关闭旧的
					  indexReader=ir;//使用新的
				  }
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			  
		  }
		  return new IndexSearcher(indexReader);
	  }
	  public static void close(IndexWriter indexWriter,IndexSearcher indexSearcher,IndexReader indexReader){
		  if(indexWriter!=null){
			  try {
				indexWriter.commit();
				  indexWriter.close();
				  THREADLOCAL.remove();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		  }if(indexSearcher!=null){
			  try {
				indexSearcher.close();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		  }if(indexReader!=null){
			  try {
				indexReader.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		  }
	  }
}
