package com.dc.flamingo.core.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;

import com.dc.flamingo.core.dao.DataDao;
import com.dc.flamingo.core.support.BaseEntity;
import com.dc.flamingo.core.support.Page;
import com.dc.flamingo.core.support.PageParam;

/**
 * 基础数据Service
 * 
 * @Class Name DataService
 * @Author lee
 * @Create In 2012-3-16
 */
public class DataService<T> {
	@Autowired
	private DataDao<T> dataDao;

	@SuppressWarnings({ "unchecked", "hiding" })
	public <T>T getBean(Class<? extends BaseEntity> clazz,
			Map<String, String> paramsMap) {
		return (T) dataDao.getBean(clazz, paramsMap);
	}

	/**
	 * 获取指定类的数据
	 * 
	 * @Methods Name getList
	 * @Create In 2012-3-16 By lee
	 * @param clazz
	 *            类名
	 * @param paramsMap
	 *            参数
	 * @return List 返回结果集
	 */
	@SuppressWarnings("rawtypes")
	public List getList(Class<? extends BaseEntity> clazz,
			Map<String, String> paramsMap) {
		return dataDao.getList(clazz, paramsMap);
	}

	/**
	 * 获取指定类的分页数据
	 * 
	 * @Methods Name getGridPage
	 * @Create In 2012-3-16 By lee
	 * @param clazz
	 *            类名
	 * @param pageParam
	 *            分页参数
	 * @param paramsMap
	 *            数据参数
	 * @return Page 分页器
	 */
	public Page getGridPage(Class<? extends BaseEntity> clazz,
			PageParam pageParam, Map<String, String> paramsMap) {
		return dataDao.getGridPage(clazz, pageParam, paramsMap);
	}

	@SuppressWarnings({ "unchecked", "hiding" })
	public <T>T saveData(T obj) {
		return (T) dataDao.saveData(obj);
	}

	/**
	 * 删除指定类的数据
	 * 
	 * @Methods Name removeByIds
	 * @Create In 2012-3-16 By lee
	 * @param clazz
	 * @param dataIds
	 *            void
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public void removeByIds(Class clazz, String[] dataIds) {
		for (int i = 0; i < dataIds.length; i++) {
			dataDao.remove(clazz, Long.valueOf(dataIds[i]));
		}
	}

	/**
	 * 获取指定类的数据集
	 * 
	 * @Methods Name findListByField
	 * @Create In 2012-3-16 By lee
	 * @param clazz
	 * @param columnName
	 * @param arg
	 * @return List<T>
	 */
	public <T> List<T> findListByField(Class<T> clazz, String columnName,
			Object arg) {
		return dataDao.findListByField(clazz, columnName, arg);
	}

	/**
	 * 根据ID获取类对象
	 * 
	 * @Methods Name findById
	 * @Create In 2012-3-16 By lee
	 * @param clazz
	 * @param id
	 * @return T
	 */
	@SuppressWarnings({ "unchecked", "hiding" })
	public <T> T findById(Class<T> clazz, long id) {
		return (T) dataDao.findById(clazz, id);
	}

	/**
	 * 根据SQL及映射获取对象数据集
	 * 
	 * @Methods Name query
	 * @Create In 2012-3-16 By lee
	 * @param sql
	 * @param rowMapper
	 * @return List<T>
	 */
	public <T> List<T> query(String sql, RowMapper<T> rowMapper) {
		return dataDao.getJdbcTemplate().query(sql, rowMapper);
	}

	/**
	 * 根据指定类和属性参数获取类对象
	 * 
	 * @Methods Name findObjectByField
	 * @Create In 2012-3-16 By lee
	 * @param clazz
	 * @param columnName
	 * @param columnValue
	 * @return T
	 */
	public <T> T findObjectByField(Class<T> clazz, String columnName,
			Object columnValue) {
		return (T) dataDao.findObjectByField(clazz, columnName, columnValue);
	}

	/**
	 * 根据指定类和属性参数删除数据
	 * 
	 * @Methods Name removeByField
	 * @Create In 2012-3-16 By lee
	 * @param table
	 * @param fieldName
	 * @param fieldValue
	 *            void
	 */
	public void removeByField(String table, String fieldName, Object fieldValue) {
		dataDao.removeByField(table, fieldName, fieldValue);
	}

	/**
	 * 执行SQL参数
	 * 
	 * @Methods Name saveSqlExecute
	 * @Create In 2012-3-16 By lee
	 * @param sql
	 *            void
	 */
	public void saveSqlExecute(String sql) {
		dataDao.execute(sql);
	}

	public DataDao getDataDao() {
		return dataDao;
	}

	public void setDataDao(DataDao dataDao) {
		this.dataDao = dataDao;
	}

}
