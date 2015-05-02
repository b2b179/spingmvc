/**
 * @project Blackboard 
 * @package com.zpark.bb.michael.util
 * @filename EncryptUtil.java
 * @autor michael
 * @date  2015年2月3日
 * @E-Mail apac.yang@gmail.com
 * @version 1.0
 * @description 加密器
 */
package com.zpark.michael.util;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;

import org.apache.log4j.Logger;

public abstract class EncryptUtil {
	
	private static Logger logger = Logger.getLogger(EncryptUtil.class);
	private static String key = "m";
	/**
	 * 加密MD5
	 * @param password
	 * @return encryptPassword
	 */
	public static String encryptPassword(String password){
		MessageDigest md5=null;
		StringBuffer cacheChar = new StringBuffer();
		try{
			byte defaultByte[] =password.getBytes();
			md5 =MessageDigest.getInstance("MD5");
			md5.reset();
			md5.update(defaultByte);
			byte resultByte[] =md5.digest();
			for(int i=0;i<resultByte.length;i++){
				 String hex = Integer.toHexString(IConstants.ENCRYPTKEY & resultByte[i]);
	             if (hex.length() == 1){
	                    cacheChar.append(key);//追加字符
	                }
	                cacheChar.append(hex);
			}
			return cacheChar.toString();
		}catch(Exception e){
			logger.error("Encrypt Passwords Failed");
			e.printStackTrace();
			return  null;
		}
	}
	/**
	 * 
	 * @description 密码比较
	 * @param sourcePass
	 * @param resultPass
	 * @return 如果匹配成功返回true否则返回false
	 */
	public static boolean comparePassword(String sourcePass,String resultPass){
		byte[] expectedBytes = null,actualBytes = null;
		System.out.println("sourcePass:"+sourcePass);
		System.out.println("resultPass:"+resultPass);
		try {
			expectedBytes = sourcePass.getBytes("UTF-8");
			actualBytes = resultPass.getBytes("UTF-8");
		} catch (UnsupportedEncodingException e) {
			logger.error("[ ExceptionMess: Can not get the character byte ]");
			e.printStackTrace();
		}
        int expectedLength = expectedBytes == null ? -1 : expectedBytes.length;
        int actualLength = actualBytes == null ? -1 : actualBytes.length;
        if (expectedLength != actualLength) {//匹配长度是否一致
            return false;
        }
        int result = 0;
        for (int i = 0; i < expectedLength; i++) {
            result |= expectedBytes[i] ^ actualBytes[i];
        }
        return result == 0;
	}
	
	public static void main(String[] args) {
		System.out.println(encryptPassword("test123"));
		System.out.println(comparePassword("ccc3e747a6afbbcbf8be7668acfebee5",encryptPassword("123")));
	}
}
