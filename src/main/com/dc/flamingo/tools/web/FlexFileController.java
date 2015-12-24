package com.dc.flamingo.tools.web;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URLEncoder;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.util.Streams;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import com.dc.flamingo.core.support.AjaxResult;
import com.dc.flamingo.util.LogUtils;
import com.dc.flamingo.util.PropertiesUtils;
import com.dc.flamingo.util.StringUtils;
import com.dc.flamingo.util.UTF8Utils;

/**
 * 
 * @author lizz
 *
 */

@Controller
@RequestMapping("/flexfile")
public class FlexFileController {

	private transient LogUtils log=LogUtils.getLogUtil(FlexFileController.class);
	String savePath=PropertiesUtils.getProperty("flex.flexfile.savepath");   //文件保存路径，不带后面的斜杠
	
	/**
	 * 删除文件
	 */
	@RequestMapping(value="/delete")   
	@ResponseBody
	public AjaxResult deleteFile(@RequestParam("filename")String filename,HttpServletResponse response){
		File f=new File(savePath+"/"+filename);
		boolean b=true;
		if (f.exists()){
			b=f.delete();
		}
		if (b) return AjaxResult.successResult(PropertiesUtils.getProperty("flex.flexfile.delete.success"));
		else return AjaxResult.errorResult(PropertiesUtils.getProperty("flex.flexfile.delete.error"));
	}



	/**
	 * 下载文件
	 */
	@RequestMapping(value="/download")   
	@ResponseBody
	public void downloadFile(@RequestParam("filename")String filename,HttpServletResponse response) throws Exception{
		filename=UTF8Utils.unescape(filename);  //对于get过来的请求，有中文解码的问题
		String filepath=savePath+"/"+filename;
		File f=new File(filepath);
		if (!f.exists()){
			response.setContentType("text/html;charset=utf-8");
			response.getWriter().print(PropertiesUtils.getProperty("flex.flexfile.filenotexists"));
			return;
		}
		ServletOutputStream out = response.getOutputStream();
		response.setHeader("Content-disposition", "attachment;filename=" + URLEncoder.encode(filename,"utf-8"));
		BufferedInputStream bis = null;
		BufferedOutputStream bos = null;
		try {
			bis = new BufferedInputStream(new FileInputStream(filepath));
			bos = new BufferedOutputStream(out);
			byte[] buff = new byte[2048];
			int bytesRead;
			while (-1 != (bytesRead = bis.read(buff, 0, buff.length))) {
				bos.write(buff, 0, bytesRead);
			}
		} catch (IOException e) {
			throw e;
		} finally {
			if (bis != null)
				bis.close();
			if (bos != null)
				bos.close();
		}

	}
	
	/**
	 * 上传文件
	 */
	@RequestMapping(value="/upload")   
	@ResponseBody
	public void uploadFile(@RequestParam("file") CommonsMultipartFile file,@RequestParam("name") String fname,HttpServletRequest request,HttpServletResponse response) throws Exception{
		log.debug("start uploading....");
		fname=UTF8Utils.unescape(fname);
		String fileName=getUuidFileName(fname);
		String folder=request.getParameter("folder");
		if(StringUtils.isBlank(folder)) folder = "";
		BufferedInputStream in = new BufferedInputStream(file.getInputStream());//获得文件输入流
		String filePath=savePath+"/"+folder+fileName;
		File fileFolder = new File(savePath+"/"+folder);
		if(!fileFolder.exists()) {
			fileFolder.mkdirs();
		}
		BufferedOutputStream out = new BufferedOutputStream(new FileOutputStream(new File(filePath)));//获得文件输出流
		Streams.copy(in, out, true);//开始把文件写到你指定的上传文件夹	
		out.flush();
		out.close();
		out=null;	
		in.close();
		in=null;
		try{
			response.setContentType("text/xml; charset=utf-8");	
			PrintWriter pw = response.getWriter();
			pw.println("<?xml version='1.0' encoding='UTF-8'?>");
			pw.println("<xml>");
			pw.println("<filepath>"+folder+fileName+"</filepath>");
			pw.println("</xml>");//
			pw.flush();
			pw.close();		
		}catch(Exception e){

		}
		log.debug("end uploading....");
	}
	
