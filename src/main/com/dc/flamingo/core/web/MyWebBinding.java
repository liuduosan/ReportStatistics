package com.dc.flamingo.core.web;

import java.util.Date;

import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.support.WebBindingInitializer;
import org.springframework.web.context.request.WebRequest;

/**
 * 处理spring mvc 映射
 * 从form表单数据到bean的映射
 * @Class Name MyWebBinding
 * @Author lee
 * @Create In Oct 12, 2011
 */
public class MyWebBinding implements WebBindingInitializer{

	@Override
	public void initBinder(WebDataBinder binder, WebRequest request) {
		//处理日期类型bind问题
		binder.registerCustomEditor(Date.class, new DateConvertEditor());
	}

}