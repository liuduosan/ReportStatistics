package com.dc.assess.tablename.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.dc.assess.position.dao.PositionDao;
import com.dc.assess.position.service.PositionService;
import com.dc.assess.tablename.dao.TableNameDao;
import com.dc.assess.tablename.dto.TableNameDto;
import com.dc.flamingo.core.service.DataService;
import com.dc.flamingo.util.BeanUtils;

@Service
@Transactional(readOnly=true)
public class TableNameService extends DataService<Object>{

	@Autowired
	private PositionDao positionDao;
	@Autowired
	private TableNameDao tableNameDao;
	@Autowired
	private PositionService positionService;
	
	/**
	 * <p>
	 * <pre>
	 * <b>方法描述：</b>
	 * 	根据部门、月份、季度统计流程节奏对应的人数
	 * <b>作者：</b>
	 * 	wangchao1(王超)
	 * 	邮箱：<a href="mailto:wangchao1@digitalchina.com" >wangchao1@digitalchina.com</a>	
	 * <b>创建时间：</b> 
	 * 	2015年12月7日 下午1:42:55
	 * </pre>
	 * </p>
	 */
	public List<TableNameDto> getByDeptNoProcessrhythm(String deptno,String quarter,String month,String time){
		List<Map<String, Object>> list=tableNameDao.getByDeptNoProcessrhythm(deptno, quarter, month,time);
		List<TableNameDto> tableNameDtolist=new ArrayList<TableNameDto>();
		for (int i = 0; i < list.size(); i++) {
			Map<String, Object> map = list.get(i);
			TableNameDto object=new TableNameDto();
			object = BeanUtils.mapToObject(map,TableNameDto.class);
			tableNameDtolist.add(object);
		}
		return tableNameDtolist;
	}
	
	
	/**
	 * <p>
	 * <pre>
	 * <b>方法描述：</b>
	 * 	根据部门、职位代码、职位级别、季度、月份、时间统计业绩区间对应的人数数据
	 * <b>作者：</b>
	 * 	wangchao1(王超)
	 * 	邮箱：<a href="mailto:wangchao1@digitalchina.com" >wangchao1@digitalchina.com</a>	
	 * <b>创建时间：</b> 
	 * 	2015年12月8日 上午10:29:12
	 * </pre>
	 * </p>
	 */
	public List<TableNameDto> getRankAnalysisPerformanceRange(String deptno,String quarter,String month,String gwjbmc,String time,String zwlb2dm){
		//根据职位级别名称查询对应职位代码
		if(gwjbmc!=null && !"".equals(gwjbmc)){
			gwjbmc=positionService.getByRankName(gwjbmc);
		}
		//根据职位类别名称查询对应职位代码
		if(zwlb2dm!=null && !"".equals(zwlb2dm)){
			zwlb2dm=positionService.getByJobCategory(zwlb2dm);
		}
		List<Map<String, Object>> list=tableNameDao.getByConditionPerformanceRange(deptno, zwlb2dm, quarter, month, time, gwjbmc);
		List<TableNameDto> tableNameDtolist=new ArrayList<TableNameDto>();
		for (int i = 0; i < list.size(); i++) {
			Map<String, Object> map = list.get(i);
			TableNameDto object=new TableNameDto();
			object = BeanUtils.mapToObject(map,TableNameDto.class);
			tableNameDtolist.add(object);
		}
		return tableNameDtolist;
	}
	
	/**
	 * <p>
	 * <pre>
	 * <b>方法描述：</b>
	 * 	按职级类别、部门、月份、季度统计业绩区间对应的人数数据
	 * <b>作者：</b>
	 * 	wangchao1(王超)
	 * 	邮箱：<a href="mailto:wangchao1@digitalchina.com" >wangchao1@digitalchina.com</a>	
	 * <b>创建时间：</b> 
	 * 	2015年12月8日 下午1:33:34
	 * </pre>
	 * </p>
	 */
//	public List<TableNameDto> getJobClassAnalysisPerformanceRange(String deptno,String quarter,String month,String zwlb2dm,String time){
//		String zwdm=positionService.getByJobCategory(zwlb2dm);
//		List<Map<String, Object>> list=tableNameDao.getByConditionPerformanceRange(deptno, zwdm, quarter, month,time);
//		List<TableNameDto> tableNameDtolist=new ArrayList<TableNameDto>();
//		for (int i = 0; i < list.size(); i++) {
//			Map<String, Object> map = list.get(i);
//			TableNameDto object=new TableNameDto();
//			object = BeanUtils.mapToObject(map,TableNameDto.class);
//			tableNameDtolist.add(object);
//		}
//		return tableNameDtolist;
//	}
	
	/**
	 * <p>
	 * <pre>
	 * <b>方法描述：</b>
	 * 	根据部门、职位类别、职位级别、季度、月份、时间统计综合考评对应的人数数据
	 * <b>作者：</b>
	 * 	wangchao1(王超)
	 * 	邮箱：<a href="mailto:wangchao1@digitalchina.com" >wangchao1@digitalchina.com</a>	
	 * <b>创建时间：</b> 
	 * 	2015年12月8日 下午2:05:08
	 * </pre>
	 * </p>
	 */
	public List<TableNameDto> getRankAnalysisComprehensiveEvaluation(String deptno,String quarter,String month,String gwjbmc,String time,String zwlb2dm){
		//根据职位级别名称查询对应职位代码
		if(gwjbmc!=null && !"".equals(gwjbmc)){
			gwjbmc=positionService.getByRankName(gwjbmc);
		}
		//根据职位类别名称查询对应职位代码
		if(zwlb2dm!=null && !"".equals(zwlb2dm)){
			zwlb2dm=positionService.getByJobCategory(zwlb2dm);
		}
		List<Map<String, Object>> list=tableNameDao.getByConditionComprehensiveEvaluation(deptno, zwlb2dm, quarter, month, time, gwjbmc);
		List<TableNameDto> tableNameDtolist=new ArrayList<TableNameDto>();
		for (int i = 0; i < list.size(); i++) {
			Map<String, Object> map = list.get(i);
			TableNameDto object=new TableNameDto();
			object = BeanUtils.mapToObject(map,TableNameDto.class);
			tableNameDtolist.add(object);
		}
		return tableNameDtolist;
	}
	
	/**
	 * <p>
	 * <pre>
	 * <b>方法描述：</b>
	 * 	按职级类别、部门、月份、季度统计综合考评对应的人数数据
	 * <b>作者：</b>
	 * 	wangchao1(王超)
	 * 	邮箱：<a href="mailto:wangchao1@digitalchina.com" >wangchao1@digitalchina.com</a>	
	 * <b>创建时间：</b> 
	 * 	2015年12月8日 下午2:06:57
	 * </pre>
	 * </p>
	 */
//	public List<TableNameDto> getJobClassAnalysisComprehensiveEvaluation(String deptno,String quarter,String month,String zwlb2dm,String time){
//		String zwdm=positionService.getByJobCategory(zwlb2dm);
//		List<Map<String, Object>> list=tableNameDao.getByConditionComprehensiveEvaluation(deptno, zwdm, quarter, month,time);
//		List<TableNameDto> tableNameDtolist=new ArrayList<TableNameDto>();
//		for (int i = 0; i < list.size(); i++) {
//			Map<String, Object> map = list.get(i);
//			TableNameDto object=new TableNameDto();
//			object = BeanUtils.mapToObject(map,TableNameDto.class);
//			tableNameDtolist.add(object);
//		}
//		return tableNameDtolist;
//	}
	
}
