package com.dc.flamingo.core.dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.dc.flamingo.util.StringUtils;

/**
 * 逻辑条件表达式
 * 用于复杂条件时使用，如但属性多对应值的OR查询等
 * @Class Name LogicalExpression
 * @Author lee
 * @Create In 2012-2-8
 */
public class LogicalExpression implements Criterion
{
	  private Criterion[] criterion;	//逻辑表达式中包含的表达式
	  private final String op;			//操作符

	  protected LogicalExpression(String op,Criterion... criterions){
	    this.criterion = criterions;
	    this.op = op;
	  }

	  public String toSqlString(){
		  List<String> lc = new ArrayList<String>();
		  String tempSqlStr = null;
		 
		  
		  for(int i=0;i<this.criterion.length;i++){
			  tempSqlStr = criterion[i].toSqlString();
			  if(StringUtils.isNotBlank(tempSqlStr)){
				  lc.add(tempSqlStr);
			  }
		  }
		  if(lc.size()!=0){
			  StringBuffer fragment = new StringBuffer();
			  fragment.append('(');
			  if(lc.size()==1){
				  fragment.append(lc.get(0));
			  }else{
				  fragment.append(StringUtils.join(lc, " "+op+" "));
			  }
			  fragment.append(")");
			  return fragment.toString();
		  }else{
			  return null;
		  }
	  }
	  public Map<String, Object> getParamMap() {
		  Map<String,Object> paramMap = new HashMap<String,Object>();
		  for(int i=0;i<this.criterion.length;i++){
			  paramMap.putAll(criterion[i].getParamMap());
		  }
		  return paramMap;
	  }
	  public String getOp()
	  {
	    return this.op;
	  }
}