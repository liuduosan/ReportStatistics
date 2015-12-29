package com.dc.flamingo.context;

import java.util.Locale;

import org.springframework.context.ApplicationContext;

import com.dc.flamingo.util.StringUtils;

/**
 * ApplicationContext存放器, 便于当容器启动以后，在任意位置获得ApplicationContext
 * @Class Name ContextHolder
 * @Author lee
 * @Create In May 12, 2011
 */
public final class ContextHolder {

	private static ApplicationContext ac;
	
	private static Locale local;

	private ContextHolder() {
	}

	public synchronized static void setApplicationContext(ApplicationContext act) {
		ac = act;
	}

	public static ApplicationContext getApplicationContext() {
		return ac;
	}
	
	/**
	 * 提供bean定义的名称，返回spring管理的bean
	 * @Methods Name getBean
	 * @Create In 2008-10-6 By sa
	 * @param name
	 * @return Object
	 */
	public static Object getBean(String name){
		if(StringUtils.isBlank(name)){
			return null;
		}
		return ac.getBean(name);
	}

	public static Locale getLocal() {
		return local;
	}

	public static void setLocal(Locale local) {
		ContextHolder.local = local;
	}
}
