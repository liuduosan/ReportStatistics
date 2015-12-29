package com.dc.assess.position.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.dc.assess.position.dao.PositionDao;
import com.dc.assess.position.dto.JobCategoryDto;
import com.dc.assess.position.dto.RankDto;
import com.dc.flamingo.core.service.DataService;

@Service
@Transactional(readOnly=true)
public class PositionService extends DataService<Object>  {

	@Autowired
	private PositionDao positionDao;
	
	/**
	 * <p>
	 * <pre>
	 * <b>方法描述：</b>
	 * 	查询所有职位类别
	 * <b>作者：</b>
	 * 	wangchao1(王超)
	 * 	邮箱：<a href="mailto:wangchao1@digitalchina.com" >wangchao1@digitalchina.com</a>	
	 * <b>创建时间：</b> 
	 * 	2015年12月3日 上午10:03:33
	 * </pre>
	 * </p>
	 */
	public List<JobCategoryDto> getJobCategory(){
		List<JobCategoryDto> JobCategorylist=positionDao.getJobCategory();
		return JobCategorylist;
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
	 * 	2015年12月3日 上午10:49:03
	 * </pre>
	 * </p>
	 */
	public String getByJobCategory(String zwlb2dm){
		List<Map<String, Object>> list=positionDao.getByJobCategory(zwlb2dm);
		  String zwdm="";
		  for (int i = 0; i < list.size(); i++) {
				Map<String, Object> map = list.get(i);
				if(list.size()-1==i){
					zwdm+="'"+map.get("zwdm")+"'";
		    	}else{
		    		zwdm+="'"+map.get("zwdm")+"'" + ",";
		    	}
			}
		return zwdm;
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
	 * 	2015年12月3日 上午10:52:40
	 * </pre>
	 * </p>
	 */
	public List<RankDto> getRank(){
		List<RankDto> RankDtolist=positionDao.getRank();
		 return RankDtolist;
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
	 * 	2015年12月8日 上午11:29:18
	 * </pre>
	 * </p>
	 */
	public String getByRankName(String gwjbmc){
		List<Map<String, Object>> list=positionDao.getByRankName(gwjbmc);
		  String zwdm="";
		  for (int i = 0; i < list.size(); i++) {
				Map<String, Object> map = list.get(i);
				if(list.size()-1==i){
					zwdm+="'"+map.get("zwdm")+"'";
		    	}else{
		    		zwdm+="'"+map.get("zwdm")+"'" + ",";
		    	}
			}
		return zwdm;
	}
}
