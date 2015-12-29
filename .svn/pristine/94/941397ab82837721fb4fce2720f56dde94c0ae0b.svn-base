package com.dc.flamingo.core.support;

import java.util.HashMap;
import java.util.Map;

import com.dc.flamingo.util.BeanUtils;

/**
 * AJAX请求返回值
 * 为统一返回值，所有ajax请求全部使用此类对象返回
 * @Class Name AjaxResult
 * @Author lee
 * @Create In 2011-12-28
 */
public class AjaxResult extends HashMap<String, Object>{
	private static final long serialVersionUID = -7484781490745076286L;
	/**
	 * 获取成功返回值及提示信息
	 * @Methods Name successReslut
	 * @Create In 2012-2-24 By yaokuo
	 * @return AjaxResult
	 */
	public static AjaxResult successResult(String msg){
		AjaxResult ajaxResult = new AjaxResult();
		ajaxResult.put("success", true);
		ajaxResult.put("msg", msg);
		return ajaxResult;
	}
	/**
	 * 获取成功返回值
	 * @Methods Name successReslut
	 * @Create In 2011-12-28 By lee
	 * @return AjaxResult
	 */
	public static AjaxResult successResult(){
		AjaxResult ajaxResult = new AjaxResult();
		ajaxResult.put("success", true);
		ajaxResult.put("msg","");
		return ajaxResult;
	}
	/**
	 * 获取异常返回值
	 * @Methods Name errorReslut
	 * @Create In 2011-12-28 By lee
	 * @param error
	 * @return AjaxResult
	 */
	public static AjaxResult errorResult(String error){
		AjaxResult ajaxResult = new AjaxResult();
		ajaxResult.put("success", false);
		ajaxResult.put("error", error);
		return ajaxResult;
	}
	/**
	 * 获取分页返回值
	 * @Methods Name pageReslut
	 * @Create In 2011-12-30 By lee
	 * @param page
	 * @return AjaxResult
	 */
	public static AjaxResult pageResult(Page page){
		AjaxResult ajaxResult = new AjaxResult();
		ajaxResult.put("success", true);
		ajaxResult.put("total", page.getTotalCount());
		ajaxResult.put("rows", page.list());
		return ajaxResult;
	}
	/**
	 * 返回对象
	 * @Methods Name objectReslut
	 * @Create In 2011-12-30 By lee
	 * @param obj
	 * @return AjaxResult
	 */
	public static AjaxResult objectResult(Object obj){
		AjaxResult ajaxResult = new AjaxResult();
		ajaxResult.put("success", true);
		ajaxResult.put("data", obj);
		return ajaxResult;
	}
	
	/**
	 * 返回带有类名前缀属性名的表单数据
	 * @Methods Name classFormResult
	 * @Create In 2012-9-28 By lee
	 * @param prefix
	 * @param obj
	 * @return AjaxResult
	 */
	public static AjaxResult classFormResult(Object obj){
		Map<String,Object> dataMap = BeanUtils.objectToClassFormMap(obj);
		AjaxResult ajaxResult = new AjaxResult();
		ajaxResult.put("success", true);
		ajaxResult.put("data", dataMap);
		return ajaxResult;
	}
}
