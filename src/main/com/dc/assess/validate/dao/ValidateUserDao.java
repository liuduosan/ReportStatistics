package com.dc.assess.validate.dao;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.dc.flamingo.core.dao.DataDao;

@Repository
public class ValidateUserDao extends DataDao<Object>{

	/**
	 * <p>
	 * <pre>
	 * <b>方法描述：</b>
	 * 	根据itcode查询权限
	 * <b>作者：</b>
	 * 	wangchao1(王超)
	 * 	邮箱：<a href="mailto:wangchao1@digitalchina.com" >wangchao1@digitalchina.com</a>	
	 * <b>创建时间：</b> 
	 * 	2015年12月17日 上午11:14:04
	 * </pre>
	 * </p>
	 */
	public List<Map<java.lang.String, Object>> validateItcode(String itcode) {
		StringBuffer sql=new StringBuffer();
		sql.append("select checkedNodes from DC_UserInfo_Rights where itcode='"+itcode+"'");
		List<Map<java.lang.String, Object>> list = this.getJdbcTemplate().queryForList(sql.toString());
		return list;
	}
	
	/**
	 * <p>
	 * <pre>
	 * <b>方法描述：</b>
	 * 	根据itcode查询是否是系统管理员
	 * <b>作者：</b>
	 * 	wangchao1(王超)
	 * 	邮箱：<a href="mailto:wangchao1@digitalchina.com" >wangchao1@digitalchina.com</a>	
	 * <b>创建时间：</b> 
	 * 	2015年12月17日 上午11:15:36
	 * </pre>
	 * </p>
	 */
	public List<Map<java.lang.String, Object>> getItcodeAdministrators(String itcode) {
		StringBuffer sql=new StringBuffer();
		sql.append("select itcode from DC_Administrators where itcode='"+itcode+"'");
		List<Map<java.lang.String, Object>> list = this.getJdbcTemplate().queryForList(sql.toString());
		return list;
	}
}
