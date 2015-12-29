package com.dc.assess.upload.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.dc.assess.upload.dao.UploadDao;
import com.dc.assess.user.dao.UserDao;
import com.dc.assess.user.dto.UserManage;
import com.dc.flamingo.core.service.DataService;
import com.dc.flamingo.core.support.AppException;
import com.dc.flamingo.util.ExcelUtils;
import com.dc.flamingo.util.PropertiesUtils;

/**
 * <p>
 * 
 * <pre>
 * <b>类描述：</b>
 * 	批量上传excel的service，包括下载模板
 * <b>作者：</b>
 * 	jiaocy1(焦春宇)
 * 	邮箱：<a href="mailto:jiaocy1@digitalchina.com" >jiaocy1@digitalchina.com</a>	
 * <b>创建时间：</b> 
 * 	2015年12月29日 下午2:38:42
 * </pre>
 * 
 * </p>
 */
@Service
@Transactional(readOnly = true)
public class UploadService extends DataService<Object> {
	@Autowired
	private UploadDao uploadDao;
	@Autowired
	private UserDao userDao;

	/**
	 * <p>
	 * 
	 * <pre>
	 * <b>方法描述：</b>
	 * 	批量上传excel的方法
	 * <b>作者：</b>
	 * 	jiaocy1(焦春宇)
	 * 	邮箱：<a href="mailto:jiaocy1@digitalchina.com" >jiaocy1@digitalchina.com</a>	
	 * <b>创建时间：</b> 
	 * 	2015年12月29日 下午2:39:58
	 * </pre>
	 * 
	 * </p>
	 */
	public List<UserManage> uploadExcel(String fileName) {
		String filePath = PropertiesUtils.getProperty("flex.flexfile.savepath",
				"/usr/local/nginx/html/dcgou");
		List<Map<String, Object>> contentList = ExcelUtils.importExcel(
				"123456.xls", "D://" + filePath + "/" + fileName);
		List<UserManage> usermanageerror = new ArrayList<UserManage>();
		if (contentList == null || contentList.size() < 1) {
			throw new AppException("上传内容为空");
		} else {
			for (int i = 0; i < contentList.size(); i++) {
				UserManage usermanage = new UserManage();
				Map<String, Object> map = contentList.get(i);
				String itcode = (String) map.get("itcode");
				String deptno = (String) map.get("部门编号");
				String author = (String) map.get("作者itcode");
				String description = (String) map.get("描述");
				boolean flag1 = uploadDao.validateItcode(itcode);
				boolean flag2 = uploadDao.validateDeptNo(deptno);
				if (!flag1) {
					usermanage.setItCode(itcode);
					usermanage.setDeptNO(deptno);
					usermanage.setAuthor(author);
					usermanage.setDescription(description);
					usermanage.setPostCode("不存在的itcode");
					usermanageerror.add(usermanage);
				} else {
					if (!flag2) {
						usermanage.setItCode(itcode);
						usermanage.setDeptNO(deptno);
						usermanage.setAuthor(author);
						usermanage.setDescription(description);
						usermanage.setPostCode("不存在的部门编号");
						usermanageerror.add(usermanage);
					} else {
						userDao.add(itcode, deptno, author, description);
					}
				}
			}
		}
		return usermanageerror;
	}

	/**
	 * <p>
	 * 
	 * <pre>
	 * <b>方法描述：</b>
	 * 	下载模板
	 * <b>作者：</b>
	 * 	jiaocy1(焦春宇)
	 * 	邮箱：<a href="mailto:jiaocy1@digitalchina.com" >jiaocy1@digitalchina.com</a>	
	 * <b>创建时间：</b> 
	 * 	2015年12月29日 下午2:40:20
	 * </pre>
	 * 
	 * </p>
	 */
	public HSSFWorkbook downloadExcel() {
		String[] excelHeader = { "itcode", "部门编号", "作者itcode", "描述" };
		HSSFWorkbook wb = new HSSFWorkbook();
		HSSFSheet sheet = wb.createSheet("权限控制");
		sheet.setDefaultRowHeight((short) 20);
		sheet.setDefaultColumnWidth(100);
		for (int i = 0; i < excelHeader.length; i++) {
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
		headStyle.setBorderTop((short) 1);
		headStyle.setBorderRight((short) 1);
		headStyle.setBorderBottom((short) 1);
		headStyle.setBorderLeft((short) 1);
		headStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);
		// 输出excel头信息
		for (int i = 0; i < excelHeader.length; i++) {
			HSSFCell cell = row.createCell(i);
			cell.setCellValue(excelHeader[i]);
			cell.setCellStyle(headStyle);
			// sheet.autoSizeColumn(i);
		}

		// 设置表主体单元格样式
		HSSFCellStyle bodyStyle = wb.createCellStyle();
		HSSFFont bodyFont = wb.createFont();
		bodyFont.setBoldweight(HSSFFont.BOLDWEIGHT_NORMAL);
		bodyFont.setFontName("宋体");
		bodyFont.setFontHeightInPoints((short) 9);
		bodyStyle = wb.createCellStyle();
		bodyStyle.setFont(bodyFont);
		bodyStyle.setBorderTop((short) 1);
		bodyStyle.setBorderRight((short) 1);
		bodyStyle.setBorderBottom((short) 1);
		bodyStyle.setBorderLeft((short) 1);
		bodyStyle.setAlignment(HSSFCellStyle.ALIGN_LEFT);
		bodyStyle.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
		for (int i = 0; i < 1; i++) {
			row = sheet.createRow(i + 1);
			for (int m = 0; m < 4; m++) {
				HSSFCell cell = row.createCell(m);
				cell.setCellStyle(bodyStyle);
			}
		}
		return wb;
	}

}
