package com.dc.flamingo.util;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

/**
 * 
 * @author yaokuo
 *
 */
public class ExcelUtils {
	/**
	 * 生成Excel样式
	 * 
	 * @param list List<Map<String, Object>>
	 * @param templateName String 模板名称
	 * @return returnPath String 返回生成的excel路径
	 * @throws Exception
	 */
	public static String exportToExcel(List<Map<String, Object>> mapList,String templateName,String downloadPath)
			throws Exception {
		Workbook workbook = null;
		if (StringUtils.isBlank(templateName)) {
			return null;
		}
//		获取生成好的excel样式
		workbook = getWorkBook(mapList, templateName);
//		生成下载的文件名
		downloadPath = downloadPath + getFileName(templateName);
//		服务器上生成下载文件
		FileOutputStream out = new FileOutputStream(downloadPath);
		out.flush();
		workbook.write(out);
		out.close();
		workbook = null;
		return downloadPath;
	}

	
	/**
	 * 生成Excel流文件下载
	 * 
	 * @param response HttpServletResponse
	 * @param list List<Map<String, Object>>
	 * @param templateName String 模板路径+名称
	 * @throws Exception
	 */
	public static void exportToStream(HttpServletResponse response, List<Map<String, Object>> mapList, 
			String templateName)
			throws Exception {
//		设置文本类型
		response.setContentType("application/x-msdownload");	
//		设置生成的Excel文件名称
		String fileName = getFileName(templateName);
		response.setHeader("Content-Disposition", "attachment;filename="+ fileName +"");	
		OutputStream baos = response.getOutputStream();
		Workbook workbook = getWorkBook(mapList, templateName);;
    	baos.flush();
    	workbook.write(baos); 
    	baos.close(); 
	}

	
	/**
	 * 根据excel中的匹配关系与无序集合生成excel
	 * @param list List<Map<String, Object>> 数据集合
	 * @param wb Workbook 
	 * @return Workbook
	 * @throws IOException 
	 * @throws FileNotFoundException 
	 */
	public static Workbook getWorkBook(List<Map<String, Object>> list, String templateName)  {
		Workbook workbook = null;
		String suffix = templateName.substring(templateName.lastIndexOf(".") + 1, templateName
				.length());
//		获取模板存放路径
		String filePath = PropertiesUtils.getProperty("flex.flexfile.excel.template", "d:\\");
		if (StringUtils.isBlank(filePath)) {
			return null;
		} else {
			filePath = filePath + templateName;
		}
		try{
			// 根据需要生成的Excel版本来创建Excel工作空间
			 workbook = "xls".equals(suffix) ? new HSSFWorkbook(new FileInputStream(filePath)) : new XSSFWorkbook(new FileInputStream(filePath));
			// 分页处理
			 int totalCount = list.size();
			int pageSize = 65500;
			int sheetSum = 0;
			if (totalCount % pageSize == 0) {
				sheetSum = totalCount / pageSize;
			} else {
				sheetSum = totalCount / pageSize + 1;
			}
			// 根据分页情况导出excel
			Sheet sheet = null;
//			分页后获取list中的数据
			int jj = 0;
			for (int ii = 0; ii < sheetSum; ii++) {
				// 创建一个和模板相同sheet表
				sheet = workbook.cloneSheet(0);
				Row formatRow = sheet.getRow(sheet.getLastRowNum());
				int cellNum = formatRow.getLastCellNum();
				String[] cellkey = new String[cellNum];
				CellStyle[] cellStyle = new CellStyle[cellNum];
				for (int i = 0; i < cellNum; i++) {
					cellkey[i] = formatRow.getCell(i).getStringCellValue();
					cellStyle[i] = formatRow.getCell(i).getCellStyle();
				}
				sheet.removeRow(formatRow);
//				设定每sheet页显示的条数
				if (ii == (sheetSum - 1)) {
					if ((totalCount % pageSize) < pageSize) {
						pageSize = totalCount % pageSize;
					}
				}
//				像sheet中写入行数据
				for (int k = 0; k < pageSize; k++) {
					Map<String, Object> dataMap = (Map<String, Object>) list.get(jj++);
					Row row = sheet.createRow((short) (sheet.getLastRowNum() + 1));
					for (int j = 0; j < cellNum; j++) {
						Cell cell = row.createCell(j);
						cell.setCellStyle(cellStyle[j]);
						if (dataMap.get(cellkey[j]) != null) {
//							判断数据格式
							if (dataMap.get(cellkey[j]) instanceof String) {
								cell.setCellValue(dataMap.get(cellkey[j])
										.toString());
							} else {
//								设置数值格式为double
								cell.setCellType(4);
								cell.setCellValue(Double.parseDouble(dataMap.get(
										cellkey[j]).toString()));
							}
						}
					}
				}
			}
//			删除生成后的excel中的第一个样式模板
			workbook.removeSheetAt(0);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			return null;
		}catch (IOException e){
			e.printStackTrace();
			return null;
		}
		return workbook;
	}
	
