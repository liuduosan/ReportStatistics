package com.dc.flamingo.core.web;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.dc.flamingo.core.service.DataService;
import com.dc.flamingo.core.support.AjaxResult;
import com.dc.flamingo.core.support.BaseEntity;
import com.dc.flamingo.core.support.Constants;
import com.dc.flamingo.core.support.Page;
import com.dc.flamingo.core.support.PageParam;
import com.dc.flamingo.util.BeanUtils;
import com.dc.flamingo.util.HttpUtils;

/**
 * 共用数据控制器
 * 提供部分关于增删查功能
 * @Class Name CommonController
 * @Author lee
 * @Create In 2012-1-6
 */
@Controller
@RequestMapping("/data")
public class DataController {
	
	@Autowired
	private DataService<?> dataService;
	
	/**
	 * 获取对象数据
	 * @Methods Name getBean
	 * @Create In 2012-1-6 By lee
	 * @param request
	 * @return AjaxResult
	 */
	@RequestMapping(value="/getBean.do")   
	@ResponseBody
	public AjaxResult getBean(HttpServletRequest request){
		Map<String,String> paramsMap = HttpUtils.requestParam2Map(request);
		Class<? extends BaseEntity> clazz = getClass(paramsMap.get("clazz"));
		BaseEntity obj = dataService.getBean(clazz,paramsMap);
		return AjaxResult.objectResult(obj);
	}
	
	/**
	 * 获取select列表数据
	 * @Methods Name getSelect
	 * @Create In 2012-1-31 By lee
	 * @param request
	 * @return AjaxResult
	 */
	@RequestMapping(value="/getSelect.do")   
	@ResponseBody
	public AjaxResult getSelect(HttpServletRequest request){
		Map<String,String> paramsMap = HttpUtils.requestParam2Map(request);
		Class<? extends BaseEntity> clazz = getClass(paramsMap.get("clazz"));
		List<?> list = dataService.getList(clazz,paramsMap);
		return AjaxResult.objectResult(list);
	}
	/**
	 * 获取分页数据
	 * @Methods Name gridPage
	 * @Create In 2012-1-6 By lee
	 * @param pageParam
	 * @param request
	 * @return AjaxResult
	 */
	@RequestMapping(value="/gridPage.do")   
	@ResponseBody
	public AjaxResult gridPage(PageParam pageParam,HttpServletRequest request){
		Map<String,String> paramsMap = HttpUtils.requestParam2Map(request);
		Class<? extends BaseEntity> clazz = getClass(paramsMap.get("clazz"));
		Page p = dataService.getGridPage(clazz,pageParam,paramsMap);
		return AjaxResult.pageResult(p);
	}
	
	/**
	 * 保存数据
	 * @Methods Name save
	 * @Create In 2012-1-6 By lee
	 * @param request
	 * @return AjaxResult
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@RequestMapping(value="/save.do")   
	@ResponseBody
	public AjaxResult save(HttpServletRequest request){
		Map paramsMap = HttpUtils.requestParam2Map(request);
		Class<? extends BaseEntity> clazz = getClass(paramsMap.get("clazz").toString());
		BaseEntity obj = BeanUtils.mapToObject(paramsMap,clazz);
		obj = dataService.saveData(obj);
		return AjaxResult.objectResult(obj);
	}

	/**
	 * 删除数据
	 * @Methods Name remove
	 * @Create In 2012-1-6 By lee
	 * @param <T>
	 * @param clazz
	 * @param ids
	 * @return AjaxResult
	 */
	@RequestMapping(value="/remove.do")   
	@ResponseBody
	public <T> AjaxResult remove(@RequestParam("clazz") String clazz,@RequestParam("ids") String ids){
		String[] dataIds = ids.split(Constants.SEPARATOR); 
		Class<T> clazz0 = getClass(clazz);
		dataService.removeByIds(clazz0, dataIds);
		return AjaxResult.successResult();
	}
	
	@SuppressWarnings({"unchecked" })
	private <T> Class<T> getClass(final String className){
		try {
			return (Class<T>) Class.forName(className);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		return null;
	}
}
