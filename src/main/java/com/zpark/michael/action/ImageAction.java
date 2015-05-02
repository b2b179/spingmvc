/**
 * com.zpark.michael.action
 * ImageAction.java
 * @Author：michael.Y
 * @date:2015年4月24日
 * @version 1.0
 * @desctiption:TODO
 * Copyright (c) 2015, apac.yang@gmail.com All Rights Reserved. 
 */
package com.zpark.michael.action;

import java.io.BufferedWriter;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.zpark.michael.entity.JsonBean;
import com.zpark.michael.util.IConstants;

@RequestMapping(value="/images")
@Controller
public class ImageAction  {

     /** 
	  * 通过Ajax获取图片信息 
	  * @return 
	  * @throws IOException  
	  */ 
	@RequestMapping(value="/uploadFile")
	@ResponseBody
	public void testUploadFile(@RequestParam(value="uploadFile",required=false)MultipartFile uploadFile,HttpServletRequest request,HttpServletResponse response) throws IOException{
		    System.out.println("------------uploadFile-----------");
			response.setContentType("text/plain"); 
		     response.setCharacterEncoding("utf-8"); 
		     JsonBean json  = new JsonBean();
			 json.setName("uploadImageByAjax"); 
		     if(uploadFile!=null){ 
		           String fileName=UUID.randomUUID()+"."+getFileType(uploadFile.getOriginalFilename()); 
		           //本地物理地址 如果不存在则创建文件夹 不存在则删除
		           File directory  = new File(request.getSession().getServletContext().getRealPath(IConstants.UPLOAD_DIRECTORY));
		           if(!directory.exists()){
		        	   directory.mkdir();
		           }
		           String realPath  = directory.getAbsolutePath()+"\\"+fileName;
		           File soureImg  = new File(realPath);
		           if(!soureImg.exists()){
		        	   soureImg.createNewFile();
		           }
		           uploadFile.transferTo(soureImg);
		           String imgUrl = "/"+IConstants.UPLOAD_DIRECTORY+"/"+fileName;
		           List<ImageTempBean> data = new ArrayList<ImageTempBean>();
		           ImageTempBean image = new ImageTempBean();
		           image.setName(soureImg.getName());
		           image.setRealpath(realPath);
		           image.setUrl(imgUrl);
		           data.add(image);
		           String deleteImg = request.getParameter("deleteImg");
		           if(deleteImg !=null && !"".equals(deleteImg)){//删除就图
		        	   System.out.println(deleteImg);
		        	   //deleteImg.replace(request.get, newChar)
		        	   System.out.println(request.getServerName());
		           }
		         json.setData(data);
		         json.setMsg("图片上传成功");
		         json.setResult(true);
		     }else{ 
		         json.setMsg("图片上传失败");
		         json.setResult(false);
		     }
		     printJsonResult(json,response);
	}
	/**
	 * 输出 json 数据
	 * @param source
	 */
	private void printJsonResult(Object source,HttpServletResponse response) throws IOException{
		ObjectMapper om  = new ObjectMapper();
		String mess = om.writeValueAsString(source);//将json对象转换为字符串  
		response.setContentType("text/html;charset=utf-8");
		BufferedWriter  out = new BufferedWriter(response.getWriter());
		out.write(mess);
		System.out.println("json mess:"+mess);
		out.flush();
		out.close();
	}
	 /**
	  *  获取该文件的后缀
	  * @param file
	  * @return String
	  */
 	private static String getFileType(String file){
 		String fileType = "";
 		if(file.indexOf(".")>0){//存在文件后缀名
 			System.out.println(file.split("\\.").length);
 		    String tempFileArr[] = file.split("\\.");
 		    fileType = tempFileArr[1] !=null ? tempFileArr[1]:null;
 		}
 		return fileType;
 	}
 
 	public static void main(String[] args) {
 		System.out.println("------------------testGetFileType------------------------");
		File file = new File("F:/upload/images/test.xml");
		if(!file.getParentFile().exists()){
			file.getParentFile().mkdirs();
			try {
				file.createNewFile();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}else{
			if(!file.exists())
				try {
					file.createNewFile();
				} catch (IOException e) {
					e.printStackTrace();
				}
		}
		System.out.println("fileName:"+file.getName());
		
	}
	class ImageTempBean{
		private String url;
		private String name;
		private String realpath;
		public String getUrl() {
			return url;
		}
		public void setUrl(String url) {
			this.url = url;
		}
		public String getName() {
			return name;
		}
		public void setName(String name) {
			this.name = name;
		}
		public String getRealpath() {
			return realpath;
		}
		public void setRealpath(String realpath) {
			this.realpath = realpath;
		}
		
		
	}
 	
}
