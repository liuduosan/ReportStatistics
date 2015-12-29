package com.dc.flamingo.util;

import java.beans.BeanInfo;
import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Method;
import java.sql.Timestamp;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.BeanWrapper;
import org.springframework.beans.BeanWrapperImpl;

import com.dc.flamingo.core.support.BaseEntity;

/**
 * Bean工具类
 * @Class Name BeanUtils
 * @Author lee
 * @Create In May 25, 2011
 */
public class BeanUtils {
	/**
	 * 将Object转为表单中有属性加类名前缀对应的MAP
	 * @Methods Name objectToClassFormMap
	 * @Create In 2012-7-19 By lee
	 * @param obj
	 * @return Map<String,Object>
	 */
	public static Map<String,Object> objectToClassFormMap(Object obj){
		Map<String, Object> dataMap = new HashMap<String, Object>();
		try {
			String className = obj.getClass().getSimpleName();
			BeanInfo beanInfo = Introspector.getBeanInfo(obj.getClass());
			for(PropertyDescriptor p: beanInfo.getPropertyDescriptors()){
				String propertyName = p.getName();
				Method method = p.getReadMethod();
				Object propertyValue = method.invoke(obj, new Object[0]);
				dataMap.put(className+"_"+propertyName, propertyValue);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return dataMap;
	}
	/**
	 * 将Object转为MAP
	 * @Methods Name objectToMap
	 * @Create In May 25, 2011 By lee
	 * @param obj
	 * @return Map<String,Object>
	 */
	public static Map<String,Object> objectToMap(Object obj){
		Map<String, Object> dataMap = new HashMap<String, Object>();
		try {
			BeanInfo beanInfo = Introspector.getBeanInfo(obj.getClass());
			for(PropertyDescriptor p: beanInfo.getPropertyDescriptors()){
				String propertyName = p.getName();
				Method method = p.getReadMethod();
				Object propertyValue = method.invoke(obj, new Object[0]);
				dataMap.put(propertyName, propertyValue);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return dataMap;
	}
	/**
	 * 将Object转为JSON使用的MAP
	 * @Methods Name objectToJsonMap
	 * @Create In May 25, 2011 By lee
	 * @param obj
	 * @return Map<String,Object>
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public static Map<String,Object> objectToJsonMap(Object obj){
		if(obj instanceof java.util.Map){
			return (Map<String, Object>) obj;
		}
		Map<String, Object> hashMap = new HashMap<String, Object>();
		hashMap.put("success", true);//增加成功返回信息
		try {
		   Class c = obj.getClass();
		   Method m[] = c.getDeclaredMethods();
		   for (int i = 0; i < m.length; i++) {
		   if (m[i].getName().indexOf("get")==0) {
			  String propertyName = Character.toLowerCase(m[i].getName().charAt(3)) + m[i].getName().substring(4);
		      Object propertyVlaue = m[i].invoke(obj, new Object[0]);
		      if(propertyVlaue!=null&&propertyVlaue instanceof BaseEntity){
		    	  BaseEntity be = (BaseEntity) propertyVlaue;
		    	  hashMap.put(propertyName+"", be.getOid());
		    	  hashMap.put(propertyName+"_text", be.toString());
		      }else if(propertyVlaue instanceof Date){//如果是时间格式，格式化
		    	  hashMap.put(propertyName, DateUtils.getDateStr((Date) propertyVlaue)); 
		      }else if(propertyVlaue instanceof Timestamp){//如果是时间格式，格式化
		    	  hashMap.put(propertyName, DateUtils.getTimeStr((Date) propertyVlaue)); 
		      }else{
		    	  hashMap.put(propertyName, propertyVlaue); 
		      }
		   }
		  }
		} catch (Exception e) {
			e.printStackTrace();
		}
		return hashMap;
	}
	
	/**
	 * 请前台传递过来的map，转换成bean
	 * @param <T>
	 * @param map
	 * @param T
	 * @return
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public static <T> T mapToObject(Map<String,Object> map, Class<T> T){
		BeanWrapper bw = new BeanWrapperImpl(T);
		try{
		PropertyDescriptor[] descriptors = bw.getPropertyDescriptors();
		for(int i=0;i<descriptors.length;i++){
			PropertyDescriptor descriptor = descriptors[i];
			String propertyName = descriptor.getName();
			Object propertyValue = map.get(propertyName);
			if(propertyValue==null||"".equals(propertyValue)){
				continue;
			}
			String propertyValueStr = "";
			if(propertyValue instanceof java.lang.Integer || propertyValue instanceof java.lang.Long
					|| propertyValue instanceof java.lang.Boolean || propertyValue instanceof java.lang.Float
					|| propertyValue instanceof java.lang.Double || propertyValue instanceof java.lang.String
					|| propertyValue instanceof java.util.Date || propertyValue instanceof java.math.BigDecimal){
				propertyValueStr = propertyValue.toString();
			}
			Class propertyTypeClass = descriptor.getPropertyType();
			String typeClassName = propertyTypeClass.getName();
			if(typeClassName.equals("java.lang.String")) {//如果是字符串类型
        		bw.setPropertyValue(propertyName, propertyValueStr);
            }
            else if(typeClassName.equals("int") || typeClassName.equals("java.lang.Integer")) {//如果是整型
            	Integer intValue = Integer.valueOf(propertyValueStr);
            	bw.setPropertyValue(propertyName, intValue);
            } 
           	else if(typeClassName.equals("long") || typeClassName.equals("java.lang.Long")) {//如果是长整型
           		Long longValue = Long.valueOf(propertyValueStr);
           		bw.setPropertyValue(propertyName, longValue);
            }
            else if(typeClassName.equals("boolean") || typeClassName.equals("java.lang.Boolean")) {//如果是布尔型
            	Boolean boolValue = Boolean.valueOf(propertyValueStr);
            	bw.setPropertyValue(propertyName, boolValue);
          	}
           	else if(typeClassName.equals("float") || typeClassName.equals("java.lang.Float")) {//如果是短浮点型
            	Float floatValue = Float.valueOf(propertyValueStr);
            	bw.setPropertyValue(propertyName, floatValue);
            }
            else if(typeClassName.equals("double") || typeClassName.equals("java.lang.Double")) {//如果是长浮点型
            	Double doubleValue = Double.valueOf(propertyValueStr);
            	bw.setPropertyValue(propertyName, doubleValue);
           	}
          	else if(typeClassName.equals("java.util.Date")) {//如果是日期时间类型
            	Date dateValue = DateUtils.convertStringToDate(propertyValueStr);
            	bw.setPropertyValue(propertyName, dateValue);
          	}
            else { //对于关联对象，只通过页面的数值设置关联对象的id属性
            	if(!"0".equals(propertyValueStr)){
            		BeanWrapper propertybw = new BeanWrapperImpl(propertyTypeClass);
                	if(propertybw.isWritableProperty("id")&&!"0".equals(propertyValueStr)){
                		propertybw.setPropertyValue("id",Long.valueOf(propertyValueStr));
                	}
             		try {
             			bw.setPropertyValue(propertyName, propertybw.getWrappedInstance() );
    				} catch (RuntimeException e) {
    					bw.setPropertyValue(propertyName, null);
    					e.printStackTrace();
    				}
            	}else{
            		bw.setPropertyValue(propertyName, null);
            	}
            }
		}
		}catch(Exception e){
			e.printStackTrace();
		}
		return (T) bw.getWrappedInstance();
	} 
	
	public static Method getWriteMethod(Object obj,String name){
		Method setMethod = null;
		try {
			BeanInfo beanInfo = Introspector.getBeanInfo(obj.getClass());
			PropertyDescriptor[] pds = beanInfo.getPropertyDescriptors();
			if(pds!=null){
				for (PropertyDescriptor pd : pds) {
					String pName = pd.getName();
					if(pName.equals(name)){
						setMethod = pd.getWriteMethod();
					}
				}
			}
			
		} catch (IntrospectionException e) {
			// TODO Auto-generated catch block
			throw new RuntimeException(obj.getClass().getSimpleName()+"类不存在！");
		}
		if(setMethod==null){
//			throw new RuntimeException("请检查"+obj.getClass().getSimpleName()+"的set方法："+name+"是否存在");
			System.out.println("请检查"+obj.getClass().getSimpleName()+"的set方法："+name+"是否存在");
			return null;
		}
		return setMethod;
	}
}
