package com.dc.assess.position.dao;

import java.util.List;
import java.util.Map;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.stereotype.Repository;

import com.dc.assess.position.dto.JobCategoryDto;
import com.dc.assess.position.dto.RankDto;
import com.dc.flamingo.core.dao.DataDao;
@Repository
public class PositionDao  extends DataDao<Object>{

	/**
	 * <p>
	 * <pre>
	 * <b>方法描述：</b>
	 * 	查询所有职位类别
	 * <b>作者：</b>
	 * 	wangchao1(王超)
	 * 	邮箱：<a href="mailto:wangchao1@digitalchina.com" >wangchao1@digitalchina.com</a>	
	 * <b>创建时间：</b> 
	 * 	2015年12月2日 下午4:36:13
	 * </pre>
	 * </p>
	 */
	public List<JobCategoryDto> getJobCategory(){
		StringBuffer sql=new StringBuffer();
		sql.append("select distinct zwlb2dm,zwlb2mc from Position where zwlb2mc is not null");
		return this.getJdbcTemplate().query(sql.toString(), BeanPropertyRowMapper.newInstance(JobCategoryDto.class));

	}
	
	/**
	 * <p>
	 * <pre>
	 * <b>方法描述：</b>
	 * 	查询6级以内的级别
	 * <b>作者：</b>
	 * 	wangchao1(王超)
	 * 	邮箱：<a href="mailto:wangchao1@digitalchina.com" >wangchao1@digitalchina.com</a>	
	 * <b>创建时间：</b> 
	 * 	2015年12月3日 上午9:30:35
	 * </pre>
	 * </p>
	 */
	public List<RankDto> getRank(){
		StringBuffer sql=new StringBuffer();
		sql.append("select distinct gwjbmc from Position where gwjbmc is not null and left(gwjbmc,1)<'7'  order by gwjbmc desc;");
		return this.getJdbcTemplate().query(sql.toString(), BeanPropertyRowMapper.newInstance(RankDto.class));
	}
	
	/**
	 * <p>
	 * <pre>
	 * <b>方法描述：</b>
	 * 	根据职位类别查询职位代码
	 * <b>作者：</b>
	 * 	wangchao1(王超)
	 * 	邮箱：<a href="mailto:wangchao1@digitalchina.com" >wangchao1@digitalchina.com</a>	
	 * <b>创建时间：</b> 
	 * 	2015年12月3日 上午10:49:49
	 * </pre>
	 * </p>
	 */
	public List<Map<String, Object>> getByJobCategory(String zwlb2dm){
		StringBuffer sql=new StringBuffer();
		sql.append("select zwdm from Position where zwlb2dm in ("+zwlb2dm+"); ");
		List<Map<String, Object>> list = this.getJdbcTemplate().queryForList(sql.toString());
		return list;
	}
	
	/**
	 * <p>
	 * <pre>
	 * <b>方法描述：</b>
	 * 	根据岗位级别名称查询职位代码
	 * <b>作者：</b>
	 * 	wangchao1(王超)
	 * 	邮箱：<a href="mailto:wangchao1@digitalchina.com" >wangchao1@digitalchina.com</a>	
	 * <b>创建时间：</b> 
	 * 	2015年12月8日 上午11:27:50
	 * </pre>
	 * </p>
	 */
	public List<Map<String, Object>> getByRankName(String gwjbmc){
		StringBuffer sql=new StringBuffer();
		sql.append("select zwdm from Position where gwjbmc in ("+gwjbmc+"); ");
		List<Map<String, Object>> list = this.getJdbcTemplate().queryForList(sql.toString());
		return list;
	}
}
