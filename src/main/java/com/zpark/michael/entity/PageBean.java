/**
 * com.zpark.michael.entity
 * PageBean.java
 * @Author：michael.Y
 * @date:2015年3月31日
 * @version 1.0
 * @desctiption:页面信息实体类
 */
package com.zpark.michael.entity;

import java.util.ArrayList;
import java.util.List;
public final class PageBean<E> {
	/**
	 * 查询的页面
	 */
	private int page =1;
	/**
	 * 页面大小
	 */
	private int pageSize=20;
	/**
	 * 页面总数
	 */
	private int countPages=-1;
	/**
	 * 总记录数
	 */
	private int countData;
	/**
	 * 默认在前台要显示的页数
	 */
	private int show =5;
	/**
	 * 存放查询出的结果集
	 */
	private List<E> data =new ArrayList<E>();
	/**
	 * 前台显示开始页
	 */
	private int showStartPage;
	/**
	 * 前台显示结束页
	 */
	private int showEndPage;

	public int getPage() {
		return page;
	}

	public void setPage(int page) {
		this.page = page;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public int getCountPages() {
		if(countPages<0){
			if(countData<1)return 0;
			if(countData<pageSize && countData>0 )return 1;
			countPages  = countData%pageSize==0 ? countData/pageSize : countData/pageSize+1;
		}
		return countPages;
	}

	public void setCountPages(int countPages) {
		this.countPages = countPages;
	}
	public List<E> getData() {
		return data;
	}

	public void setData(List<E> data) {
		this.data = data;
	}

	public int getCountData() {
		return countData;
	}

	public void setCountData(int countData) {
		this.countData = countData;
	}

	public int getShow() {
		return show;
	}

	public void setShow(int show) {
		this.show = show;
	}

	public int getShowStartPage() {
		return showStartPage;
	}

	public void setShowStartPage(int showStartPage) {
		this.showStartPage = showStartPage;
	}

	public int getShowEndPage() {
		return showEndPage;
	}

	public void setShowEndPage(int showEndPage) {
		this.showEndPage = showEndPage;
	}

	@Override
	public String toString() {
		return "PageBean [page=" + page + ", pageSize=" + pageSize
				+ ", countPages=" + countPages + ", countData=" + countData
				+ ", show=" + show + ", showStartPage=" + showStartPage
				+ ", showEndPage=" + showEndPage + "]";
	}
	
}
