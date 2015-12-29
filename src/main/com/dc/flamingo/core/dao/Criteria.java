package com.dc.flamingo.core.dao;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import com.dc.flamingo.core.support.Page;
import com.dc.flamingo.util.StringUtils;

/**
 * 条件查询器
 * 用于提供针对单个实体（表）进行条件查询的简便查询方式
 * @Class Name Criteria
 * @Author lee
 * @Create In 2012-2-8
 */
public class Criteria {
	private Class<?> clazz;
	private String table;
	private String dbType;
	private JdbcTemplate jdbcTemplate;
	private List<SimpleExpression> ses = new ArrayList<SimpleExpression>();
	private List<LogicalExpression> les = new ArrayList<LogicalExpression>();
	private List<Order> orders = new ArrayList<Order>();
	protected Class<?> getQueryClass(){
		return clazz;
	}
	/**
	 * 构造
	 * @param <T>
	 * @param jdbcTemplate
	 * @param clazz
	 */
	protected Criteria(JdbcTemplate jdbcTemplate, String dbType, Class<?> clazz) {
		this.jdbcTemplate=jdbcTemplate;
		this.clazz = clazz;
		this.dbType = dbType;
		try {
			Field tableField = clazz.getField("TABLE");
			table = tableField.get(clazz).toString();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 增加简单条件表达式
	 * @Methods Name add
	 * @Create In 2012-2-8 By lee
	 * @param expression0 void
	 */
	public void add(SimpleExpression expression0){
		ses.add(expression0);
	}
	
	/**
	 * 增加逻辑条件表达式
	 * @Methods Name add
	 * @Create In 2012-2-8 By lee
	 * @param expression0 void
	 */
	public void add(LogicalExpression expression0){
		les.add(expression0);
	}
	
	/**
	 * 增加排序条件
	 * @Methods Name addOrder
	 * @Create In 2012-2-8 By lee
	 * @param order0 void
	 */
	public void addOrder(Order order0){
		orders.add(order0);
	}
	
	/**
	 * 增加排序条件
	 * @Methods Name addOrder
	 * @Create In 2012-2-9 By lee
	 * @param propertyName
	 * @param ascending void
	 */
	public void addOrder(String propertyName,boolean ascending){
		orders.add(new Order(propertyName,ascending));
	}
	/**
	 * 返回条件查询实体的集合
	 * @Methods Name listObject
	 * @Create In 2012-2-8 By lee
	 * @return List<?>
	 */
	public List<?> listObject(){
		return jdbcTemplate.query(getSqlString(),BeanPropertyRowMapper.newInstance(clazz));
	}
	
	/**
	 * 返回条件查询MAP集合
	 * @Methods Name list
	 * @Create In 2012-2-8 By lee
	 * @return List<Map<String,Object>>
	 */
	public List<Map<String,Object>> list(){
		return jdbcTemplate.queryForList(getSqlString());
	}
	
	/**
	 * 根据分页条件返回分页查询结果
	 * @Methods Name page
	 * @Create In 2012-2-8 By lee
	 * @param pageNo
	 * @param pageSize
	 * @return Page
	 */
	public Page page(int pageNo, int pageSize){
		List<?> data = this.listObject(pageNo, pageSize);
		long start=(long)pageNo * pageSize;
		return new Page(start, this.getTotal(), pageSize, data);
	}
	
	/**
	 * 拼装SQL方法
	 * 内部方法，将条件查询最终查询语句拼装为SQL
	 * @Methods Name getSqlString
	 * @Create In 2012-2-8 By lee
	 * @return String
	 */
	private String getSqlString(){
		StringBuffer sqlBuffer = new StringBuffer();
		sqlBuffer.append("SELECT * FROM ");
		sqlBuffer.append(table);
		
		List<String> criterion = new ArrayList<String>();
		String tempSqlStr = null;
		for(SimpleExpression se : ses){
			tempSqlStr = se.toSqlString();
			if(StringUtils.isNotBlank(tempSqlStr)){
				criterion.add(tempSqlStr);
			}
		}
		for(LogicalExpression le : les){
			tempSqlStr = le.toSqlString();
			if(StringUtils.isNotBlank(tempSqlStr)){
				criterion.add(tempSqlStr);
			}
		}
		if(criterion.size()!=0){
			sqlBuffer.append(" WHERE ");
			sqlBuffer.append(StringUtils.join(criterion, " AND "));
		}
		List<String> sqlOrder = new ArrayList<String>();
		for(Order order : orders){
			sqlOrder.add(order.toSqlString());
		}
		if(sqlOrder.size()!=0){
			sqlBuffer.append(" ORDER BY ");
			sqlBuffer.append(StringUtils.join(sqlOrder, " , "));
		}
		return sqlBuffer.toString();
	}
	private String getSqlStringNoOrder(){
		StringBuffer sqlBuffer = new StringBuffer();
		sqlBuffer.append("SELECT * FROM ");
		sqlBuffer.append(table);
		
		List<String> criterion = new ArrayList<String>();
		String tempSqlStr = null;
		for(SimpleExpression se : ses){
			tempSqlStr = se.toSqlString();
			if(StringUtils.isNotBlank(tempSqlStr)){
				criterion.add(tempSqlStr);
			}
		}
		for(LogicalExpression le : les){
			tempSqlStr = le.toSqlString();
			if(StringUtils.isNotBlank(tempSqlStr)){
				criterion.add(tempSqlStr);
			}
		}
		if(criterion.size()!=0){
			sqlBuffer.append(" WHERE ");
			sqlBuffer.append(StringUtils.join(criterion, " AND "));
		}
		return sqlBuffer.toString();
	}
	/**
	 * 获取排序SQL
	 * @Methods Name getOrderSql
	 * @Create In 2012-2-8 By lee
	 * @return String
	 */
	private String getOrderSql(){
		List<String> sqlOrder = new ArrayList<String>();
		for(Order order : orders){
			sqlOrder.add(order.toSqlString());
		}
		if(sqlOrder.size()!=0){
			return " ORDER BY "+StringUtils.join(sqlOrder, " , ");
		}else{
			return " ORDER BY id DESC";
		}
		
	}
	
	/**
	 * 获取查询总条数
	 * @Methods Name getTotal
	 * @Create In 2012-2-8 By lee
	 * @return long
	 */
	private long getTotal() {
		String totalSql = "SELECT COUNT(*) FROM ("+getSqlStringNoOrder()+") a";
		return jdbcTemplate.queryForLong(totalSql);
	}
	
	/**
	 * 返回条件查询实体的集合
	 * @Methods Name listObject
	 * @Create In 2012-2-8 By lee
	 * @param pageNum
	 * @param pageSize
	 * @return List<?>
	 */
	private List<?> listObject(int pageNum, int pageSize){
		String sql = "";
		if(JdbcDao.DB_MYSQL.equals(dbType)){
			sql = getSqlString()+" LIMIT "+pageNum*pageSize+","+pageSize;
		}else if(JdbcDao.DB_MSSQL.equals(dbType)){
			StringBuffer pageSql = new StringBuffer();
			pageSql.append("WITH TEMP AS(SELECT ROW_NUMBER() OVER("+getOrderSql()+") AS RowNumber,*");
			pageSql.append(" FROM (SELECT * FROM ("+getSqlStringNoOrder()+") t) TEMP)");
			pageSql.append(" SELECT * FROM TEMP WHERE RowNumber");
			pageSql.append(" BETWEEN "+(pageNum*pageSize+1)+" AND "+(pageNum+1)*pageSize);
			sql = pageSql.toString();
		}else if(JdbcDao.DB_ORACLE.equals(dbType)){
			StringBuffer pageOracle=new StringBuffer();
			pageOracle.append("select * from (select a.*,rownum row_num from (");
			pageOracle.append(getSqlString());
			pageOracle.append(") a) b where b.row_num between "+(pageNum*pageSize+1)+" AND "+(pageNum+1)*pageSize);
			sql=pageOracle.toString();
		}
		return jdbcTemplate.query(sql,BeanPropertyRowMapper.newInstance(clazz));
	}
	
	/**
	 * 以对象形式返回单条结果
	 * @Methods Name uniqueResult
	 * @Create In 2012-2-8 By lee
	 * @return Object
	 */
	public Object uniqueResult(){
		try{
			return jdbcTemplate.queryForObject(getSqlString(),BeanPropertyRowMapper.newInstance(clazz));
		}catch(Exception e){
			//e.printStackTrace();
			return null;
		}
	}
}
