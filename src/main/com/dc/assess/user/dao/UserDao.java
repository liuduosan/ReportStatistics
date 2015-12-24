package com.dc.assess.user.dao;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.PreparedStatementSetter;
import org.springframework.stereotype.Repository;

import com.dc.assess.user.service.Pagination;
import com.dc.flamingo.core.dao.DataDao;

@Repository
public class UserDao extends DataDao<Object> {
	private static final Logger logger = LoggerFactory.getLogger(UserDao.class);

	/**
	 * 根据UserName或者ITCode
	 * 
	 * @param userNo
	 * @param itcode
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<Map<String,Object>> queryByUserNoOrITCode(String UserName, String itcode,int currentPage, int numPerPage) {
		StringBuffer sql = new StringBuffer();
		sql.append("select  id,DC_UserInfo_Rights.itcode,checkedNodes,userno,UserName,"
				+ "Gender,IDCard,DC_UserInfo.FlatName,DC_UserInfo.FlatCode,DC_UserInfo.CompanyCode,CompanyName,DeptNO,DeptStruc.DeptName,"
				+ "TitleCode,TitleName,PostCode,Post,CostCenter,CostCenterName,author,description"
				+ "  from DC_UserInfo_Rights join DC_UserInfo on  DC_UserInfo_Rights.itcode=DC_UserInfo.itcode  "
				+ "join DeptStruc on  DC_UserInfo_Rights.checkedNodes=DeptStruc.DepartNo2 "
				+ "where 1=1  ");
		
		if(itcode!=null && !itcode.equals("")){
			sql.append(" and  DC_UserInfo.itcode like '%" + itcode
				+ "%'");
		}
		if(UserName!=null && !UserName.equals("")){
			sql.append(" and DC_UserInfo.UserName like '%" + UserName + "%'");
		}
		
		Pagination pageInfo = new Pagination(sql.toString(), currentPage, numPerPage, getJdbcTemplate());
		return pageInfo.getResultList();
		
	}
	
	public List<Map<String,Object>> queryByUserNameOrITCode(String UserName, String itcode) {
		StringBuffer sql = new StringBuffer();
		sql.append("select  itcode,UserName"
				+ "  from  DC_UserInfo  "
				+ "where 1=1  ");
		
		if(itcode!=null && !itcode.equals("")){
			sql.append(" and  DC_UserInfo.itcode like '%" + itcode
				+ "%'");
			return getJdbcTemplate().queryForList(sql.toString());
		}
		if(UserName!=null && !UserName.equals("")){
			sql.append(" and DC_UserInfo.UserName like '%" + UserName + "%'");
			return getJdbcTemplate().queryForList(sql.toString());
		}
		return null;
	}

	/**
	 * 计算分页记录总数
	 * @param UserNo
	 * @param itcode
	 * @return
	 */
	public int countByUserNoOrITCode(String UserName, String itcode) {
		StringBuffer sql = new StringBuffer();
		sql.append("select    count(*)"
				+ "  from DC_UserInfo_Rights join DC_UserInfo on  DC_UserInfo_Rights.itcode=DC_UserInfo.itcode  "
				+ "where 1=1  ");
		
		if(itcode!=null && !itcode.equals("")){
			sql.append(" and  DC_UserInfo.itcode like '%" + itcode
				+ "%'");
		}else{
			sql.append(" and DC_UserInfo.UserName like '%" + UserName + "%'");
		}
		return getJdbcTemplate().queryForInt(sql.toString());
	}

	/**
	 * null字符串处理
	 * @param obj
	 * @return
	 */
	public String Null(Object obj) {
		if (obj == null) {
			return "";
		} else {
			return obj.toString();
		}
	}
	public boolean isExist(String itcode,  String checkedNodes){
		String sql = "select count(*) from DC_UserInfo_Rights where itcode ='"+itcode+"' and checkedNodes='"+checkedNodes+"'";
		int i =  getJdbcTemplate().queryForInt(sql);
		if(i>0){
			return true;
		}else{
			return false;
		}
	}

	public void add(final String itcode, final String checkedNodes,final String author,final String description) {
		getJdbcTemplate().update("insert into DC_UserInfo_Rights values(?,?,?,?)", new PreparedStatementSetter() {
			
			@Override
			public void setValues(PreparedStatement ps) throws SQLException {
				ps.setString(1, itcode);
				ps.setString(2, checkedNodes);
				ps.setString(3, author);
				ps.setString(4, description);
			}
		});
	}
	
	public void update(final String itcode, final String checkedNodes,final String author,final String description,final String id) {
			getJdbcTemplate().update("update  DC_UserInfo_Rights set itcode=?,checkedNodes=?,author=?,description=? where id=?", new PreparedStatementSetter() {
			
			@Override
			public void setValues(PreparedStatement ps) throws SQLException {
				ps.setString(1, itcode);
				ps.setString(2, checkedNodes);
				ps.setString(3, author);
				ps.setString(4, description);
				ps.setString(5, id);
			}
		});
	}
	
	
	public List<String> findNodesByITCode(String itcode){
		String sql = "select checkedNodes from DC_UserInfo_Rights where itcode like '%"+itcode+"%'";
		return getJdbcTemplate().queryForList(sql, String.class);
	}
	
	public void delete(final String itcodes){
		String[] itcodesArray = itcodes.split(",");
		for (final String itcode : itcodesArray) {
			if(itcode!=null && !itcode.equals("")){
				getJdbcTemplate().update("delete from DC_UserInfo_Rights where id=?", 
						new PreparedStatementSetter() {
					@Override
					public void setValues(PreparedStatement ps) throws SQLException {
						ps.setString(1, itcode);
					}
				});
			}
		}
	}
}
