package com.dc.flamingo.core.aop;

import java.io.Serializable;

import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;

import com.dc.flamingo.core.support.AppException;
import com.dc.flamingo.util.LogUtils;

/**
 * 服务层拦截器
 * 处理日志，异常等信息
 * @Class Name ServiceInterceptor
 * @Author lee
 * @Create In Jul 1, 2011
 */
public class ServiceInterceptor implements MethodInterceptor, Serializable{

	/**
	 * @Field long serialVersionUID 
	 */
	private static final long serialVersionUID = -9182457678141869715L;

	/**
	 * Create a new SimpleTraceInterceptor with a static logger.
	 */
	public ServiceInterceptor() {
	}

	/**
     * 拦截主方法，环绕加强
     * 日志输出方法的输入输出以及异常信息
     * @Methods Name invoke
     * @param MethodInvocation 拦截方法
     * @return Object	方法返回值
     */
	public Object invoke(MethodInvocation invocation) throws Throwable {
		LogUtils logger = LogUtils.getLogUtil(invocation.getThis().getClass());
		String methodName = invocation.getMethod().getName();
		try {
			StringBuffer paramSb = new StringBuffer(); 
			Object[] param = invocation.getArguments();
			paramSb.append("[");
    		for(int i=0;i<param.length;i++){
    			if(param[i]==null){
    				paramSb.append("null");
    			}else{
    				paramSb.append(param[i].toString());
    			}
    			
    			if(i!=param.length-1){
    				paramSb.append(",");
    			}
    		}
    		paramSb.append("]");
			logger.debug("进入 " + methodName+" 参数:"+paramSb.toString());
			Object rval = invocation.proceed();
			if(rval!=null){
				logger.debug("离开 " + methodName+" 返回:["+rval.toString()+"]");
			}else{
				logger.debug("离开 " + methodName+" 返回:[null]");
			}
			
			return rval;
		}
		catch (Throwable ex) {
			logger.error("error in " + methodName + ex.getLocalizedMessage());
			if(!(ex instanceof AppException)){
				ex.printStackTrace();
			}
			throw ex;
		}
	}

}
