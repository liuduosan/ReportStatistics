package com.dc.assess.dept.web;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.dc.flamingo.core.support.AjaxResult;
import com.dc.assess.dept.dto.DeptStrucDto;
import com.dc.assess.dept.service.DeptStrucService;
import com.dc.assess.validate.service.ValidateUserService;
import com.github.abel533.echarts.json.GsonUtil;

@Controller
@RequestMapping(value="/DeptStruc")
public class DeptStrucController {

	@Autowired
	private DeptStrucService deptStrucService;

	@Autowired
	private ValidateUserService validateUserService;
	
	/**
	 * <p>
	 * <pre>
	 * <b>方法描述：</b>
	 * 	查询所有部门创建树
	 * <b>作者：</b>
	 * 	wangchao1(王超)
	 * 	邮箱：<a href="mailto:wangchao1@digitalchina.com" >wangchao1@digitalchina.com</a>	
	 * <b>创建时间：</b> 
	 * 	2015年11月30日 上午10:21:00
	 * </pre>
	 * </p>
	 */
	@RequestMapping(value="/getDeptStruc")
	@ResponseBody
	public AjaxResult getDeptStruc(HttpServletRequest request){
		List<DeptStrucDto> list = (List<DeptStrucDto>) request.getSession().getServletContext().getAttribute("data");
		//获取用户itcode，根据itcode查询用户权限
		String itcode = "liutaoq";
		String checkedNodes=validateUserService.getItcode(itcode);
		AjaxResult result=new AjaxResult();
		if(list == null||list.size() == 0){
			List<DeptStrucDto> DeptStrucDtolist=deptStrucService.createProdLayerTree();
			String format = GsonUtil.format(DeptStrucDtolist);
			request.getSession().getServletContext().setAttribute("data", DeptStrucDtolist);
			result.put("format", format);
			result.put("checkedNodes", checkedNodes);
			result.put("success", true);
			return result;
		}else{
			String format = GsonUtil.format(list);
			result.put("format", format);
			result.put("checkedNodes", checkedNodes);
			result.put("success", true);
			return result;
		}
	}
}
