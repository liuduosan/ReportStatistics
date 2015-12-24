package com.dc.assess.tablename.web;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.dc.assess.position.dto.JobCategoryDto;
import com.dc.assess.position.dto.RankDto;
import com.dc.assess.position.service.PositionService;
import com.dc.assess.tablename.dto.TableNameDto;
import com.dc.assess.tablename.service.TableNameService;
import com.dc.flamingo.core.support.AjaxResult;
import com.github.abel533.echarts.json.GsonUtil;

@Controller
@RequestMapping(value="/TableName")
public class TableNameController {

	@Autowired
	private TableNameService tableNameService;
	@Autowired
	private PositionService positionService;
	
	/**
	 * <p>
	 * <pre>
	 * <b>方法描述：</b>
	 * 	跳转报表查询页面
	 * <b>作者：</b>
	 * 	wangchao1(王超)
	 * 	邮箱：<a href="mailto:wangchao1@digitalchina.com" >wangchao1@digitalchina.com</a>	
	 * <b>创建时间：</b> 
	 * 	2015年12月11日 下午3:00:36
	 * </pre>
	 * </p>
	 */
	@RequestMapping(value="/toProcesslink")
	public ModelAndView toProcesslink(HttpServletRequest request){
		ModelAndView mav = new ModelAndView();
		mav.setViewName("/reportform");
		return mav;
	}
	
	/**
	 * <p>
	 * <pre>
	 * <b>方法描述：</b>
	 * 	跳转流程节奏页面
	 * <b>作者：</b>
	 * 	wangchao1(王超)
	 * 	邮箱：<a href="mailto:wangchao1@digitalchina.com" >wangchao1@digitalchina.com</a>	
	 * <b>创建时间：</b> 
	 * 	2015年12月15日 上午9:42:03
	 * </pre>
	 * </p>
	 */
	@RequestMapping(value="/toProcessRhythm")
	public ModelAndView toProcessRhythm(){
		ModelAndView mav = new ModelAndView();
		mav.setViewName("/processrhythm/processrhythm");
		return mav;
	}
	
	/**
	 * <p>
	 * <pre>
	 * <b>方法描述：</b>
	 * 	跳转业绩达成页面
	 * <b>作者：</b>
	 * 	wangchao1(王超)
	 * 	邮箱：<a href="mailto:wangchao1@digitalchina.com" >wangchao1@digitalchina.com</a>	
	 * <b>创建时间：</b> 
	 * 	2015年12月15日 上午9:46:56
	 * </pre>
	 * </p>
	 */
	@RequestMapping(value="/toResultsAchieved")
	public ModelAndView toResultsAchieved(){
		//查询下拉列表所有职位类别
		List<JobCategoryDto> JobCategorylist=positionService.getJobCategory();
		//查询下拉列表6级以内的级别
		List<RankDto> RankDtolist=positionService.getRank();
		ModelAndView mav = new ModelAndView();
		mav.addObject("JobCategorylist", JobCategorylist);
		mav.addObject("RankDtolist", RankDtolist);
		mav.setViewName("/resultsachieved/resultsachieved");
		return mav;
	}
	
	/**
	 * <p>
	 * <pre>
	 * <b>方法描述：</b>
	 * 	根据部门、月份、季度统计流程节奏对应的人数(饼状图)
	 * <b>作者：</b>
	 * 	wangchao1(王超)
	 * 	邮箱：<a href="mailto:wangchao1@digitalchina.com" >wangchao1@digitalchina.com</a>	
	 * <b>创建时间：</b> 
	 * 	2015年12月7日 下午1:55:54
	 * </pre>
	 * </p>
	 */
	@RequestMapping(value="/getByDeptNoProcessrhythmPie")
	@ResponseBody
	public AjaxResult getByDeptNoProcessrhythmPie(String deptno,String quarter,String month,String time){
		List<TableNameDto> list=tableNameService.getByDeptNoProcessrhythm(deptno, quarter, month,time);
		AjaxResult ajax=new AjaxResult();
		ajax.put("data", GsonUtil.format(list));
		ajax.put("success", true);
		return ajax;
	}
	
