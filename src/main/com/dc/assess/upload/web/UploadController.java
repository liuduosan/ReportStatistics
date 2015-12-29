package com.dc.assess.upload.web;

import java.io.OutputStream;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.dc.assess.upload.service.UploadService;
import com.dc.assess.user.dto.UserManage;
import com.dc.flamingo.core.support.AjaxResult;
import com.dc.flamingo.core.support.AppException;


/**
 * <p>
 * <pre>
 * <b>类描述：</b>
 * 	批量上传excle的controller，包括下载模板
 * <b>作者：</b>
 * 	jiaocy1(焦春宇)
 * 	邮箱：<a href="mailto:jiaocy1@digitalchina.com" >jiaocy1@digitalchina.com</a>	
 * <b>创建时间：</b> 
 * 	2015年12月29日 下午2:47:32
 * </pre>
 * </p>
 */
@Controller
@RequestMapping(value="/upload1")
public class UploadController {
	@Autowired
	private UploadService uploadService;

	/**
	 * <p>
	 * <pre>
	 * <b>方法描述：</b>
	 * 	批量上传excle
	 * <b>作者：</b>
	 * 	jiaocy1(焦春宇)
	 * 	邮箱：<a href="mailto:jiaocy1@digitalchina.com" >jiaocy1@digitalchina.com</a>	
	 * <b>创建时间：</b> 
	 * 	2015年12月29日 下午2:48:19
	 * </pre>
	 * </p>
	 */
	@RequestMapping(value="/uploaddeal",method=RequestMethod.POST)
	@ResponseBody
	public AjaxResult uploadExcel(HttpServletRequest request){
		
		System.out.println("进入数据处理页面");
		try{
		List<UserManage> errorList =uploadService.uploadExcel((String)request.getParameter("fileName"));
		return AjaxResult.objectResult(errorList);
		}catch (AppException e) {
		return AjaxResult.errorResult(e.getMessage());
		}
	}
	/**
	 * <p>
	 * <pre>
	 * <b>方法描述：</b>
	 * 	下载excel模板
	 * <b>作者：</b>
	 * 	jiaocy1(焦春宇)
	 * 	邮箱：<a href="mailto:jiaocy1@digitalchina.com" >jiaocy1@digitalchina.com</a>	
	 * <b>创建时间：</b> 
	 * 	2015年12月29日 下午2:48:41
	 * </pre>
	 * </p>
	 */
	@RequestMapping(value = "/downloadExcel")
	public void downloadExcel(HttpServletResponse response) {       
		HSSFWorkbook wb =  uploadService.downloadExcel();
		response.setContentType("application/vnd.ms-excel");  
		response.setHeader("Content-disposition", "attachment;filename=downloadInsuranceTemplateExcel.xls");  
		try {
			OutputStream ouputStream = response.getOutputStream();  
			wb.write(ouputStream);  
			ouputStream.flush();  
			ouputStream.close(); 
		} catch(Exception e) {

		}
	}
}