package com.dc.flamingo.core.support;

/**
 * 分页器参数
 * @Class Name PageParam
 * @Author lee
 * @Create In 2012-3-16
 */
public class PageParam {
	private int page = 1;	//页码
	private int rows = 10;	//每页显示行数
	private String order;	//排序关键字
	private boolean desc = true;	//是否是倒序排序
	public int getPageNum() {
		return page-1;
	}
	public int getPageSize() {
		return rows;
	}
	public int getPage() {
		return page;
	}
	public void setPage(int page) {
		this.page = page;
	}
	public int getRows() {
		return rows;
	}
	public void setRows(int rows) {
		this.rows = rows;
	}
	public String getOrder() {
		return order;
	}
	public void setOrder(String order) {
		this.order = order;
	}
	public boolean isDesc() {
		return desc;
	}
	public void setDesc(boolean desc) {
		this.desc = desc;
	}

	
}