	/**
	 * <p>
	 * <pre>
	 * <b>方法描述：</b>
	 * 	根据部门、月份、季度统计流程节奏对应的人数(柱状图)
	 * <b>作者：</b>
	 * 	wangchao1(王超)
	 * 	邮箱：<a href="mailto:wangchao1@digitalchina.com" >wangchao1@digitalchina.com</a>	
	 * <b>创建时间：</b> 
	 * 	2015年12月8日 下午2:17:07
	 * </pre>
	 * </p>
	 */
	@RequestMapping(value="/getByDeptNoProcessrhythmBar")
	@ResponseBody
	public AjaxResult getByDeptNoProcessrhythmBar(String deptno,String quarter,String month,String time){
		List<TableNameDto> list=tableNameService.getByDeptNoProcessrhythm(deptno, quarter, month,time);
		List<String> nameArr = new ArrayList<String>();
		List<String> valueArr = new ArrayList<String>();
		for (int i = 0; i < list.size(); i++) {
			nameArr.add(list.get(i).getName());
			valueArr.add(list.get(i).getValue());
		}
		AjaxResult ajax=new AjaxResult();
		ajax.put("name", GsonUtil.format(nameArr));
		ajax.put("value", GsonUtil.format(valueArr));
		ajax.put("success", true);
		return ajax;
	}
	
	
	/**
	 * <p>
	 * <pre>
	 * <b>方法描述：</b>
	 * 	根据部门、职位代码、职位级别、季度、月份、时间统计业绩区间对应的人数数据(饼状图)
	 * <b>作者：</b>
	 * 	wangchao1(王超)
	 * 	邮箱：<a href="mailto:wangchao1@digitalchina.com" >wangchao1@digitalchina.com</a>	
	 * <b>创建时间：</b> 
	 * 	2015年12月8日 下午2:16:47
	 * </pre>
	 * </p>
	 */
	@RequestMapping(value="/getRankAnalysisPerformanceRangePie")
	@ResponseBody
	public AjaxResult getRankAnalysisPerformanceRangePie(String deptno,String quarter,String month,String gwjbmc,String time,String zwlb2dm){
		List<TableNameDto> list=tableNameService.getRankAnalysisPerformanceRange(deptno, quarter, month, gwjbmc, time, zwlb2dm);
		AjaxResult ajax=new AjaxResult();
		ajax.put("data", GsonUtil.format(list));
		ajax.put("success", true);
		return ajax;
	}
	
	/**
	 * <p>
	 * <pre>
	 * <b>方法描述：</b>
	 * 	根据部门、职位代码、职位级别、季度、月份、时间统计业绩区间对应的人数数据(柱状图)
	 * <b>作者：</b>
	 * 	wangchao1(王超)
	 * 	邮箱：<a href="mailto:wangchao1@digitalchina.com" >wangchao1@digitalchina.com</a>	
	 * <b>创建时间：</b> 
	 * 	2015年12月8日 下午2:20:39
	 * </pre>
	 * </p>
	 */
	@RequestMapping(value="/getRankAnalysisPerformanceRangeBar")
	@ResponseBody
	public AjaxResult getRankAnalysisPerformanceRangeBar(String deptno,String quarter,String month,String gwjbmc,String time,String zwlb2dm){
		List<TableNameDto> list=tableNameService.getRankAnalysisPerformanceRange(deptno, quarter, month, gwjbmc, time, zwlb2dm);
		List<String> nameArr = new ArrayList<String>();
		List<String> valueArr = new ArrayList<String>();
		for (int i = 0; i < list.size(); i++) {
			nameArr.add(list.get(i).getName());
			valueArr.add(list.get(i).getValue());
		}
		AjaxResult ajax=new AjaxResult();
		ajax.put("name", GsonUtil.format(nameArr));
		ajax.put("value", GsonUtil.format(valueArr));
		ajax.put("success", true);
		return ajax;
	}
	
