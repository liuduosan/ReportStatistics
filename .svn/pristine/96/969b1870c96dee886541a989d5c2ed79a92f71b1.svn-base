package com.dc.flamingo.core.dao;

import org.apache.commons.lang3.StringUtils;

/**
 * 条件构造器
 * 用于创建条件表达式
 * @Class Name Restrictions
 * @Author lee
 * @Create In 2012-2-8
 */
public class Restrictions {

	/**
	 * 等于情况
	 * @Methods Name eq
	 * @Create In 2012-2-8 By lee
	 * @param columnName
	 * @param value
	 * @return SimpleExpression
	 */
	public static SimpleExpression eq(String columnName, Object value) {
		if(value==null){
			return new SimpleExpression(columnName, value, "is");
		}else{
			return new SimpleExpression(columnName, value, "=");
		}
	}
	
	/**
	 * 不等于情况
	 * @Methods Name ne
	 * @Create In 2012-2-8 By lee
	 * @param columnName
	 * @param value
	 * @return SimpleExpression
	 */
	public static SimpleExpression ne(String columnName, Object value) {
		if(value==null){
			return new SimpleExpression(columnName, value, "is not");
		}else{
			return new SimpleExpression(columnName, value, "<>");
		}
	}

	/**
	 * 模糊匹配
	 * @Methods Name like
	 * @Create In 2012-2-8 By lee
	 * @param columnName
	 * @param value
	 * @return SimpleExpression
	 */
	public static SimpleExpression like(String columnName, String value) {
		return like(columnName, value,MatchMode.ANYWHERE);
	}

	/**
	 * 匹配
	 * @Methods Name like
	 * @Create In 2012-2-8 By lee
	 * @param columnName
	 * @param value
	 * @param matchMode
	 * @return SimpleExpression
	 */
	public static SimpleExpression like(String columnName, String value,
			MatchMode matchMode) {
		return new SimpleExpression(columnName, matchMode.toMatchString(value), " like ");
	}

	/**
	 * 大于
	 * @Methods Name gt
	 * @Create In 2012-2-8 By lee
	 * @param columnName
	 * @param value
	 * @return SimpleExpression
	 */
	public static SimpleExpression gt(String columnName, Object value) {
		return new SimpleExpression(columnName, value, ">");
	}

	/**
	 * 小于
	 * @Methods Name lt
	 * @Create In 2012-2-8 By lee
	 * @param columnName
	 * @param value
	 * @return SimpleExpression
	 */
	public static SimpleExpression lt(String columnName, Object value) {
		return new SimpleExpression(columnName, value, "<");
	}

	/**
	 * 大于等于
	 * @Methods Name le
	 * @Create In 2012-2-8 By lee
	 * @param columnName
	 * @param value
	 * @return SimpleExpression
	 */
	public static SimpleExpression le(String columnName, Object value) {
		return new SimpleExpression(columnName, value, "<=");
	}

	/**
	 * 小于等于
	 * @Methods Name ge
	 * @Create In 2012-2-8 By lee
	 * @param columnName
	 * @param value
	 * @return SimpleExpression
	 */
	public static SimpleExpression ge(String columnName, Object value) {
		return new SimpleExpression(columnName, value, ">=");
	}
	
	/**
	 * 等于，且是否排空
	 * @Methods Name eq
	 * @Create In 2012-2-8 By lee
	 * @param columnName
	 * @param value
	 * @param containNull
	 * @return SimpleExpression
	 */
	public static SimpleExpression eq(String columnName, Object value, boolean containNull) {
		return new SimpleExpression(columnName, value, "=", containNull);
	}
	
	/**
	 * 不等于，且是否排空
	 * @Methods Name ne
	 * @Create In 2012-2-8 By lee
	 * @param columnName
	 * @param value
	 * @param containNull
	 * @return SimpleExpression
	 */
	public static SimpleExpression ne(String columnName, Object value, boolean containNull) {
		return new SimpleExpression(columnName, value, "<>", containNull);
	}

	/**
	 * 模糊匹配，且是否排空
	 * @Methods Name like
	 * @Create In 2012-2-8 By lee
	 * @param columnName
	 * @param value
	 * @param containNull
	 * @return SimpleExpression
	 */
	public static SimpleExpression like(String columnName, String value, boolean containNull) {
		return like(columnName, value,MatchMode.ANYWHERE, containNull);
	}

	/**
	 * 匹配，且是否排空
	 * @Methods Name like
	 * @Create In 2012-2-8 By lee
	 * @param columnName
	 * @param value
	 * @param matchMode
	 * @param containNull
	 * @return SimpleExpression
	 */
	public static SimpleExpression like(String columnName, String value,
			MatchMode matchMode, boolean containNull) {
		if(!containNull&&StringUtils.isBlank(value)){
			return new SimpleExpression(columnName, null, " like ", containNull);
		}
		return new SimpleExpression(columnName, matchMode.toMatchString(value), " like ", containNull);
	}

	/**
	 * 大于，且是否排空
	 * @Methods Name gt
	 * @Create In 2012-2-8 By lee
	 * @param columnName
	 * @param value
	 * @param containNull
	 * @return SimpleExpression
	 */
	public static SimpleExpression gt(String columnName, Object value, boolean containNull) {
		return new SimpleExpression(columnName, value, ">", containNull);
	}

	/**
	 * 小于，且是否排空
	 * @Methods Name lt
	 * @Create In 2012-2-8 By lee
	 * @param columnName
	 * @param value
	 * @param containNull
	 * @return SimpleExpression
	 */
	public static SimpleExpression lt(String columnName, Object value, boolean containNull) {
		return new SimpleExpression(columnName, value, "<", containNull);
	}

	/**
	 * 小于等于，且是否排空
	 * @Methods Name le
	 * @Create In 2012-2-8 By lee
	 * @param columnName
	 * @param value
	 * @param containNull
	 * @return SimpleExpression
	 */
	public static SimpleExpression le(String columnName, Object value, boolean containNull) {
		return new SimpleExpression(columnName, value, "<=", containNull);
	}

	/**
	 * 大于等于，且是否排空
	 * @Methods Name ge
	 * @Create In 2012-2-8 By lee
	 * @param columnName
	 * @param value
	 * @param containNull
	 * @return SimpleExpression
	 */
	public static SimpleExpression ge(String columnName, Object value, boolean containNull) {
		return new SimpleExpression(columnName, value, ">=", containNull);
	}

	/**
	 * 为空
	 * @Methods Name isNull
	 * @Create In 2012-7-9 By lee
	 * @param columnName
	 * @return SimpleExpression
	 */
	public static SimpleExpression isNull(String columnName) {
		return new SimpleExpression(columnName, null, "is");
	}
	/**
	 * 不为空
	 * @Methods Name isNotNull
	 * @Create In 2012-7-9 By lee
	 * @param columnName
	 * @return SimpleExpression
	 */
	public static SimpleExpression isNotNull(String columnName) {
		return new SimpleExpression(columnName, null, "is not");
	}
	/**
	 * 并且
	 * @Methods Name and
	 * @Create In 2012-10-16 By lee
	 * @param criterions
	 * @return LogicalExpression
	 */
	public static LogicalExpression and(Criterion... criterions){
		return new LogicalExpression("AND",criterions);
	}
	/**
	 * 或者
	 * @Methods Name or
	 * @Create In 2012-2-21 By lee
	 * @param criterions
	 * @return LogicalExpression
	 */
	public static LogicalExpression or(Criterion... criterions){
		return new LogicalExpression("OR",criterions);
	}
}
