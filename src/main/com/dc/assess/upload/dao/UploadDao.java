package com.dc.assess.upload.dao;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.dc.flamingo.core.dao.DataDao;

/**
 * <p>
 * <pre>
 * <b>类描述：</b>
 * 	批量上传Dao
 * <b>作者：</b>
 * 	jiaocy1(焦春宇)
 * 	邮箱：<a href="mailto:jiaocy1@digitalchina.com" >jiaocy1@digitalchina.com</a>	
 * <b>创建时间：</b> 
 * 	2015年12月29日 下午2:46:29
 * </pre>
 * </p>
 */
@Repository
public class UploadDao extends DataDao<Object>{

	/**
	 * <p>
	 * <pre>
	 * <b>方法描述：</b>
	 * 	验证是否存在itcode
	 * <b>作者：</b>
	 * 	jiaocy1(焦春宇)
	 * 	邮箱：<a href="mailto:jiaocy1@digitalchina.com" >jiaocy1@digitalchina.com</a>	
	 * <b>创建时间：</b> 
	 * 	2015年12月29日 下午2:46:48
	 * </pre>
	 * </p>
	 */
	public boolean validateItcode(String itcode) {
		StringBuffer sql=new StringBuffer();
		sql.append("select ItCode from DC_UserInfo where itcode='"+itcode+"'");
		List<Map<java.lang.String, Object>> list = this.getJdbcTemplate().queryForList(sql.toString());
		if(list.isEmpty()){
			return false;
		}else{
			return true;
		}
	}

	/**
	 * <p>
	 * <pre>
	 * <b>方法描述：</b>
	 * 	验证是否存在部门编号
	 * <b>作者：</b>
	 * 	jiaocy1(焦春宇)
	 * 	邮箱：<a href="mailto:jiaocy1@digitalchina.com" >jiaocy1@digitalchina.com</a>	
	 * <b>创建时间：</b> 
	 * 	2015年12月29日 下午2:47:04
	 * </pre>
	 * </p>
	 */
	public boolean validateDeptNo(String deptno) {
		StringBuffer sql=new StringBuffer();
		sql.append("select DepartNo2 from DeptStruc where DepartNo2='"+deptno+"'");
		List<Map<java.lang.String, Object>> list = this.getJdbcTemplate().queryForList(sql.toString());
		if(list.isEmpty()){
			return false;
		}else{
			return true;
		}
	}

	
}