	/**
	 * 上传文件
	 */
	@RequestMapping(value="/uploadTra")   
	@ResponseBody
	public void uploadTraFile(@RequestParam("file") CommonsMultipartFile file,HttpServletRequest request,HttpServletResponse response) throws Exception{
		log.debug("start uploading....");
		//String ftype=fileName.substring(fileName.lastIndexOf(".")+1).toLowerCase();
		//fname=UTF8Utils.unescape(fname);
		String fname = file.getFileItem().getName();
		if(fname.indexOf("\\") > -1) fname = fname.substring(fname.lastIndexOf("\\")+1);
		String ftype=fname.substring(fname.lastIndexOf(".")+1).toLowerCase();
		String fileName=getUuidFileName(fname);
		String folder=request.getParameter("folder");
		Long fileLimitSize=Long.parseLong(request.getParameter("fileLimitSize"));
		String fileLimitType=request.getParameter("fileLimitType");
		if(StringUtils.isBlank(folder)) folder = "";
		BufferedInputStream in = new BufferedInputStream(file.getInputStream());//获得文件输入流
		String filePath=savePath+"/"+folder+fileName;
		File fileFolder = new File(savePath+"/"+folder);
		if(!fileFolder.exists()) {
			fileFolder.mkdirs();
		}
		String error = "";
		if(file.getFileItem().getSize() > fileLimitSize) {
			error = "文件大小超过限制";
		}
		if(fileLimitType.indexOf("*." + ftype) < 0) {
			error = "文件格式有误";
		}
		if(error.equals("")) {
			BufferedOutputStream out = new BufferedOutputStream(new FileOutputStream(new File(filePath)));//获得文件输出流
			Streams.copy(in, out, true);//开始把文件写到你指定的上传文件夹	
			out.flush();
			out.close();
			out=null;	
			in.close();
			in=null;
		}
		
		try{
			response.setContentType("text/html; charset=utf-8");	
			PrintWriter pw = response.getWriter();
			String url = PropertiesUtils.getProperty("app.dcgouFront") + "/app/register/uploadCallback.jsp?fileName=" + fname+ "&filePath=" + folder+fileName + "&error=" + error;
			pw.println("<html>");
			pw.println("<script>");
			pw.println("function createIfameAndCall() {");
			pw.println("	var frame = document.createElement('iframe');");
			pw.println("	frame.setAttribute('style','display:none;');");
			pw.println("	frame.setAttribute('src','" + url + "');");
			pw.println("	document.body.appendChild(frame);");
			pw.println("}");
			pw.println("</script>");
			pw.println("<body onload='createIfameAndCall()'>");
			pw.println("</body>");
			pw.println("</html>");
			pw.flush();
			pw.close();		
		}catch(Exception e){

		}
		log.debug("end uploading....");
	}
	
	private String getUuidFileName(String fileName){
		String ftype=fileName.substring(fileName.lastIndexOf(".")+1).toLowerCase(); //文件后缀
		fileName=StringUtils.getUUID() + "." + ftype;
		return fileName;
	}

	//判断文件是否存在，获取可以保存的文件名称，不包含路径
	@SuppressWarnings("unused")
	private String getFileName(String fileName){
		//先将特殊字符去掉
		fileName=fileName.replace("&", "");
		fileName=fileName.replace("+", "");
		fileName=fileName.replace("<", "");
		fileName=fileName.replace(">", "");
		fileName=fileName.replace("%", "");
		fileName=fileName.replace(" ", "");
		String ffileName=fileName.substring(0,fileName.lastIndexOf("."));            //文件前缀
		String ftype=fileName.substring(fileName.lastIndexOf(".")+1).toLowerCase(); //文件后缀
		File f = new File(savePath+"/"+fileName);
		int i=0; 
		while(f.exists()){
			i++;
			f=null;
			fileName=ffileName+"-"+String.valueOf(i)+"."+ftype;
			System.out.println("fileName:"+fileName); 
			f=new File(savePath+"/"+fileName);
		}
		return fileName;
	}


}
