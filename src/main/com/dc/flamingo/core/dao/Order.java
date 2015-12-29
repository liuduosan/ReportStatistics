package com.dc.flamingo.core.dao;

import java.io.Serializable;

/**
 * 排序
 * @Class Name Order
 * @Author lee
 * @Create In 2011-12-29
 */
public class Order implements Serializable {
	private static final long serialVersionUID = 2081828355594400792L;
	private boolean ascending;	//正序还是倒序
	private String propertyName;//排序字段名称

	public String toString() {
		return new StringBuilder().append(this.propertyName).append(' ')
				.append((this.ascending) ? "asc" : "desc").toString();
	}

	protected Order(String propertyName, boolean ascending) {
		this.propertyName = propertyName;
		this.ascending = ascending;
	}

	public String toSqlString(){
		StringBuffer fragment = new StringBuffer();
		fragment.append(propertyName);
		fragment.append((this.ascending) ? " ASC" : " DESC");
		return fragment.toString();
	}

	public static Order asc(String propertyName) {
		return new Order(propertyName, true);
	}

	public static Order desc(String propertyName) {
		return new Order(propertyName, false);
	}
}