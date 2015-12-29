package com.dc.flamingo.util;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class HttpUtils {
	/**
	 * 将请求中的搜索参数置入Map中
	 * @Methods Name requestParam2Map
	 * @Create In May 30, 2011 By lee
	 * @param request
	 * @return Map<String,String>
	 */
	public static Map<String, String> requestParam2Map(HttpServletRequest request){
		Map<String, String> map = new HashMap<String, String>();
		Enumeration<?> enume = request.getParameterNames();
		while(enume.hasMoreElements()){
			Object item = enume.nextElement();
			String paramName = item.toString();
			String value = request.getParameter(paramName);
			String[] values = request.getParameterValues(paramName);
			if(StringUtils.isBlank(value)&&values.length<=1){
				continue;
			}
			if(values.length>1){
				value=StringUtils.join(values,",");
			}
			map.put(paramName, value);
		}
		return map;
	}
	
	/**
	 * 下载指定目录的文件
	 * 
	 * @param fileDirPath
	 *            文件路径 （不包括文件名）
	 * @param fileName
	 *            文件名
	 * @param fileType
	 *            文件類型
	 * @param response
	 * @throws Exception
	 */
	public static void downloadFile(String fileDirPath, String fileName,
			String fileType,
			HttpServletResponse response) throws Exception {
		String filePath = fileDirPath + FileUtil.FSP + fileName + "." + fileType;
		File f = new File(filePath);

		if (!f.exists()) {
			response.setContentType("text/html;charset=utf-8");
			response.getWriter().print("没有找到："+filePath+" 对应的文件！");
			return;
		}
		ServletOutputStream out = response.getOutputStream();
		response.setHeader("Content-disposition", "attachment;filename="
				+ URLEncoder.encode(fileName + "." + fileType, "utf-8"));
		BufferedInputStream bis = null;
		BufferedOutputStream bos = null;
		try {
			bis = new BufferedInputStream(new FileInputStream(filePath));
			bos = new BufferedOutputStream(out);
			byte[] buff = new byte[2048];
			int bytesRead;
			while (-1 != (bytesRead = bis.read(buff, 0, buff.length))) {
				bos.write(buff, 0, bytesRead);
			}
		} catch (IOException e) {
			e.printStackTrace();
			throw e;
		} finally {
			if (bis != null)
				bis.close();
			if (bos != null)
				bos.close();
		}
	}
	
	public static void setCharacterEncoding(HttpServletRequest request,
			HttpServletResponse response) {
		try {
			request.setCharacterEncoding("utf-8");
			response.setCharacterEncoding("utf-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
	}

}
