package com.dc.flamingo.core.dao;

import java.beans.PropertyDescriptor;
import java.lang.reflect.Field;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.beans.BeanWrapper;
import org.springframework.beans.BeanWrapperImpl;

import com.dc.flamingo.core.support.BaseEntity;
import com.dc.flamingo.core.support.Page;
import com.dc.flamingo.core.support.PageParam;
import com.dc.flamingo.util.DateUtils;

/**
 * 共用数据查询DAO
 * @Class Name DataDao
 * @Author lee
 * @Create In 2012-2-8
 */
public class DataDao<T> extends JdbcDao<T>{
	
	public DataDao(){
		super.setDbType(DB_ORACLE);
	}
	/**
	 * 根据类和条件进行分页查询
	 * 此方法仅用于针对单个类（表）进行简单查询情况
	 * @Methods Name getGridPage
	 * @Create In 2012-2-8 By lee
	 * @param clazz
	 * @param pageParam
	 * @param params
	 * @return Page
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	
	public Page getGridPage(Class<? extends BaseEntity> clazz, PageParam pageParam, Map<String, String> params) {
		Criteria critea = super.getCriteria(clazz);
		BeanWrapper bw = new BeanWrapperImpl(clazz);
		try{
		PropertyDescriptor[] descriptors = bw.getPropertyDescriptors();
		for(int i=0;i<descriptors.length;i++){
			PropertyDescriptor descriptor = descriptors[i];
			String propertyName = descriptor.getName();
			Object propertyValue = params.get(propertyName);
			if(propertyValue==null||"".equals(propertyValue)){
				continue;
			}
			String propertyValueStr = "";
			if(propertyValue instanceof java.lang.Integer || propertyValue instanceof java.lang.Long
					|| propertyValue instanceof java.lang.Boolean || propertyValue instanceof java.lang.Float
					|| propertyValue instanceof java.lang.Double || propertyValue instanceof java.lang.String
					|| propertyValue instanceof java.util.Date){
				propertyValueStr = propertyValue.toString();
			}
			Class propertyTypeClass = descriptor.getPropertyType();
			String typeClassName = propertyTypeClass.getName();
			if(typeClassName.equals("java.lang.String")) {//如果是字符串类型,使用模糊查询匹配
				critea.add(Restrictions.like(propertyName, propertyValueStr));
            }
            else if(typeClassName.equals("int") || typeClassName.equals("java.lang.Integer")) {//如果是整型
            	Integer intValue = Integer.valueOf(propertyValueStr);
            	critea.add(Restrictions.eq(propertyName, intValue));
            } 
           	else if(typeClassName.equals("long") || typeClassName.equals("java.lang.Long")) {//如果是长整型
           		Long longValue = Long.valueOf(propertyValueStr);
           		critea.add(Restrictions.eq(propertyName, longValue));
            }
            else if(typeClassName.equals("boolean") || typeClassName.equals("java.lang.Boolean")) {//如果是布尔型
            	Boolean boolValue = Boolean.valueOf(propertyValueStr);
            	critea.add(Restrictions.eq(propertyName, boolValue));
          	}
           	else if(typeClassName.equals("float") || typeClassName.equals("java.lang.Float")) {//如果是短浮点型
            	Float floatValue = Float.valueOf(propertyValueStr);
            	critea.add(Restrictions.eq(propertyName, floatValue));
            }
            else if(typeClassName.equals("double") || typeClassName.equals("java.lang.Double")) {//如果是长浮点型
            	Double doubleValue = Double.valueOf(propertyValueStr);
            	critea.add(Restrictions.eq(propertyName, doubleValue));
           	}
          	else if(typeClassName.equals("java.util.Date")) {//如果是日期时间类型
            	Date dateValue = DateUtils.convertStringToDate(propertyValueStr);
            	critea.add(Restrictions.eq(propertyName, dateValue));
          	}
            else { //对于关联对象，只通过页面的数值设置关联对象的id属性
            	BeanWrapper propertybw = new BeanWrapperImpl(propertyTypeClass);
            	if(propertybw.isWritableProperty("id")){
            		propertybw.setPropertyValue("id",Long.valueOf(propertyValueStr));
            	}
             	if(BaseEntity.class.isAnnotationPresent(propertyTypeClass)){
             		try {
             			critea.add(Restrictions.eq(propertyName, propertybw.getWrappedInstance()));
					} catch (RuntimeException e) {
						bw.setPropertyValue(propertyName, null);
						e.printStackTrace();
					}
             	}
            }
		}
		}catch(Exception e){
			e.printStackTrace();
		}
		Page page = critea.page(pageParam.getPageNum(), pageParam.getPageSize());
		return page;
	}

	/**
	 * 根据类和条件进行查询
	 * 此方法仅用于针对单个类（表）进行简单查询情况
	 * @Methods Name getList
	 * @Create In 2012-2-8 By lee
	 * @param clazz
	 * @param params
	 * @return List
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public List<?> getList(Class<? extends BaseEntity> clazz, Map<String, String> params) {
		Criteria critea = super.getCriteria(clazz);
		BeanWrapper bw = new BeanWrapperImpl(clazz);
		try{
		PropertyDescriptor[] descriptors = bw.getPropertyDescriptors();
		for(int i=0;i<descriptors.length;i++){
			PropertyDescriptor descriptor = descriptors[i];
			String propertyName = descriptor.getName();
			Object propertyValue = params.get(propertyName);
			if(propertyValue==null||"".equals(propertyValue)){
				continue;
			}
			String propertyValueStr = "";
			if(propertyValue instanceof java.lang.Integer || propertyValue instanceof java.lang.Long
					|| propertyValue instanceof java.lang.Boolean || propertyValue instanceof java.lang.Float
					|| propertyValue instanceof java.lang.Double || propertyValue instanceof java.lang.String
					|| propertyValue instanceof java.util.Date){
				propertyValueStr = propertyValue.toString();
			}
			Class propertyTypeClass = descriptor.getPropertyType();
			String typeClassName = propertyTypeClass.getName();
			if(typeClassName.equals("java.lang.String")) {//如果是字符串类型,使用模糊查询匹配
				critea.add(Restrictions.like(propertyName, propertyValueStr));
            }
            else if(typeClassName.equals("int") || typeClassName.equals("java.lang.Integer")) {//如果是整型
            	Integer intValue = Integer.valueOf(propertyValueStr);
            	critea.add(Restrictions.eq(propertyName, intValue));
            } 
           	else if(typeClassName.equals("long") || typeClassName.equals("java.lang.Long")) {//如果是长整型
           		Long longValue = Long.valueOf(propertyValueStr);
           		critea.add(Restrictions.eq(propertyName, longValue));
            }
            else if(typeClassName.equals("boolean") || typeClassName.equals("java.lang.Boolean")) {//如果是布尔型
            	Boolean boolValue = Boolean.valueOf(propertyValueStr);
            	critea.add(Restrictions.eq(propertyName, boolValue));
          	}
           	else if(typeClassName.equals("float") || typeClassName.equals("java.lang.Float")) {//如果是短浮点型
            	Float floatValue = Float.valueOf(propertyValueStr);
            	critea.add(Restrictions.eq(propertyName, floatValue));
            }
            else if(typeClassName.equals("double") || typeClassName.equals("java.lang.Double")) {//如果是长浮点型
            	Double doubleValue = Double.valueOf(propertyValueStr);
            	critea.add(Restrictions.eq(propertyName, doubleValue));
           	}
          	else if(typeClassName.equals("java.util.Date")) {//如果是日期时间类型
            	Date dateValue = DateUtils.convertStringToDate(propertyValueStr);
            	critea.add(Restrictions.eq(propertyName, dateValue));
          	}
            else { //对于关联对象，只通过页面的数值设置关联对象的id属性
            	BeanWrapper propertybw = new BeanWrapperImpl(propertyTypeClass);
            	if(propertybw.isWritableProperty("id")){
            		propertybw.setPropertyValue("id",Long.valueOf(propertyValueStr));
            	}
             	if(BaseEntity.class.isAnnotationPresent(propertyTypeClass)){
             		try {
             			critea.add(Restrictions.eq(propertyName, propertybw.getWrappedInstance()));
					} catch (RuntimeException e) {
						bw.setPropertyValue(propertyName, null);
						e.printStackTrace();
					}
             	}
            }
		}
		}catch(Exception e){
			e.printStackTrace();
		}
		return critea.listObject();
	}
	
	/**
	 * 根据类和条件进行单挑记录查询
	 * 此方法仅用于针对单个类（表）进行简单查询情况
	 * @Methods Name getBean
	 * @Create In 2012-2-8 By lee
	 * @param clazz
	 * @param params
	 * @return BaseEntity
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	
	public <T>T getBean(Class<? extends BaseEntity> clazz, Map<String, String> params) {
		Criteria critea = super.getCriteria(clazz);
		BeanWrapper bw = new BeanWrapperImpl(clazz);
		try{
		PropertyDescriptor[] descriptors = bw.getPropertyDescriptors();
		for(int i=0;i<descriptors.length;i++){
			PropertyDescriptor descriptor = descriptors[i];
			String propertyName = descriptor.getName();
			Object propertyValue = params.get(propertyName);
			if(propertyValue==null||"".equals(propertyValue)){
				continue;
			}
			String propertyValueStr = "";
			if(propertyValue instanceof java.lang.Integer || propertyValue instanceof java.lang.Long
					|| propertyValue instanceof java.lang.Boolean || propertyValue instanceof java.lang.Float
					|| propertyValue instanceof java.lang.Double || propertyValue instanceof java.lang.String
					|| propertyValue instanceof java.util.Date){
				propertyValueStr = propertyValue.toString();
			}
			Class propertyTypeClass = descriptor.getPropertyType();
			String typeClassName = propertyTypeClass.getName();
			if(typeClassName.equals("java.lang.String")) {//如果是字符串类型
				critea.add(Restrictions.eq(propertyName, propertyValueStr));
            }
            else if(typeClassName.equals("int") || typeClassName.equals("java.lang.Integer")) {//如果是整型
            	Integer intValue = Integer.valueOf(propertyValueStr);
            	critea.add(Restrictions.eq(propertyName, intValue));
            } 
           	else if(typeClassName.equals("long") || typeClassName.equals("java.lang.Long")) {//如果是长整型
           		Long longValue = Long.valueOf(propertyValueStr);
           		critea.add(Restrictions.eq(propertyName, longValue));
            }
            else if(typeClassName.equals("boolean") || typeClassName.equals("java.lang.Boolean")) {//如果是布尔型
            	Boolean boolValue = Boolean.valueOf(propertyValueStr);
            	critea.add(Restrictions.eq(propertyName, boolValue));
          	}
           	else if(typeClassName.equals("float") || typeClassName.equals("java.lang.Float")) {//如果是短浮点型
            	Float floatValue = Float.valueOf(propertyValueStr);
            	critea.add(Restrictions.eq(propertyName, floatValue));
            }
            else if(typeClassName.equals("double") || typeClassName.equals("java.lang.Double")) {//如果是长浮点型
            	Double doubleValue = Double.valueOf(propertyValueStr);
            	critea.add(Restrictions.eq(propertyName, doubleValue));
           	}
          	else if(typeClassName.equals("java.util.Date")) {//如果是日期时间类型
            	Date dateValue = DateUtils.convertStringToDate(propertyValueStr);
            	critea.add(Restrictions.eq(propertyName, dateValue));
          	}
            else { //对于关联对象，只通过页面的数值设置关联对象的id属性
            	BeanWrapper propertybw = new BeanWrapperImpl(propertyTypeClass);
            	if(propertybw.isWritableProperty("id")){
            		propertybw.setPropertyValue("id",Long.valueOf(propertyValueStr));
            	}
             	if(BaseEntity.class.isAnnotationPresent(propertyTypeClass)){
             		try {
             			critea.add(Restrictions.eq(propertyName, propertybw.getWrappedInstance()));
					} catch (RuntimeException e) {
						bw.setPropertyValue(propertyName, null);
						e.printStackTrace();
					}
             	}
            }
		}
		}catch(Exception e){
			e.printStackTrace();
		}
		return (T) critea.uniqueResult();
	}

	/**
	 * 共用保存方法
	 * @Methods Name saveData
	 * @Create In 2012-2-8 By lee
	 * @param obj
	 * @return BaseEntity
	 */
	@SuppressWarnings({"hiding"})
	public <T>T saveData(T obj) {
		return (T) super.save(obj);
	}

	/**
	 * 共用删除方法
	 * @Methods Name remove
	 * @Create In 2012-2-8 By lee
	 * @param clazz
	 * @param id void
	 */
	public void remove(Class<? extends BaseEntity> clazz, long id) {
		try {
			Field tableField = clazz.getField("TABLE");
			super.removeById(tableField.get(clazz).toString(), id);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

}
