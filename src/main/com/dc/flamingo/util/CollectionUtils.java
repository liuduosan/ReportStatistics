package com.dc.flamingo.util;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.List;

import org.apache.commons.beanutils.PropertyUtils;
/**
 * @ToDo   一些集合工具类，用于集合的转换 
 * @author lizz
 */
@SuppressWarnings({"all"})
public class CollectionUtils {
	
	private CollectionUtils(){}
	
	/**
	 * 集合类转换成LinkedHashSet
	 * @param c
	 * @return
	 */
	public static LinkedHashSet asLinkedHashSet(Collection c) {
		return (LinkedHashSet)asTargetTypeCollection(c,LinkedHashSet.class);
	}
	
	/**
	 * 集合类转换成HashSet
	 * @param c
	 * @return
	 */
	public static HashSet asHashSet(Collection c) {
		return (HashSet)asTargetTypeCollection(c,HashSet.class);
	}
	
	/**
	 * 集合类转换成ArrayList
	 * @param c
	 * @return
	 */
	public static ArrayList asArrayList(Collection c) {
		return (ArrayList)asTargetTypeCollection(c,ArrayList.class);
	}
	
	public static Collection asTargetTypeCollection(Collection c,Class targetCollectionClass) {
		if(targetCollectionClass == null) 
			throw new IllegalArgumentException("'targetCollectionClass' must be not null");
		if(c == null)
			return null;
		if(targetCollectionClass.isInstance(c)) 
			return c;
		
		Collection result = null;
		
		try {
			result = (Collection)targetCollectionClass.newInstance();
		} catch (Exception e) {
			throw new IllegalArgumentException("targetCollectionClass="+targetCollectionClass.getName()+" is not correct!",e);
		}
		
		result.addAll(c);
		return result;
	}
	
	/**
	 * 求一组可Iterable对象的某一个属性的值，Collection接口扩展自Iterable,所以所有的集合类均合适
	 * @param from
	 * @param propertyName
	 * @return
	 */
	public static List selectProperty(Iterable from,String propertyName) {
		if(propertyName == null) throw new IllegalArgumentException("'propertyName' must be not null");
		if(from == null) return null;
		
		List result = new ArrayList();
		for(Object o : from) {
			try {
				if(o == null) {
					result.add(null);
				}else {
					Object value = PropertyUtils.getSimpleProperty(o, propertyName);
					result.add(value);
				}
			} catch (IllegalAccessException e) {
				throw new IllegalArgumentException("Cannot get propertyValue by propertyName:"+propertyName+" on class:"+o.getClass(),e);
			} catch (InvocationTargetException e) {
				throw new IllegalArgumentException("Cannot get propertyValue by propertyName:"+propertyName+" on class:"+o.getClass(),e.getTargetException());
			} catch (NoSuchMethodException e) {
				throw new IllegalArgumentException("no such property:"+propertyName+" on class:"+o.getClass(),e);
			}
		}
		return result;
	}
	

	
	/**
	 * 求一组可迭代对象的某个属性的平均值
	 * @param objects
	 * @param propertyName
	 * @return
	 */
	public static double avg(Iterable objects,String propertyName) {
		List<Number> propertyValues = CollectionUtils.selectProperty(objects, propertyName);
		return avg(propertyValues);
	}
	
	/**
	 * 求集合的平均数，数字类型的
	 * @param values
	 * @return
	 */
	public static double avg(Collection<Number> values) {
		if(values == null) return 0;
		if(values.isEmpty()) return 0;
		return sum(values) / values.size();
	}
	
	/**
	 * 求一组可迭代对象的某个属性的值的总和
	 * @param objects
	 * @param propertyName
	 * @return
	 */
	public static double sum(Iterable objects,String propertyName) {
		if(objects == null) return 0;
		List<Number> propertyValues = CollectionUtils.selectProperty(objects, propertyName);
		return sum(propertyValues);
	}

	public static double sum(Iterable<Number> values) {
		if(values == null) return 0;
		
		double sum = 0;
		for(Number num : values) {
			if(num == null) continue;
			sum += num.doubleValue();
		}
		return sum;
	}
	
	/**
	 * 求一个集合类中元素的最大元素
	 * @param objects
	 * @param propertyName
	 * @return
	 */
	public static Object max(Collection objects,String propertyName) {
		List<Comparable> propertyValues = CollectionUtils.selectProperty(objects, propertyName);
		return Collections.max(propertyValues);
	}
	
	/**
	 * 求一个集合类中元素的最小元素
	 * @param objects
	 * @param propertyName
	 * @return
	 */
	public static Object min(Collection objects,String propertyName) {
		List<Comparable> propertyValues = CollectionUtils.selectProperty(objects, propertyName);
		return Collections.min(propertyValues);
	}

}
