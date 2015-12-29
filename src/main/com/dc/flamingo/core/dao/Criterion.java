package com.dc.flamingo.core.dao;

import java.util.Map;

/**
 * 条件接口
 * 用户提供条件表达式接口
 * @Class Name Criterion
 * @Author lee
 * @Create In 2012-2-8
 */
public interface Criterion {
	 public abstract String toSqlString();
	 public abstract Map<String,Object> getParamMap();
}