	/**
	 * 生成返回下载的excel名称
	 * @param templateName String 
	 * @return
	 */
	private static String getFileName(String templateName) {
		String prefix = "";
		String suffix = "";
		// 根据文件路径判断后缀名
		if (StringUtils.isBlank(templateName)) {
			return null;
		} else {
//			这里不能用split，防止多个.出现
			suffix = templateName.substring(templateName.lastIndexOf(".") + 1, templateName
					.length());
			prefix = templateName.substring(0, templateName.lastIndexOf("."));
		}
		long time = new Date().getTime();
		prefix = prefix + "_" + time + "." + suffix;
		return prefix;
	}
	
	
	/**
	 * 导入Excel
	 * @param templateName String 模板名称
	 * @param impFile String 上传文件名称，默认上传存储的位置是在属性中配置的 flex.flexfile.savepath
	 * @return
	 * @throws IOException 
	 */
	public static List<Map<String,Object>> importExcel(String templateName, String impFile) {   
		Workbook wb = null;
		List<Map<String,Object>> returnList = null;
		String suffix = templateName.substring(templateName.lastIndexOf(".") + 1, templateName
				.length());
//		获取模板存放路径
		String filePath = PropertiesUtils.getProperty("flex.flexfile.excel.template", "D:\\");
		if (StringUtils.isBlank(filePath)) {
			return null;
		} else {
			filePath = filePath + templateName;
		}
		try {

//			获取模板的excel格式
			wb = "xls".equals(suffix) ? new HSSFWorkbook(new FileInputStream(filePath)) : new XSSFWorkbook(new FileInputStream(filePath)); 
//			获取模板第一个sheet页
	        Sheet sheetTemplateRow = wb.getSheetAt(0);
//	        获取模板第一个sheet页的样式行
	        int rowStart = sheetTemplateRow.getLastRowNum();
			Row formatRow = sheetTemplateRow.getRow(rowStart);
			int cellNum = formatRow.getLastCellNum();
			String[] cellkey = new String[cellNum];
//			获取到模板里面的列名
			for (int i = 0; i < cellNum; i++) {
				cellkey[i] = formatRow.getCell(i).getStringCellValue();
			}
	        int sheetNum = wb.getNumberOfSheets();   
	        returnList = new ArrayList();
//			获取上传文件信息
	        String impFileSuffix = impFile.substring(impFile.lastIndexOf(".") + 1, impFile.length());
	        
	        wb = "xls".equals(impFileSuffix) ? new HSSFWorkbook(new FileInputStream(impFile)) : new XSSFWorkbook(new FileInputStream(impFile)); 
	        
	        
	        List list = null;
	        Map ebMap = null;
	        for (int i = 0; i <sheetNum; i++) {   
	        	Sheet sheetRow = wb.getSheetAt(i);
	        	list = new ArrayList();
	        	ebMap = new HashMap();
	            Sheet childSheet = wb.getSheetAt(i);  
//	             取第一行的数据，按照模板样式来
	            int firstRowNum = rowStart;
	            int rowNum = childSheet.getLastRowNum(); 
	            int cellNums = childSheet.getRow(firstRowNum).getPhysicalNumberOfCells();
	            Map<String, Object> hashMap = null; 
	            for (int j = firstRowNum; j <= rowNum; j++) {       
	            	Row row = childSheet.getRow(j + 1); 
	            	hashMap = new HashMap();
	            	if (row != null) {
	            		for (int k = 0; k < cellkey.length; k++) {   
	            			if (row.getCell(k).getCellType() == 1) {
	            				hashMap.put(cellkey[k], row.getCell(k).getRichStringCellValue().toString());
	            			} else {
	            				DecimalFormat df = new DecimalFormat("0");  
	            				hashMap.put(cellkey[k], df.format(row.getCell(k).getNumericCellValue()));
	            			}
	            			
	            	}
	            		returnList.add(hashMap);
	            	} 
	            }  
	        } 
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			//删除掉上传的文件
		}
		return returnList;
    }  	
	
}
