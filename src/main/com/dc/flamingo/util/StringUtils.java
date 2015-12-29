package com.dc.flamingo.util;

import java.util.UUID;

/**
 * 字符串处理工具类
 * 
 * @Class Name StringUtils
 * @Author lizz
 * @Create In Jun 21, 2011
 * 
 *         注意：如果要增加新的方法，请优先检查此方法是否在其他的StringUtils类中，除了org.apache.commons.lang3.
 *         StringUtils，
 * 
 */
public class StringUtils extends org.apache.commons.lang3.StringUtils {
		
	public static boolean isSame(String one,String two){
		if(isBlank(one)&&isBlank(two)){
			return true;
		}else if(isNotBlank(one)&&isBlank(two)){
			return false;
		}else if(isBlank(one)&&isNotBlank(two)){
			return false;
		}else{
			return one.equals(two);
		}
	}
	
	/**
	 * 删除字符串尾部的regex字符串
	 * 如：source = "abc," regex = ","
	 * 返回值为"abc"
	 * @param source 原字符串
	 * @param regex 要删除的子字符串
	 * @return 加工过的新字符串
	 */
	public static String removeEndOfchars(String source,String regex){
		if(source.trim().endsWith(regex.trim())){
			return source.substring(0, source.trim().length()-1);
		}else{
			return source.trim();
		}
		
	}
	
	public static String getUUID(){ 
		String s = UUID.randomUUID().toString(); 
		return s.substring(0,8)+s.substring(9,13)+s.substring(14,18)+s.substring(19,23)+s.substring(24); 
	} 
	public static boolean isNotEmpty(String str){
		return (str==null||"".equals(str))?false:true;
	}
}
