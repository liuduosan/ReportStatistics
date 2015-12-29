package com.dc.flamingo.util;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.alibaba.fastjson.JSON;
import com.dc.flamingo.core.support.Page;

public class JsonUtils {

	public static String JSON_SUCCESS = "{\"success\":true}";
    /**
     * POJO对象转换为JSON
     * @Methods Name objectToJson
     * @Create In May 24, 2011 By lee
     * @param pojo
     * @return String
     */
	public static String objectToJson(Object pojo){
		return JSON.toJSONString(BeanUtils.objectToJsonMap(pojo));
    }
    /**
     * MAP转化为JSON
     * @Methods Name mapToJson
     * @Create In May 25, 2011 By lee
     * @param map
     * @return String
     */
    public static String mapToJson(Map<String, Object> map){
    	return JSON.toJSONString(map);
    }
    /**
     * 数组转为JSON
     * @Methods Name arrayToJson
     * @Create In Jul 12, 2011 By lee
     * @param pojo
     * @return String
     */
    public static String arrayToJson(Object[] array){
    	String json = null;
    	try{
    		json =  JSON.toJSONString(array);
    	}catch(Exception e){
    		e.printStackTrace();
    	}
    	return json;
    }
    /**
     * List对象转换为JSON
     * @Methods Name listToJson
     * @Create In May 24, 2011 By lee
     * @param <T>
     * @param list
     * @return String
     */
	public static <T> String listToJson(List<T> list){
		List<Map<String,Object>> beanMapList = new ArrayList<Map<String, Object>>();
		for(T t : list){
			beanMapList.add(BeanUtils.objectToJsonMap(t));
		}
		return JSON.toJSONString(beanMapList);
	}
	/**
	 * Set对象转换为JSON
	 * @Methods Name setToJson
	 * @Create In Jun 30, 2011 By lee
	 * @param <T>
	 * @param set
	 * @return String
	 */
	public static <T> String setToJson(Set<T> set) {
		List<Map<String,Object>> beanMapList = new ArrayList<Map<String, Object>>();
		for(T t : set){
			beanMapList.add(BeanUtils.objectToJsonMap(t));
		}
		return JSON.toJSONString(beanMapList);
	}
	/**
	 * 分页器转换为JSON
	 * @Methods Name pageToJson
	 * @Create In Aug 8, 2011 By lee
	 * @param page
	 * @return String
	 */
	public static String pageToJson(Page page){
		StringBuffer sb = new StringBuffer();
		sb.append("{\"total\":"+page.getTotalCount()+",");
		sb.append("\"rows\":"+listToJson(page.list())+"}");
		return sb.toString();
		
	}
	/**
	 * 将JSON转成Bean对象
	 * @Methods Name jsonToObject
	 * @Create In May 24, 2011 By lee
	 * @param <T>
	 * @param json
	 * @param clazz
	 * @return T
	 */
	public static <T> T jsonToObject(String json, Class<T> clazz){
		return JSON.parseObject(json, clazz);
	}
	/**
	 * 将JSON转成List
	 * @Methods Name jsonToList
	 * @Create In May 25, 2011 By lee
	 * @param <T>
	 * @param json
	 * @param clazz
	 * @return List<T>
	 */
	public static <T> List<T> jsonToList(String json, Class<T> clazz){
		return JSON.parseArray(json, clazz);
	}
	/**
	 * 将JSON转成Map
	 * @Methods Name jsonToMap
	 * @Create In May 25, 2011 By lee
	 * @param json
	 * @return Map<String, Object>
	 */
	@SuppressWarnings("unchecked")
	public static Map<String, Object> jsonToMap(String json){
		return JSON.parseObject(json, Map.class);
	}
	
	/**
	 * AJAX请求在ACTION层异常时返回的数据
	 * @Methods Name errorJson
	 * @Create In Aug 5, 2011 By lee
	 * @param error
	 * @return String
	 */
	public static String errorJson(String error){
		return "{\"total\":0,\"rows\":[],\"success\":false,\"error\":\""+error+"\"}";
	}
	
	public static Object toJsonObject(String json){
		return JSON.parseObject(json);
	}
}
