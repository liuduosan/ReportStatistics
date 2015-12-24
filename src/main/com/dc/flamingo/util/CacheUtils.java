package com.dc.flamingo.util;

import com.opensymphony.oscache.base.NeedsRefreshException;
import com.opensymphony.oscache.base.events.CacheEventListener;
import com.opensymphony.oscache.general.GeneralCacheAdministrator;

/**
 * 
 * @Title: CacheUtils.java
 * @Description: <br>此类用于管理项目中的所有缓存类，比如流程缓存，人员缓存
 * @Company: DigitalChina
 * @Created on Feb 15, 2012 3:05:18 PM
 * @author lizz
 * @version $Revision: 1.1 $
 * @since 1.0
 */
public final class CacheUtils{
	private static final GeneralCacheAdministrator INSTANCE;
	private CacheUtils(){}	//工具类不允许创建实例
	static{
		INSTANCE=new GeneralCacheAdministrator();
		CacheEventListener listener=new MyCacheEventListener();
		INSTANCE.getCache().addCacheEventListener(listener);
	}

	/**
	 * 将指定key的对象压入缓存，并指定到某个组中
	 * @param group 组名称，比如workflow
	 * @param key，缓存的key，比如流程id
	 * @param content 缓存的对象
	 */
	public static void put(String group,String key,Object content){
		INSTANCE.putInCache(group+"_"+key, content,new String[]{group});
	}
	/**
	 * 从缓存中清除指定某个组的某个key的对象
	 * @param group 组名称，比如workflow
	 * @param key，缓存的key，比如流程id
	 */
	public static void remove(String group,String key){
		INSTANCE.removeEntry(group+"_"+key);
	}
	

	/**
	 * 从缓存中清除某个组的所有对象
	 */
	public static void removeGroup(String group){
		INSTANCE.flushGroup(group);
	}
	
	/**
	 * 从缓存中清除所有对象
	 */
	public static void removeAll(){
		INSTANCE.flushAll();
	}
	

	/**
	 * 通过指定key从缓存中取出该对象，如果取不到，则返回null
	 * @param group 组名称，比如workflow
	 * @param key，缓存的key，比如流程id
	 * @return
	 */
	public static Object get(String group,String key) {
		try {
			return INSTANCE.getFromCache(group+"_"+key,-1);
		} catch (NeedsRefreshException e) {
			INSTANCE.cancelUpdate(group+"_"+key);
			return null;
		}
	}

}		


