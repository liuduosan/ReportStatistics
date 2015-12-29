package com.dc.assess.exportreport.web;

import java.io.OutputStream;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.dc.assess.exportreport.service.DownLoadService;

@Controller
@RequestMapping(value="/download")
public class DownLoadController {

	@Autowired
	private DownLoadService downLoadService;
	@RequestMapping(value="/excel")
	public void downLoadExcel(HttpServletRequest request,HttpServletResponse response){
		HSSFWorkbook wb =  downLoadService.getexcel();
		response.setContentType("application/vnd.ms-excel");  
		response.setHeader("Content-disposition", "attachment;filename=searchAssessmentExportExcel.xls");  
		try {
			OutputStream ouputStream = response.getOutputStream();  
			wb.write(ouputStream);  
			ouputStream.flush();  
			ouputStream.close(); 
		} catch(Exception e) {

		}
	}
	
}
