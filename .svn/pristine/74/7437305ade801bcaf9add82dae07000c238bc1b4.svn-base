package com.dc.flamingo.core.support;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * 分页器
 * 提供获取集合数据，记录总数，分页数等方法。
 * @Class Name Page
 * @Author lee
 * @Create In May 12, 2011
 */
public class Page implements Serializable {

	private static final long serialVersionUID = 2039457293845L;

	private static final int DEFAULT_PAGE_SIZE = 20;

	private int pageSize = DEFAULT_PAGE_SIZE; 

	private long start; 	//起始
	
	private Object data; 	//分页器装载数据

	private long totalCount; //分页器总数据数量寄存

	public <T> Page() {
		this(0, 0, DEFAULT_PAGE_SIZE, new ArrayList<T>());
	}

	/**
	 * 分页器构造
	 * @param start
	 * @param totalSize
	 * @param pageSize
	 * @param data
	 */
	public Page(long start, long totalSize, int pageSize, Object data) {
		this.pageSize = pageSize;
		this.start = start;
		this.totalCount = totalSize;
		this.data = data;
	}
	/**
	 * 获取总数据数量
	 * @Methods Name getTotalCount
	 * @Create In May 12, 2011 By lee
	 * @return Long
	 */
	public Long getTotalCount() {
		return this.totalCount;
	}
	public void setTotalCount(int totalCount){
		this.totalCount=totalCount;
	}
	/**
	 * 获取总页数
	 * @Methods Name getTotalPageCount
	 * @Create In May 12, 2011 By lee
	 * @return Long
	 */
	public Long getTotalPageCount() {
		if (totalCount % pageSize == 0)
			return totalCount / pageSize;
		else
			return totalCount / pageSize + 1;
	}
	/**
	 * 获取当前每页显示数据条数
	 * @Methods Name getPageSize
	 * @Create In May 12, 2011 By lee
	 * @return int
	 */
	public int getPageSize() {
		return pageSize;
	}
	/**
	 * 获取分页器包含数据
	 * @Methods Name getResult
	 * @Create In May 12, 2011 By lee
	 * @return Object
	 */
	public Object getResult() {
		return data;
	}
	public Object getData() {
		return data;
	}

	public void setData(Object data) {
		this.data = data;
	}

	/**
	 * 获取分页器数据集
	 * @Methods Name list
	 * @Create In May 12, 2011 By lee
	 * @return List
	 */
	@SuppressWarnings("unchecked")
	public <T> List<T> list() {
		return (List<T>) data;
	}

	/**
	 * 获取当前显示的页码
	 * @Methods Name getCurrentPageNo
	 * @Create In May 12, 2011 By lee
	 * @return Long
	 */
	public Long getCurrentPageNo() {
		return start / pageSize + 1;
	}

	/**
	 * 判断是否有下一页
	 * @Methods Name hasNextPage
	 * @Create In May 12, 2011 By lee
	 * @return boolean
	 */
	public boolean hasNextPage() {
		return this.getCurrentPageNo() < this.getTotalPageCount() - 1;
	}

	/**
	 * 判断是否有前一页
	 * @Methods Name hasPreviousPage
	 * @Create In May 12, 2011 By lee
	 * @return boolean
	 */
	public boolean hasPreviousPage() {
		return this.getCurrentPageNo() > 1;
	}
	/**
	 * 获取分页器查询数据起始数，针对数据库而言，下标从0开始
	 * @Methods Name getStartOfPage
	 * @Create In May 12, 2011 By lee
	 * @param pageNo
	 * @param pageSize
	 * @return int
	 */
	public static int getStartOfPage(int pageNo, int pageSize) {
		return (pageNo - 1) * pageSize;
	}
	
}
