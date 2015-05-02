/**
 * @project Blackboard
 * @package com.zpark.bb.michael.util
 * @filename AbstractCommonMethod.java
 * @autor michael
 * @date  2015年2月2日
 * @E-Mail apac.yang@gmail.com
 * @description  所有的常用方法都放在此处
 * 保存一些常用的方法
 */
package com.zpark.michael.util;

import com.zpark.michael.entity.PageBean;


public abstract class CommonMethodUtil {
	/**
	 * 
	 * @description 该方法主要用于比较后一个字符是否存在在前字符数组中
	 * @param commands
	 * @param command
	 * @return 如果存在该内容则返回True，如果不存在则返回False
	 */
	public static boolean arrayEqualsObject(String[] commands, String command) {
		if (commands == null || command == null || command.equals(""))
			return false;
		else {
			for (String c : commands)
				if (c.equals(command.toUpperCase()))return true;
		}
		return false;
	}
	
	/**
	 * 
	 * @param <E>
	 * @param <E>
	 * @param <E>
	 * @description 自动转载分页
	 * @param condition
	 * @param PageBean
	 * @return PageBean
	 */
	public static  <E> PageBean<E>  autoPage(PageBean<E> page){
		int count = page.getCountData();
		int countPage  = count<= page.getPageSize() ? 1 : (int) Math.ceil(count/page.getPageSize()) +(count%page.getPageSize()>0? 1:0);
		page.setCountData(count);
		page.setCountPages(countPage);
		int currentPage  = page.getPage();
		int tempPage = 0;
		if(countPage>IConstants.DEFAULT_SHOW_PAGE){//判断当前页面总数是否大于设置的页面显示总数
			if(countPage>=currentPage+2)
			{
				if(currentPage>=3){// 当前页大于等于为第三页，同时满足
					page.setShowStartPage(currentPage-2);
					page.setShowEndPage(currentPage+2);
				}else{
					page.setShowStartPage(1);
					tempPage = 3-currentPage;
					if(countPage>=currentPage+2+tempPage)
						page.setShowEndPage(currentPage+2+tempPage);
					else
						page.setShowEndPage(countPage);
				}
			}else{
				page.setShowEndPage(countPage);
				tempPage = currentPage+2 -countPage;
				if(currentPage-tempPage>=3){//前面至少还有3+tempPage的页数
					page.setShowStartPage(currentPage-2-tempPage);
				}else{//从第一页开始
					page.setShowStartPage(currentPage>=3?currentPage-2:1);
				}
			}
		}else{
			page.setShowStartPage(1);
			page.setShowEndPage(countPage);
		}
		return page;
	}
}
