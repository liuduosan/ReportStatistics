package com.dc.assess.dept.dao;

import java.util.List;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.stereotype.Repository;

import com.dc.flamingo.core.dao.DataDao;
import com.dc.assess.dept.dto.DeptStrucDataDto;

@Repository
public class DeptStrucDao extends DataDao<Object>{

	/**
	 * <p>
	 * <pre>
	 * <b>方法描述：</b>
	 * 	查询第一级节点
	 * <b>作者：</b>
	 * 	wangchao1(王超)
	 * 	邮箱：<a href="mailto:wangchao1@digitalchina.com" >wangchao1@digitalchina.com</a>	
	 * <b>创建时间：</b> 
	 * 	2015年11月30日 上午10:19:12
	 * </pre>
	 * </p>
	 */
	public List<DeptStrucDataDto> getDeptStrucLevel1(){
		StringBuffer sql=new StringBuffer();
		sql.append("select departNo1,departNo2,deptName,lev1,flatCode from deptStruc t where t.lev1='0'  order by t.lev1");
		return this.getJdbcTemplate().query(sql.toString(), BeanPropertyRowMapper.newInstance(DeptStrucDataDto.class));
	}

	/**
	 * <p>
	 * <pre>
	 * <b>方法描述：</b>
	 * 	根据子节点查询父节点
	 * <b>作者：</b>
	 * 	wangchao1(王超)
	 * 	邮箱：<a href="mailto:wangchao1@digitalchina.com" >wangchao1@digitalchina.com</a>	
	 * <b>创建时间：</b> 
	 * 	2015年11月30日 上午10:19:32
	 * </pre>
	 * </p>
	 */
	public List<DeptStrucDataDto> getChildrenByNum(String departNo2) {
		StringBuffer sql=new StringBuffer();
		sql.append("select departNo1,departNo2,deptName,lev1,flatCode from deptStruc t where t.departNo1='"+departNo2+"'  order by t.lev1");
		return this.getJdbcTemplate().query(sql.toString(), BeanPropertyRowMapper.newInstance(DeptStrucDataDto.class));
	}

	/**
	 * <p>
	 * <pre>
	 * <b>方法描述：</b>
	 * 	查询所有节点
	 * <b>作者：</b>
	 * 	wangchao1(王超)
	 * 	邮箱：<a href="mailto:wangchao1@digitalchina.com" >wangchao1@digitalchina.com</a>	
	 * <b>创建时间：</b> 
	 * 	2015年11月30日 上午10:20:35
	 * </pre>
	 * </p>
	 */
	public List<DeptStrucDataDto> getAllDatafromCache() {
		StringBuffer sql=new StringBuffer();
		sql.append("select distinct departNo2,departNo1,deptName,lev1 from deptStruc t");
		return this.getJdbcTemplate().query(sql.toString(), BeanPropertyRowMapper.newInstance(DeptStrucDataDto.class));
	}

}
