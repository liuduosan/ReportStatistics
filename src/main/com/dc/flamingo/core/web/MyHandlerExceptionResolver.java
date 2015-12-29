package com.dc.flamingo.core.web;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

import com.dc.flamingo.core.support.AppException;
import com.dc.flamingo.util.JsonUtils;
import com.dc.flamingo.util.LogUtils;

/**
 * 异常处理解析器
 * 用于统一处理spring mvc的异常处理
 * @Class Name MyHandlerExceptionResolver
 * @Author lee
 * @Create In 2012-3-16
 */
public class MyHandlerExceptionResolver implements HandlerExceptionResolver{
	private transient LogUtils log=LogUtils.getLogUtil(this.getClass());
	public ModelAndView resolveException(HttpServletRequest request,
			HttpServletResponse response, Object arg2, Exception ex) {
		String requestType =(String) ((HttpServletRequest) request).getHeader("X-Requested-With");   
		Map<String,Object> model = new HashMap<String,Object>();  
        model.put("ex", ex.getClass().getSimpleName());  
        model.put("error", ex.getMessage());  
		log.error("====================Controller-ERROR-["+request.getRequestURI()+"]-BEGIN====================");
		log.error(ex.toString());
		if(!(ex instanceof AppException)){
			ex.printStackTrace();
		}
        log.error("====================Controller-ERROR-["+request.getRequestURI()+"]-END====================");
        //如果是ajax请求，response回写类型的异常处理，将异常写入response中提供前台处理
        if("XMLHttpRequest".equals(requestType)){
			String json = JsonUtils.errorJson(ex.toString());
			response.setContentType("text/plain");
			response.setCharacterEncoding("UTF-8");
			PrintWriter out;
			try {
				out = response.getWriter();
				out.println(json);
				out.flush();
				out.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
			return null;
	    }else{	//跳转类异常处理，直接跳转至异常提示页面
			return new ModelAndView("/error", model);  
		}
	}

}