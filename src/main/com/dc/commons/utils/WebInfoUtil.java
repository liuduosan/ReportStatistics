package com.dc.commons.utils;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.dc.flamingo.util.BeanUtils;

public class WebInfoUtil {
	/**
	 * <p>
	 * <pre>
	 * <b>方法描述：</b>
	 * 	将页面参数封装到实体可以有多个
	 * <b>作者：</b>
	 * 	wangchao1(王超)
	 * 	邮箱：<a href="mailto:wangchao1@digitalchina.com" >wangchao1@digitalchina.com</a>	
	 * <b>创建时间：</b> 
	 * 	2015年11月6日 下午2:19:23
	 * </pre>
	 * </p>
	 */
	public static List<Object>  webParamToObj(Class<?> obj,HttpServletRequest request) {
		List<Object> list = new ArrayList<Object>();
		@SuppressWarnings("unchecked")
		Enumeration<String> parameterNames = request.getParameterNames();
		while(parameterNames.hasMoreElements()){
			Object nextElement = parameterNames.nextElement();
			System.out.println(nextElement.toString());
			String[] parameterValues = request.getParameterValues(nextElement.toString());
			for (int i=0;i<parameterValues.length ;i++) {
				Object newInstance = null;
				try {
					if(list.size()!=0 && list.size()!=i){
						newInstance = list.get(i);
					}
					if(newInstance == null){
						newInstance = obj.newInstance();
						list.add(newInstance);
					}
					
					Method writeMethod = BeanUtils.getWriteMethod(newInstance, nextElement.toString());
					if(writeMethod!=null){
						writeMethod.invoke(newInstance, parameterValues[i]);
					}
				} catch (Exception e) {
					// TODO Auto-generated catch block
					throw new RuntimeException("将页面param参数封装到实体"+obj.getSimpleName()+"出错！"+e.getMessage());
				}
				
			}
			
			
		}
		return list;
	}
}
