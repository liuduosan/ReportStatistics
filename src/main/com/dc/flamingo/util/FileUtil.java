package com.dc.flamingo.util;

import java.io.File;
import java.util.List;

import org.apache.commons.io.FileUtils;

public class FileUtil {

	public static final String FSP = System.getProperty("file.separator");
	public static final String CREATE_PDF_FILE = "pdf";
	public static final String CREATE_IMAGE_FILE = "image";
	
	public static void createDirectory(String dirPath) {
		File dir = new File(dirPath);
		if (!dir.exists()) {
			dir.mkdirs();
		}
	}
	
	public static void deleteFile(String path) {
		File fileDir = new File(path);
		if(fileDir.exists()) {
			if(fileDir.isDirectory()) {
				for(File file : fileDir.listFiles()) {
					deleteFile(file.getAbsolutePath());
				}
				fileDir.delete();
			}
			fileDir.delete();
		} 
	}
	
	public static List<String> readFileName(String path,List<String> nameList) {
		File fileDir = new File(path);
		if(fileDir.exists()) {
			if(fileDir.isDirectory()) {
				for(File file : fileDir.listFiles()) {
					readFileName(file.getAbsolutePath(),nameList);
				}
				nameList.add(fileDir.getName());
			}
			nameList.add(fileDir.getName());
		} 
		return nameList;
	}
	
	public static void renameFile(String pdfFullFileName,
			String pdfFileRealName) throws Exception {
		try {
			File file = new File(pdfFullFileName);
			if(file.exists()) {
//				file.renameTo(new File(pdfFileRealName));
				FileUtils.copyFile(file, new File(pdfFileRealName));
				deleteFile(pdfFullFileName);
			}
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static String getFileType(File file) {
		String path = file.getAbsolutePath();
		return path.substring(path.lastIndexOf(".")+1);
	}
}
