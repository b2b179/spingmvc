/**
 * com.zpark.michael.action
 * NotFoundAction.java
 * @Author：michael.Y
 * @date:2015年4月1日
 * @version 1.0
 * @desctiption:验证码action
 */
package com.zpark.michael.action;

import java.io.BufferedOutputStream;
import java.io.ByteArrayInputStream;
import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.zpark.michael.entity.JsonBean;
import com.zpark.michael.util.IConstants;
import com.zpark.michael.util.SecurityCode;
import com.zpark.michael.util.SecurityImage;
import com.zpark.michael.util.SecurityCode.SecurityCodeLevel;
@RequestMapping(value="/security")
@Controller
public class SecurityCodeAction{
	private Logger logger  = Logger.getLogger(this.getClass());
	
	/**
	 *  获取验证码
	 * @return SUCCESS
	 * @throws Exception
	 */
	@RequestMapping(value="/getCode")
	@ResponseBody
	public void getCode(HttpServletRequest request,HttpServletResponse response) throws Exception {
		logger.debug(" create an securityCode to front ");
		 String safeCode  = SecurityCode.getSecurityCode(4,SecurityCodeLevel.Hard,false);
		 request.getSession(true).setAttribute(IConstants.SECURITY_CODE, safeCode);
		 logger.debug("[  "+IConstants.SECURITY_CODE+": "+safeCode+"   ]");
		 ByteArrayInputStream bis  = SecurityImage.getImageAsInputStream(safeCode);
		 BufferedOutputStream bos  =  new BufferedOutputStream(response.getOutputStream());
		 byte bb[]  = new byte[safeCode.getBytes().length>512?512:safeCode.getBytes().length];
		 while (bis.read(bb)>0) {
			 bos.write(bb);
		}
		bis.close();
		bos.close();
	}
	/**
	 * 验证验证码
	 */
	@RequestMapping(value="/checkCode")
	@ResponseBody
	public void checkCode(String securityCode,HttpServletRequest request,HttpServletResponse response){
		Object oCode  = request.getSession().getAttribute(IConstants.SECURITY_CODE);
		JsonBean jsonBean  = new JsonBean();
		if(securityCode ==null || securityCode.length()<4){//验证失败
			jsonBean.setResult(false);
		}else if(oCode != null && oCode.toString().toUpperCase().equals(securityCode.toUpperCase())){//验证成功
			jsonBean.setResult(true);
		}else{//验证码错误，输出Json对象至前台
			jsonBean.setResult(false);
		}
		jsonBean.setName("securityCode check ");
		jsonBean.setMsg(securityCode);
		try {
			 ObjectMapper om  = new ObjectMapper();
			 String mess = om.writeValueAsString(jsonBean);//将json对象转换为字符串
			 BufferedOutputStream bos  =  new BufferedOutputStream(response.getOutputStream());
			 byte bb[]  = new byte[mess.getBytes().length>512?512:mess.getBytes().length];
			 ByteArrayInputStream bis  = new ByteArrayInputStream(mess.getBytes());
			 while (bis.read(bb)>0) {
				 bos.write(bb);
			}
			System.out.println("[ checkCode:]"+mess);
			bis.close();
			bos.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
