/**
 * com.zpark.luene
 * TestLuene.java
 * @Author：michael.Y
 * @date:2015年4月30日
 * @version 1.0
 * @desctiption:TODO
 * Copyright (c) 2015, apac.yang@gmail.com All Rights Reserved. 
 */
package com.zpark.luene;

import java.io.File;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;
import org.apache.lucene.util.Version;
import org.junit.Test;
import org.wltea.analyzer.lucene.IKAnalyzer;

import com.zpark.michael.util.LuceneUitl;

public class TestLuene {
	
	@Test
	public void testDirectory(){
		System.out.println(TestLuene.class.getResource("/"));
	}
	
	@Test
	public void testIndexWriter(){
		try {
			  Directory directory=FSDirectory.open(new File("index"));
			  Analyzer analyzer=new IKAnalyzer();
			  IndexWriterConfig indexWriterConfig=new IndexWriterConfig(Version.LUCENE_35,analyzer);
			  IndexWriter indexWriter = new IndexWriter(directory, indexWriterConfig);
			  System.out.println(indexWriter);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Test
	public void testIndexWriterLuence(){
		try {
			  System.out.println(LuceneUitl.getIndexWriter());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
