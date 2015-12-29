package com.dc.flamingo.core.dao;

import java.beans.BeanInfo;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;

import com.dc.flamingo.core.support.AppException;
import com.dc.flamingo.core.support.BaseEntity;
import com.dc.flamingo.core.support.Page;
import com.dc.flamingo.core.dao.Criteria;
import com.dc.flamingo.util.BeanUtils;
import com.dc.flamingo.util.StringUtils;
/**
 * DAO层基于JDBC实现基类
 * @Class Name BaseJdbcDao
 * @Author lee
 * @Create In Jun 15, 2011
 */
public class JdbcDao<T>{
	public static final String DB_MYSQL = "MySQL";
	public static final String DB_MSSQL = "Microsoft SQL Server";
	public static final String DB_ORACLE = "ORACLE";
	@Autowired
	private JdbcTemplate jdbcTemplate;
	private NamedParameterJdbcTemplate namedParameterJdbcTemplate;
	private String dbType;
	
	
	public void saveAll(Collection<?> entities,String[] paramName,String[] paramValue){
		Iterator<?> localIterator = entities.iterator(); 
		while(localIterator.hasNext()) {  
            Object entity = localIterator.next();
            if(paramName!=null && paramName.length>0 && paramValue!=null && paramValue.length>0){
            	for (int i=0; i<paramName.length; i++) {
            		String name = paramName[i];
            		String value = paramValue[i];
            		Method writeMethod = BeanUtils.getWriteMethod(entity, name);
            		try {
						writeMethod.invoke(entity, value);
					} catch (Exception e) {
						// TODO Auto-generated catch block
						throw new RuntimeException("向集合实体"+entity.getClass().getSimpleName()+":"+name+"中赋值出错，请检查set方法");
					} 
				}
            }
            
            save(entity);  
		}  
	}
	
	/**
	 * 保存
	 * @Methods Name save
	 * @Create In 2012-2-8 By lee
	 * @param <T>
	 * @param t
	 * @return T
	 */
	@SuppressWarnings("unchecked")
	public <T> T save(T t){
		if(t instanceof BaseEntity){	//如果是系统实体
			BaseEntity obj = (BaseEntity) t;
			if(obj.getOid()==null||obj.getOid()==0){
				obj.setOid(insertAndGetKey(t));	
				return (T) obj;
			}else{//对象已经保存过
				this.update(t, obj.getOid());
				return t;
			}
		}else{
			throw new AppException("发生异常,实体"+t.getClass().getName()+"没有继承BaseEntity");
		}
	}
	
