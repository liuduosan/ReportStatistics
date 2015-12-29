/**
 * 
 */
package com.dc.commons.utils;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

/**
 * <p>
 * <pre>
 * <b>类描述：</b>
 * 	具体内容
 * <b>作者：</b>
 * 	sunjd1(孙俊东)
 * 	邮箱：<a href="mailto:sunjd1@digitalchina.com" >sunjd1@digitalchina.com</a>	
 * <b>创建时间：</b> 
 * 	2015年10月28日 上午9:43:45
 * </pre>
 * </p>
 */
public class FormIdCreaterUtil {
	public static void main(String[] args) {
		System.out.println(idCreater().length());
	}
	private static String parren = "yyyy-MM-dd";
	public static String idCreater(){
		DateFormat format = new SimpleDateFormat("yyyyMMddHHmmss");
		return format.format(new Date())+threeRandom();
	}
	public static String dateToString(){
		DateFormat format = new SimpleDateFormat(parren);
		return format.format(new Date());
	}
	public static String threeRandom(){
		Random random = new Random();
		StringBuffer buffer = new StringBuffer();
		for(int i = 0;i<3;i++){
			buffer.append(random.nextInt(10));
		}
		return buffer.toString();
	}
	public static String dateToTimesString(){
		Long time = new Date().getTime();
		return time.toString();
	}
}
