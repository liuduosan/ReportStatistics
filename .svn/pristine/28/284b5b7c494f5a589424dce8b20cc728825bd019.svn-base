package com.dc.assess.exportreport.service;

import java.util.Iterator;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.dc.assess.exportreport.dao.DownLoadDao;
import com.dc.assess.exportreport.dto.DownLoadDto;
import com.dc.flamingo.core.service.DataService;

@Service
@Transactional(readOnly=true)
public class DownLoadService extends DataService<Object> {

	@Autowired
	private DownLoadDao downLoadDao;
	public List<DownLoadDto> noHeadName() {
		List<DownLoadDto> UserInfoList = downLoadDao.getUserInfo();
		List<DownLoadDto> HeadNameList = downLoadDao.getHeadName();
		for (DownLoadDto downLoadDto : HeadNameList) {
			for (Iterator<DownLoadDto> iterator = UserInfoList.iterator(); iterator
					.hasNext();) {
				DownLoadDto downLoadDto1 = (DownLoadDto) iterator.next();
				if (downLoadDto1.getItCode().equals(downLoadDto.getItCode())) {
					iterator.remove();
				}
			}
		}
		return UserInfoList;
	}
	public List<DownLoadDto> noTableName() {
		List<DownLoadDto> UserInfoList = downLoadDao.getUserInfo();
		List<DownLoadDto> TableNameList = downLoadDao.getTableName();
		for (DownLoadDto downLoadDto : TableNameList) {
			for (Iterator<DownLoadDto> iterator = UserInfoList.iterator(); iterator
					.hasNext();) {
				DownLoadDto downLoadDto1 = (DownLoadDto) iterator.next();
				if (downLoadDto1.getItCode().equals(downLoadDto.getItCode())) {
					iterator.remove();
				}
			}
		}
		return UserInfoList;
	}
	
	public HSSFWorkbook getexcel() {
		List<DownLoadDto> noHeadName = this.noHeadName();
		List<DownLoadDto> noTableName = this.noTableName();

    	String[] excelHeader = { "未建表头itcode", "未建表头姓名","所在部门", "未建表itcode", "未建表姓名","所在部门"};
        HSSFWorkbook wb = new HSSFWorkbook();  
        HSSFSheet sheet = wb.createSheet("未建考核名单"); 
        sheet.setDefaultRowHeight((short)20);
        sheet.setDefaultColumnWidth(100);
        for(int i = 0; i < excelHeader.length;i++) {
        	sheet.setColumnWidth(i, 5000);
        }

        HSSFRow row = sheet.createRow((int) 0); 
        // 设置表头单元格样式
        HSSFCellStyle headStyle = wb.createCellStyle();  
        HSSFFont headFont = wb.createFont();
        headFont.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
        headFont.setFontName("宋体");
        headFont.setFontHeightInPoints((short) 11);
        headStyle.setFont(headFont);
        headStyle.setBorderTop((short)1);
        headStyle.setBorderRight((short)1);
        headStyle.setBorderBottom((short)1);
        headStyle.setBorderLeft((short)1);
        headStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);
        // 输出excel头信息
        for (int i = 0; i < excelHeader.length; i++) {  
            HSSFCell cell = row.createCell(i);  
            cell.setCellValue(excelHeader[i]);  
            cell.setCellStyle(headStyle);  
        } 
        // 设置表主体单元格样式
        HSSFCellStyle bodyStyle = wb.createCellStyle();  
        HSSFFont bodyFont = wb.createFont();
        bodyFont.setBoldweight(HSSFFont.BOLDWEIGHT_NORMAL);
        bodyFont.setFontName("宋体");
        bodyFont.setFontHeightInPoints((short) 9);
        bodyStyle = wb.createCellStyle();
        bodyStyle.setFont(bodyFont);
        bodyStyle.setBorderTop((short)1);
        bodyStyle.setBorderRight((short)1);
        bodyStyle.setBorderBottom((short)1);
        bodyStyle.setBorderLeft((short)1);
        bodyStyle.setAlignment(HSSFCellStyle.ALIGN_LEFT);
        bodyStyle.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
        int size;
        if (noHeadName.size()>noTableName.size()) {
        	 size = noHeadName.size();
		}else{
			 size = noTableName.size();
		}
    	
		for(int i = 0; i <size; i++) {
			row = sheet.createRow(i+1);  
    		for(int m = 0; m < 6; m++) {
    			HSSFCell cell = row.createCell(m);
    			cell.setCellStyle(bodyStyle);
    		}
    		try {
    			DownLoadDto entityTmp =noHeadName.get(i);
    			row.getCell(0).setCellValue(entityTmp.getItCode());  
    	        row.getCell(1).setCellValue(entityTmp.getUserName()); 
    	        row.getCell(2).setCellValue(entityTmp.getDeptname()); 
			} catch (Exception e) {
				// TODO: handle exception
			}
    		try {
    			DownLoadDto entityTmp1 =noTableName.get(i);
                row.getCell(3).setCellValue(entityTmp1.getItCode());  
        		row.getCell(4).setCellValue(entityTmp1.getUserName()); 
        		row.getCell(5).setCellValue(entityTmp1.getDeptname()); 
			} catch (Exception e) {
				// TODO: handle exception
			}
    	}
    	return wb;
	}
}
