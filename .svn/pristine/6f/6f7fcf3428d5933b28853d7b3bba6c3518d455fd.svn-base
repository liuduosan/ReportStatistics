package com.dc.assess.exportreport.dao;

import java.util.List;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.stereotype.Repository;

import com.dc.assess.exportreport.dto.DownLoadDto;
import com.dc.flamingo.core.dao.DataDao;

@Repository
public class DownLoadDao extends DataDao<Object>{

	public List<DownLoadDto> getUserInfo(){
		StringBuffer sql=new StringBuffer();
		sql.append("select ItCode,UserName,deptname from DC_UserInfo order by ItCode");
		return this.getJdbcTemplate().query(sql.toString(), BeanPropertyRowMapper.newInstance(DownLoadDto.class));
	}

	public List<DownLoadDto> getHeadName(){
		StringBuffer sql=new StringBuffer();
		sql.append("select itcode,person_name from HeadName order by ItCode");
		return this.getJdbcTemplate().query(sql.toString(), BeanPropertyRowMapper.newInstance(DownLoadDto.class));
	}
	public List<DownLoadDto> getTableName(){
		StringBuffer sql=new StringBuffer();
		sql.append("select itcode,person_name from TableName order by ItCode");
		return this.getJdbcTemplate().query(sql.toString(), BeanPropertyRowMapper.newInstance(DownLoadDto.class));
	}
}
