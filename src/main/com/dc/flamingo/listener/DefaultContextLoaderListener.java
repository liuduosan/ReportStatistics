package com.dc.flamingo.listener;

import java.util.List;
import java.util.Locale;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.springframework.context.ApplicationContext;
import org.springframework.web.context.ContextLoaderListener;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.dc.assess.dept.dto.DeptStrucDto;
import com.dc.assess.dept.service.DeptStrucService;
import com.dc.flamingo.context.ContextHolder;
import com.dc.flamingo.util.LogUtils;

/**
 * Spring上下文装载listener
 * @Class Name DefaultContextLoaderListener
 * @Author lee
 * @Create In May 24, 2011
 */
public class DefaultContextLoaderListener extends ContextLoaderListener implements ServletContextListener{
	private LogUtils log = LogUtils.getLogUtil(this.getClass());
	@Override
	public void contextInitialized(ServletContextEvent event) {
		super.contextInitialized(event);
		ServletContext context = event.getServletContext();
		ApplicationContext ctx = WebApplicationContextUtils.getRequiredWebApplicationContext(context);
		ContextHolder.setApplicationContext(ctx);
		ContextHolder.setLocal(Locale.getDefault());
		log.info("=============创建Spring上下文完成==============");
		DeptStrucService bean = ctx.getBean(DeptStrucService.class);
		List<DeptStrucDto> DeptStrucDtolist = bean.createProdLayerTree();
		context.setAttribute("data", DeptStrucDtolist);
		log.info("=============加载部门信息完成==============");
	}
}
