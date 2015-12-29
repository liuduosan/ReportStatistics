/**
 * 
 */
package com.dc.flamingo.context;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;



/**
 * <p>
 * <pre>
 * <b>类描述：</b>
 * 	拦截没有登录用户
 * <b>作者：</b>
 * 	wangchao1(王超)
 * 	邮箱：<a href="mailto:wangchao1@digitalchina.com" >wangchao1@digitalchina.com</a>	
 * <b>创建时间：</b> 
 * 	2015年12月11日 下午1:56:12
 * </pre>
 * </p>
 */
public class UserInterceptor extends HandlerInterceptorAdapter {

	/* (non-Javadoc)
	 * @see org.springframework.web.servlet.HandlerInterceptor#afterCompletion(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse, java.lang.Object, java.lang.Exception)
	 */
	@Override
	public void afterCompletion(HttpServletRequest arg0,
			HttpServletResponse arg1, Object arg2, Exception arg3)
			throws Exception {
		// TODO Auto-generated method stub
		
	}

	/* (non-Javadoc)
	 * @see org.springframework.web.servlet.HandlerInterceptor#postHandle(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse, java.lang.Object, org.springframework.web.servlet.ModelAndView)
	 */
	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response,
			Object arg2, ModelAndView mav) throws Exception {
		// TODO Auto-generated method stub
		//response.sendRedirect(request.getContextPath()+"/login.jsp");
	}

	/* (non-Javadoc)
	 * @see org.springframework.web.servlet.HandlerInterceptor#preHandle(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse, java.lang.Object)
	 */
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response,
			Object obj) throws Exception {
		String itcode = request.getHeader("iv-user");
		if(itcode == null){
			response.sendRedirect("http://dcone.digitalchina.com/dcoa7/DCPerformance2015/DigiFlowAssessRelation.nsf/DCPFrame?OpenForm");
			return false;
		}
		return super.preHandle(request, response, obj);
	}

}