	/**
	 * 保存并返回生成ID
	 * @Methods Name insertAndGetKey
	 * @Create In 2012-2-8 By lee
	 * @param <T>
	 * @param t
	 * @return long
	 */
	public <T> long insertAndGetKey(final T t) {
		if(DB_ORACLE.equals(dbType)){
			SimpleJdbcInsert simpleJdbcInsert = new SimpleJdbcInsert(jdbcTemplate).withTableName(getTable(t.getClass()));
			Map<String, Object> parameters = new HashMap<String, Object>();
			try {
				BeanInfo beanInfo = Introspector.getBeanInfo(t.getClass());
				for(PropertyDescriptor p: beanInfo.getPropertyDescriptors()){
					String propertyName = p.getName();
					Method method = p.getReadMethod();
					Object propertyValue = method.invoke(t, new Object[0]);
					if(propertyValue==null||"id".equals(propertyName)||"class".equals(propertyName)
							||propertyValue.getClass().isArray()
							||propertyValue instanceof Collection<?>
							||propertyValue instanceof Map<?,?>){
						continue;
					}
					parameters.put(propertyName, propertyValue);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
			String seqSql = "select "+getSequence(t.getClass())+".nextval from dual";
			Long oid = this.getJdbcTemplate().queryForLong(seqSql);
			parameters.put("oid", oid);
			simpleJdbcInsert.execute(parameters);
			return oid;
		}else{
			SimpleJdbcInsert simpleJdbcInsert = new SimpleJdbcInsert(jdbcTemplate).withTableName(getTable(t.getClass())).usingGeneratedKeyColumns("id");
			Map<String, Object> parameters = new HashMap<String, Object>();
			try {
				BeanInfo beanInfo = Introspector.getBeanInfo(t.getClass());
				for(PropertyDescriptor p: beanInfo.getPropertyDescriptors()){
					String propertyName = p.getName();
					Method method = p.getReadMethod();
					Object propertyValue = method.invoke(t, new Object[0]);
					if(propertyValue==null||"id".equals(propertyName)||"class".equals(propertyName)
							||propertyValue.getClass().isArray()
							||propertyValue instanceof Collection<?>
							||propertyValue instanceof Map<?,?>){
						continue;
					}
					parameters.put(propertyName, propertyValue);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
			Number newId = simpleJdbcInsert.executeAndReturnKey(parameters);
			return newId.longValue();
		}
	}  

	/**
	 * 更新
	 * @Methods Name update
	 * @Create In 2012-2-8 By lee
	 * @param <T>
	 * @param t
	 * @param id void
	 */
	public<T> void update(final T t,long id) {
		StringBuffer updateBuffer = new StringBuffer();
		updateBuffer.append("UPDATE ");
		updateBuffer.append(getTable(t.getClass()));
		updateBuffer.append(" SET ");
		List<String> param = new ArrayList<String>();
		List<Object> paramValue = new ArrayList<Object>();
		try {
			BeanInfo beanInfo = Introspector.getBeanInfo(t.getClass());
			for(PropertyDescriptor p: beanInfo.getPropertyDescriptors()){
				String propertyName = p.getName();
				Method method = p.getReadMethod();
				Object propertyValue = method.invoke(t, new Object[0]);
				if(propertyValue==null||"oid".equals(propertyName)||"class".equals(propertyName)
						||propertyValue.getClass().isArray()
						||propertyValue instanceof Collection<?>
						||propertyValue instanceof Map<?,?>){
					continue;
				}
				if(DB_MSSQL.equals(dbType)){
					param.add("["+propertyName+"]=?");
				}else{
					param.add(propertyName+"=?");
				}
				
				paramValue.add(propertyValue);
			}
			updateBuffer.append(StringUtils.join(param, ","));
		} catch (Exception e) {
			e.printStackTrace();
		}
		updateBuffer.append(" WHERE oid="+id);
		String sql = updateBuffer.toString();
		this.getJdbcTemplate().update(sql,paramValue.toArray());  
	}
	/**
	 * 根据ID获取数据，如果不存在，则返回null
	 * @Methods Name findById
	 * @Create In Oct 19, 2011 By lee
	 * @param <T>
	 * @param clazz
	 * @param id
	 * @return T
	 */
	public <T> T findById(Class<T> clazz,long id){
		try{
			String sql = "SELECT * FROM "+getTable(clazz)+" WHERE oid="+id;
			return this.getJdbcTemplate().queryForObject(sql, BeanPropertyRowMapper.newInstance(clazz));
		}catch(Exception e){
			//e.printStackTrace();
			return null;
		}
	}

	/**
	 * 按某个字段查询，返回一个对象实体
	 * @param <T>
	 * @param tableName
	 * @param clazz
	 * @param id
	 * @return
	 */
	public <T> T findObjectByField(Class<T> clazz,String fieldName,Object value){
		try{
			String sql = "SELECT * FROM "+getTable(clazz)+" WHERE "+fieldName+"=?";
			return this.getJdbcTemplate().queryForObject(sql,new Object[]{value}, BeanPropertyRowMapper.newInstance(clazz));
		}catch(Exception e){
			//e.printStackTrace();
			return null;
		}
	}
	/**
	 * 按某个字段查询，返回一个对象集合
	 * @param <T>
	 * @param tableName
	 * @param clazz
	 * @param id
	 * @return
	 */
	public <T> List<T> findListByField(Class<T> clazz,String fieldName,Object value){
		try{
			String sql = "SELECT * FROM "+getTable(clazz)+" WHERE "+fieldName+"=?";
			return this.getJdbcTemplate().query(sql,new Object[]{value}, BeanPropertyRowMapper.newInstance(clazz));
		}catch(Exception e){
			return null;
		}
	}
	/**
	 * 根据ID删除数据
	 * @Methods Name removeById
	 * @Create In Nov 7, 2011 By lee
	 * @param tableName
	 * @param id void
	 */
	public void removeById(String tableName,long id){
		String sql = "DELETE FROM "+tableName+" WHERE oid="+id;
		this.getJdbcTemplate().execute(sql);
	}
	/**
	 * 获取条件查询器
	 * @Methods Name getCriteria
	 * @Create In Nov 7, 2011 By lee
	 * @param clazz
	 * @return Criteria
	 */
	public <T> Criteria getCriteria(Class<? extends BaseEntity> clazz){
		return new Criteria(this.getJdbcTemplate(),this.getDbType(),clazz);
	}
	/**
	 * 分页查询
	 * @Methods Name pagedQuery
	 * @Create In 2012-2-8 By lee
	 * @param sql
	 * @param pageNum
	 * @param pageSize
	 * @return Page
	 */
	public Page pagedQuery(String sql, int pageNum, int pageSize) {
		int total = 0;
		List<Map<String,Object>> list = new ArrayList<Map<String,Object>>();
		if(DB_MYSQL.equals(dbType)){
			total = this.getJdbcTemplate().queryForInt("SELECT COUNT(*) FROM ("+sql+") a");
			sql = sql+" LIMIT "+pageNum*pageSize+","+pageSize;
			list = this.getJdbcTemplate().queryForList(sql);
		}else if(DB_MSSQL.equals(dbType)){
			throw new AppException(sql+"此分页查询方法不支持SQLSERVER，请使用带排序的方法");
		}else if(DB_ORACLE.equals(dbType)){
			StringBuffer pageSql = new StringBuffer();
			pageSql.append("select t2.*	from (select rownum r,t1.* from (").append(sql).append(") t1 where rownum<")
				   .append((pageNum+1)*pageSize).append(") t2 where t2.r>=").append(pageNum*pageSize);
			list = this.getJdbcTemplate().queryForList(pageSql.toString());
		}
		long start=(long)pageNum*pageSize;
		Page page = new Page(start,total,pageSize,list);
		return page;
	}
	/**
	 * 分页查询
	 * @Methods Name pagedQuery
	 * @Create In 2012-2-8 By lee
	 * @param sql
	 * @param orderColumn sqlserver数据库时需要传入排序字段才能分页
	 * @param pageNum
	 * @param pageSize
	 * @return Page
	 */
	public Page pagedQuery(String sql, String orderColumn, int pageNum, int pageSize) {
		int total = 0;
		List<Map<String,Object>> list = new ArrayList<Map<String,Object>>();
		if(DB_MYSQL.equals(dbType)){
			total = this.getJdbcTemplate().queryForInt("SELECT COUNT(*) FROM ("+sql+") a");
			sql = sql+" LIMIT "+pageNum*pageSize+","+pageSize;
			list = this.getJdbcTemplate().queryForList(sql);
		}else if(DB_MSSQL.equals(dbType)){
			StringBuffer pageSql = new StringBuffer();
			pageSql.append("WITH TEMP AS(SELECT ROW_NUMBER() OVER(ORDER BY "+orderColumn+") AS RowNumber,*");
			pageSql.append(" FROM (SELECT * FROM ("+sql+") t) TEMP)");
			pageSql.append(" SELECT * FROM TEMP WHERE RowNumber");
			pageSql.append(" BETWEEN "+(pageNum*pageSize+1)+" AND "+(pageNum+1)*pageSize);
			total = this.getJdbcTemplate().queryForInt("SELECT COUNT(*) FROM ("+sql+") a");
			list = this.getJdbcTemplate().queryForList(pageSql.toString());
		}else if(DB_ORACLE.equals(dbType)){
			StringBuffer pageSql = new StringBuffer();
			pageSql.append("select t2.*	from (select rownum r,t1.* from (").append(sql).append(") t1 where rownum<")
				   .append((pageNum+1)*pageSize).append(") t2 where t2.r>=").append(pageNum*pageSize);
			list = this.getJdbcTemplate().queryForList(pageSql.toString());
		}
		long start=(long)pageNum*pageSize;
		Page page = new Page(start,total,pageSize,list);
		return page;
	}
	
	/**
	 * 获取类对SEQUENCE
	 * @Methods Name getSequence
	 * @Create In 2012-11-9 By lee
	 * @param class1
	 * @return String
	 */
	private <T> String getSequence(Class<T> clazz) {
		Field tableField;
		try {
			tableField = clazz.getField("SEQUENCE");
			return tableField.get(clazz).toString();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	/**
	 * 获取类对应数据库表名
	 * @Methods Name getTable
	 * @Create In 2012-2-8 By lee
	 * @param <T>
	 * @param clazz
	 * @return String
	 */
	private <T> String getTable(final Class<T> clazz){
		Field tableField;
		try {
			tableField = clazz.getField("TABLE");
			return tableField.get(clazz).toString();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 获取类对应说有对象数据集合
	 * @Methods Name findAll
	 * @Create In 2012-2-8 By lee
	 * @param <T>
	 * @param clazz
	 * @return List<T>
	 */
	public <T> List<T> findAll(Class<T> clazz) {
		String sql = "SELECT * FROM "+getTable(clazz);
		return this.getJdbcTemplate().query(sql, BeanPropertyRowMapper.newInstance(clazz));
	}

	/**
	 * 根据属性删除表记录
	 * @Methods Name removeByField
	 * @Create In 2012-2-8 By lee
	 * @param tableName
	 * @param fieldName
	 * @param fieldValue void
	 */
	public void removeByField(String tableName, String fieldName, Object fieldValue) {
		String sql = "DELETE FROM "+tableName+" WHERE "+fieldName+"=?";
		this.getJdbcTemplate().update(sql, new Object[]{fieldValue});
	}

	/**
	 * 直接执行SQL
	 * @Methods Name execute
	 * @Create In 2012-2-8 By lee
	 * @param sql void
	 */
	public void execute(String sql) {
		this.getJdbcTemplate().execute(sql);
	}

	public JdbcTemplate getJdbcTemplate() {
		return jdbcTemplate;
	}

	public NamedParameterJdbcTemplate getNamedParameterJdbcTemplate() {
		return namedParameterJdbcTemplate;
	}

	public String getDbType() {
		return dbType;
	}
	
	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
		this.namedParameterJdbcTemplate = new NamedParameterJdbcTemplate(jdbcTemplate);
	}
	
	public void setDbType(String dbType){
		this.dbType = dbType;
	}
}
