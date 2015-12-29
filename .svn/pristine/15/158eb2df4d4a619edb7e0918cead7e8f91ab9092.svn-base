package com.dc.flamingo.util;

import java.util.UUID;

import com.dc.flamingo.context.ContextHolder;
import com.dc.flamingo.tools.service.IdGeneratorService;

/**
 * 编号生成器工具
 * @Class Name NumbersGeneratorUtil
 * @Author lee
 * @Create In May 23, 2011
 */
public class IdGeneratorUtils {
	/**
	 * 获取最新编号
	 * @Methods Name getLastNumber
	 * @Create In Jun 22, 2011 By lee
	 * @param key
	 * @return String
	 */
	public static long getLastNumber(String key){
		IdGeneratorService idGeneratorService = (IdGeneratorService) ContextHolder.getBean("idGeneratorService");
		return idGeneratorService.getLastId(key);
	}
	
	/**
	 * 生成最新编号字符串
	 * @Methods Name initLastNumber
	 * @Create In Jun 22, 2011 By lee
	 * @param key	编号生成器关键字
	 * @param num	编号长度
	 * @return String
	 */
	public static String initLastNumber(String key, int num){
		IdGeneratorService idGeneratorService = (IdGeneratorService) ContextHolder.getBean("idGeneratorService");
		long lastNumber = idGeneratorService.saveLastId(key);
		String lastNumberStr = Long.toString(lastNumber);
		for(int i=(num-lastNumberStr.length());i>0;i--){
			lastNumberStr = "0"+lastNumberStr;
		}
		return lastNumberStr;
	}
	
	/**
	 * 带前缀的编号字符串
	 * @Methods Name initLastNumber
	 * @Create In Aug 10, 2011 By lee
	 * @param key
	 * @param num
	 * @param prefix
	 * @return String
	 */
	public static String initLastNumber(String key, int num, String prefix){
		return prefix+initLastNumber(key, num);
	}
	
	/**
	 * 
	* @Title: getUUID
	* @Description: TODO(生成一个唯一的32位字符串)
	* @param @return    设定文件
	* @return String    返回类型
	* @throws
	 */
	public static String getUUID(){ 
		String s = UUID.randomUUID().toString(); 
		return s.substring(0,8)+s.substring(9,13)+s.substring(14,18)+s.substring(19,23)+s.substring(24); 
	} 
}
