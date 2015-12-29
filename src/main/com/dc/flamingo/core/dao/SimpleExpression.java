package com.dc.flamingo.core.dao;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;

import com.dc.flamingo.util.DateUtils;

/**
 * 简单条件表达式
 * @Class Name Expression
 * @Author lee
 * @Create In 2011-12-29
 */
public class SimpleExpression implements Criterion{
	private final String columnName;	//数据库列名
	private final Object value;			//对应值
	private final String op;			//计算符
	private final boolean containNull;	//是否排空

	protected SimpleExpression(String columnName, Object value, String op) {
		this.columnName = columnName;
		this.value = value;
		this.op = op;
		this.containNull = true;
	}
	protected SimpleExpression(String columnName, Object value, String op, boolean containNull) {
		this.columnName = columnName;
		this.value = value;
		this.op = op;
		this.containNull = containNull;
	}

	public String toSqlString(){
		if(checkNull()){
			return null;
		}
		StringBuffer fragment = new StringBuffer();
		if (StringUtils.isNotBlank(columnName))
			fragment.append('(');
			fragment.append(this.columnName);
			fragment.append(" "+op+" ");
			fragment.append(getValueStr());
			fragment.append(")");
		return fragment.toString();
	}
	public Map<String, Object> getParamMap() {
		Map<String,Object> paramMap = new HashMap<String,Object>();
		if(value!=null){
			paramMap.put(columnName+"["+Integer.toHexString(value.hashCode())+"]", value);
		}
		return paramMap;
	}
	public boolean checkNull(){
		if(!containNull){
			if(value==null){
				return true;
			}else if(value instanceof String){
				String valueStr = (String) value;
				return StringUtils.isBlank(valueStr);
			}
		}
		return false;
		
	}
	String getValueStr(){
		if(value instanceof String){
			String valueStr = (String) value;
			if(StringUtils.isBlank(valueStr)){
				return "''";
			}else{
				return "'"+value+"'";
			}
		}
		if(value instanceof Date){
			if(value==null){
				return "null";
			}else{
				return "to_Date('"+DateUtils.convertDateTimeToString((Date)value)+"','yyyy-mm-dd hh24:mi:ss')";
			}
		}
		if(value==null){
			return null;
		}else{
			return value.toString();
		}	
	}
	public String toString() {
		return this.columnName + getOp() + this.value;
	}

	protected final String getOp() {
		return this.op;
	}
	
}
