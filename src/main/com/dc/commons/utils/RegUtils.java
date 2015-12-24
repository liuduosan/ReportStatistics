/**
 * 
 */
package com.dc.commons.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * <p>
 * <pre>
 * <b>类描述：</b>
 * 	具体内容
 * <b>作者：</b>
 * 	sunjd1(孙俊东)
 * 	邮箱：<a href="mailto:sunjd1@digitalchina.com" >sunjd1@digitalchina.com</a>	
 * <b>创建时间：</b> 
 * 	2015年11月16日 上午11:18:00
 * </pre>
 * </p>
 */
public class RegUtils {
	public static void main(String[] args) {
		regIdCode("41072719911127561");
	}
	public static boolean regIdCode(String str){
		Pattern patternSfzhm1 = Pattern
				.compile("^[1-9]\\d{5}[1-9]\\d{3}((0\\d)|(1[0-2]))(([0|1|2]\\d)|3[0-1])\\d{3}([0-9]|x|X){1}$");
		Pattern patternSfzhm2 = Pattern
				.compile("^[1-9]\\d{7}((0\\d)|(1[0-2]))(([0|1|2]\\d)|3[0-1])\\d{2}([0-9]|x|X){1}$");
		Matcher matcherSfzhm1 = patternSfzhm1.matcher(str);
		Matcher matcherSfzhm2 = patternSfzhm2.matcher(str);
		if (!(str.equals("")) && !matcherSfzhm1.find() && !matcherSfzhm2.find()) {
			System.out.println("身份证号码："+str+"  格式不正确！");
			return false;
		}else{
			System.out.println("身份证号码："+str+"  格式正确！");
			return true;
		}
	} 
}
