package com.dc.assess.tablename.dao;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.dc.flamingo.core.dao.DataDao;

@Repository
public class TableNameDao  extends DataDao<Object> {
	
	/**
	 * <p>
	 * <pre>
	 * <b>方法描述：</b>
	 * 	根据部门、月份、季度统计流程节奏对应的人数
	 * <b>作者：</b>
	 * 	wangchao1(王超)
	 * 	邮箱：<a href="mailto:wangchao1@digitalchina.com" >wangchao1@digitalchina.com</a>	
	 * <b>创建时间：</b> 
	 * 	2015年12月3日 上午11:09:28
	 * </pre>
	 * </p>
	 * @param deptno : 部门
	 * @param quarter : 季度
	 * @param month : 月份
	 */
	public List<Map<String, Object>> getByDeptNoProcessrhythm(String deptno,String quarter,String month,String time){
		StringBuffer sql=new StringBuffer();
		sql.append("select count(*) as value,t.curnode as name from TableName t,DC_UserInfo u where t.itcode=u.itcode ");
		if(deptno!=null && !"".equals(deptno)){
			sql.append("and u.deptno in ("+deptno+") ");
		}
		if(quarter!=null && !"".equals(quarter)){
			sql.append("and t.jidu='"+quarter+"' ");
		}
		if(month!=null && !"".equals(month)){
			sql.append("and t.yuedu='"+month+"' ");
		}
		if(time!=null && !"".equals(time)){
			sql.append("and t.date='"+time+"' ");
		}
		sql.append("and t.curnode!='' and t.curnode is not null ");
		sql.append("group by t.curnode order by value;");
		List<Map<String, Object>> list = this.getJdbcTemplate().queryForList(sql.toString());
		return list;
	}

	/**
	 * <p>
	 * <pre>
	 * <b>方法描述：</b>
	 * 	根据部门、职位类别、职位级别、季度、月份、时间统计业绩区间对应的人数
	 * <b>作者：</b>
	 * 	wangchao1(王超)
	 * 	邮箱：<a href="mailto:wangchao1@digitalchina.com" >wangchao1@digitalchina.com</a>	
	 * <b>创建时间：</b> 
	 * 	2015年12月3日 下午1:40:02
	 * </pre>
	 * </p>
	 * @param deptno : 部门编号
	 * @param zwlb2dm : 职位类别
	 * @param quarter : 季度
	 * @param month : 月份
	 * @param time : 时间
	 * @param gwjbmc : 职位级别
	 */
	public List<Map<String, Object>> getByConditionPerformanceRange(String deptno,String zwlb2dm,String quarter,String month,String time,String gwjbmc){
		StringBuffer sql=new StringBuffer();
		sql.append("SELECT ");
		sql.append("CASE WHEN t.results >='140' THEN 'X>=140%' ");
		sql.append("WHEN t.results >='100' AND t.results < '140'  THEN '140%>X>=100%' ");
		sql.append("WHEN t.results >='80' AND t.results < '100'  THEN '100%>X>=80%' ");
		sql.append("WHEN t.results >='70' AND t.results < '80' THEN '80%>X>=70%' ");
		sql.append("WHEN t.results < '70' THEN '70%>X' ");
		sql.append("ELSE NULL END name,COUNT(*) as value ");
		sql.append("FROM TableName t,DC_UserInfo u where t.itcode=u.itcode ");
		if(deptno!=null && !"".equals(deptno)){
			sql.append("and u.deptno in ("+deptno+") ");
		}
		if(zwlb2dm!=null && !"".equals(zwlb2dm)){
			sql.append("and u.titlecode in ("+zwlb2dm+") ");
		}
		if(gwjbmc!=null && !"".equals(gwjbmc)){
			sql.append("and u.titlecode in ("+gwjbmc+") ");
		}
		if(quarter!=null && !"".equals(quarter)){
			sql.append("and t.jidu='"+quarter+"' ");
		}
		if(month!=null && !"".equals(month)){
			sql.append("and t.yuedu='"+month+"' ");
		}
		if(time!=null && !"".equals(time)){
			sql.append("and t.date='"+time+"' ");
		}
		sql.append("and t.results!='' and t.results is not null ");
		sql.append("GROUP BY ");
		sql.append("CASE WHEN t.results >='140' THEN 'X>=140%' ");
		sql.append("WHEN t.results >='100' AND t.results < '140'  THEN '140%>X>=100%' ");
		sql.append("WHEN t.results >='80' AND t.results < '100'  THEN '100%>X>=80%' ");
		sql.append("WHEN t.results >='70' AND t.results < '80' THEN '80%>X>=70%' ");
		sql.append("WHEN t.results < '70' THEN '70%>X' ");
		sql.append("ELSE NULL END order by value;");
		List<Map<String, Object>> list = this.getJdbcTemplate().queryForList(sql.toString());
		return list;
	}
	
	
	/**
	 * <p>
	 * <pre>
	 * <b>方法描述：</b>
	 * 	根据部门、职位类别、职位级别、季度、月份、时间统计综合考评对应的人数
	 * <b>作者：</b>
	 * 	wangchao1(王超)
	 * 	邮箱：<a href="mailto:wangchao1@digitalchina.com" >wangchao1@digitalchina.com</a>	
	 * <b>创建时间：</b> 
	 * 	2015年12月3日 下午2:07:55
	 * </pre>
	 * </p>
	 * @param deptno : 部门编号
	 * @param zwlb2dm : 职位类别
	 * @param quarter : 季度
	 * @param month : 月份
	 * @param time : 时间
	 * @param gwjbmc : 职位级别
	 */
	public List<Map<String, Object>> getByConditionComprehensiveEvaluation(String deptno,String zwlb2dm,String quarter,String month,String time,String gwjbmc){
		StringBuffer sql=new StringBuffer();
		sql.append("select count(*) as value,t.evaluation_level as name from TableName t,DC_UserInfo u ");
		sql.append("where t.itcode=u.itcode ");
		if(deptno!=null && !"".equals(deptno)){
			sql.append("and u.deptno in ("+deptno+") ");
		}
		if(zwlb2dm!=null && !"".equals(zwlb2dm)){
			sql.append("and u.titlecode in ("+zwlb2dm+") ");
		}
		if(gwjbmc!=null && !"".equals(gwjbmc)){
			sql.append("and u.titlecode in ("+gwjbmc+") ");
		}
		if(quarter!=null && !"".equals(quarter)){
			sql.append("and t.jidu='"+quarter+"' ");
		}
		if(month!=null && !"".equals(month)){
			sql.append("and t.yuedu='"+month+"' ");
		}
		if(time!=null && !"".equals(time)){
			sql.append("and t.date='"+time+"' ");
		}
		sql.append("and t.evaluation_level!='' and t.evaluation_level is not null ");
		sql.append("GROUP BY t.evaluation_level order by value;");
		List<Map<String, Object>> list = this.getJdbcTemplate().queryForList(sql.toString());
		return list;
	}
	
}