	/**
	 * <p>
	 * <pre>
	 * <b>方法描述：</b>
	 * 	根据部门、职位类别、职位级别、季度、月份、时间统计综合考评对应的人数数据(饼状图)
	 * <b>作者：</b>
	 * 	wangchao1(王超)
	 * 	邮箱：<a href="mailto:wangchao1@digitalchina.com" >wangchao1@digitalchina.com</a>	
	 * <b>创建时间：</b> 
	 * 	2015年12月8日 下午2:24:53
	 * </pre>
	 * </p>
	 */
	@RequestMapping(value="/getRankAnalysisComprehensiveEvaluationPie")
	@ResponseBody
	public AjaxResult getRankAnalysisComprehensiveEvaluationPie(String deptno,String quarter,String month,String gwjbmc,String time,String zwlb2dm){
		List<TableNameDto> list=tableNameService.getRankAnalysisComprehensiveEvaluation(deptno, quarter, month, gwjbmc, time, zwlb2dm);
		AjaxResult ajax=new AjaxResult();
		ajax.put("data", GsonUtil.format(list));
		ajax.put("success", true);
		return ajax;
	}
	
	/**
	 * <p>
	 * <pre>
	 * <b>方法描述：</b>
	 * 	根据部门、职位类别、职位级别、季度、月份、时间统计综合考评对应的人数数据(柱状图)
	 * <b>作者：</b>
	 * 	wangchao1(王超)
	 * 	邮箱：<a href="mailto:wangchao1@digitalchina.com" >wangchao1@digitalchina.com</a>	
	 * <b>创建时间：</b> 
	 * 	2015年12月8日 下午2:26:36
	 * </pre>
	 * </p>
	 */
	@RequestMapping(value="/getRankAnalysisComprehensiveEvaluationBar")
	@ResponseBody
	public AjaxResult getRankAnalysisComprehensiveEvaluationBar(String deptno,String quarter,String month,String gwjbmc,String time,String zwlb2dm){
		List<TableNameDto> list=tableNameService.getRankAnalysisComprehensiveEvaluation(deptno, quarter, month, gwjbmc, time, zwlb2dm);
		List<String> nameArr = new ArrayList<String>();
		List<String> valueArr = new ArrayList<String>();
		for (int i = 0; i < list.size(); i++) {
			nameArr.add(list.get(i).getName());
			valueArr.add(list.get(i).getValue());
		}
		AjaxResult ajax=new AjaxResult();
		ajax.put("name", GsonUtil.format(nameArr));
		ajax.put("value", GsonUtil.format(valueArr));
		ajax.put("success", true);
		return ajax;
	}
	
	
//	/**
//	 * <p>
//	 * <pre>
//	 * <b>方法描述：</b>
//	 * 	按职级类别、部门、月份、季度统计业绩区间对应的人数数据(饼状图)
//	 * <b>作者：</b>
//	 * 	wangchao1(王超)
//	 * 	邮箱：<a href="mailto:wangchao1@digitalchina.com" >wangchao1@digitalchina.com</a>	
//	 * <b>创建时间：</b> 
//	 * 	2015年12月8日 下午2:29:58
//	 * </pre>
//	 * </p>
//	 */
//	@RequestMapping(value="/getJobClassAnalysisPerformanceRangePie")
//	@ResponseBody
//	public AjaxResult getJobClassAnalysisPerformanceRangePie(String deptno,String quarter,String month,String zwlb2dm,String time){
//		List<TableNameDto> list=tableNameService.getJobClassAnalysisPerformanceRange(deptno, quarter, month, zwlb2dm,time);
//		AjaxResult ajax=new AjaxResult();
//		ajax.put("data", GsonUtil.format(list));
//		ajax.put("success", true);
//		return ajax;
//	}
//	
//	/**
//	 * <p>
//	 * <pre>
//	 * <b>方法描述：</b>
//	 * 	按职级类别、部门、月份、季度统计业绩区间对应的人数数据(柱状图)
//	 * <b>作者：</b>
//	 * 	wangchao1(王超)
//	 * 	邮箱：<a href="mailto:wangchao1@digitalchina.com" >wangchao1@digitalchina.com</a>	
//	 * <b>创建时间：</b> 
//	 * 	2015年12月8日 下午2:30:25
//	 * </pre>
//	 * </p>
//	 */
//	@RequestMapping(value="/getJobClassAnalysisPerformanceRangeBar")
//	@ResponseBody
//	public AjaxResult getJobClassAnalysisPerformanceRangeBar(String deptno,String quarter,String month,String zwlb2dm,String time){
//		List<TableNameDto> list=tableNameService.getJobClassAnalysisPerformanceRange(deptno, quarter, month, zwlb2dm,time);
//		List<String> nameArr = new ArrayList<String>();
//		List<String> valueArr = new ArrayList<String>();
//		for (int i = 0; i < list.size(); i++) {
//			nameArr.add(list.get(i).getName());
//			valueArr.add(list.get(i).getValue());
//		}
//		AjaxResult ajax=new AjaxResult();
//		ajax.put("name", GsonUtil.format(nameArr));
//		ajax.put("value", GsonUtil.format(valueArr));
//		ajax.put("success", true);
//		return ajax;
//	}
//	
//	/**
//	 * <p>
//	 * <pre>
//	 * <b>方法描述：</b>
//	 * 	按职级类别、部门、月份、季度统计综合考评对应的人数数据(饼状图)
//	 * <b>作者：</b>
//	 * 	wangchao1(王超)
//	 * 	邮箱：<a href="mailto:wangchao1@digitalchina.com" >wangchao1@digitalchina.com</a>	
//	 * <b>创建时间：</b> 
//	 * 	2015年12月8日 下午2:32:51
//	 * </pre>
//	 * </p>
//	 */
//	@RequestMapping(value="/getJobClassAnalysisComprehensiveEvaluationPie")
//	@ResponseBody
//	public AjaxResult getJobClassAnalysisComprehensiveEvaluationPie(String deptno,String quarter,String month,String zwlb2dm,String time){
//		List<TableNameDto> list=tableNameService.getJobClassAnalysisComprehensiveEvaluation(deptno, quarter, month, zwlb2dm,time);
//		AjaxResult ajax=new AjaxResult();
//		ajax.put("data", GsonUtil.format(list));
//		ajax.put("success", true);
//		return ajax;
//	}
//	
//	/**
//	 * <p>
//	 * <pre>
//	 * <b>方法描述：</b>
//	 * 	按职级类别、部门、月份、季度统计综合考评对应的人数数据(柱状图)
//	 * <b>作者：</b>
//	 * 	wangchao1(王超)
//	 * 	邮箱：<a href="mailto:wangchao1@digitalchina.com" >wangchao1@digitalchina.com</a>	
//	 * <b>创建时间：</b> 
//	 * 	2015年12月8日 下午2:33:16
//	 * </pre>
//	 * </p>
//	 */
//	@RequestMapping(value="/getJobClassAnalysisComprehensiveEvaluationBar")
//	@ResponseBody
//	public AjaxResult getJobClassAnalysisComprehensiveEvaluationBar(String deptno,String quarter,String month,String zwlb2dm,String time){
//		List<TableNameDto> list=tableNameService.getJobClassAnalysisComprehensiveEvaluation(deptno, quarter, month, zwlb2dm,time);
//		List<String> nameArr = new ArrayList<String>();
//		List<String> valueArr = new ArrayList<String>();
//		for (int i = 0; i < list.size(); i++) {
//			nameArr.add(list.get(i).getName());
//			valueArr.add(list.get(i).getValue());
//		}
//		AjaxResult ajax=new AjaxResult();
//		ajax.put("name", GsonUtil.format(nameArr));
//		ajax.put("value", GsonUtil.format(valueArr));
//		ajax.put("success", true);
//		return ajax;
//	}
}
