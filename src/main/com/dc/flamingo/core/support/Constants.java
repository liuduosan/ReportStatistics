package com.dc.flamingo.core.support;

/**
 * 系统常量
 * 用户记录系统调用的公用状态常量
 * @Class Name Constants
 * @Author lee
 * @Create In May 13, 2011
 */
public class Constants {
	public static final int STATUS_DRAFT = 0;		//草稿状态
	public static final int STATUS_FLOW = 1;		//处理中
	public static final int STATUS_FINISH = 2;		//完成状态
	public static final int STATUS_BACK = -1;		//打回状态
	
	public static final String SEPARATOR = ";";	//分隔符
	public static final String PROPERTIES_SPRING_BEAN_ID = "messageSource"; //spring注入的bean Id
	
}
