package com.dc.flamingo.util;

import java.io.File;
import java.util.Properties;
/**
 * @Title: PropertiesUtil.java
 * @Description: <br>
 * <br>
 * @Company: DigitalChina
 * @Created on Nov 19, 2011 8:08:42 PM
 * @author lizz
 * @version $Revision: 1.1 $
 * @since 1.0
 */
public class SystemInfoUtils {
	private static final LogUtils logger = LogUtils
			.getLogUtil(SystemInfoUtils.class);
	
		
	/**
	 * @Title: getSystemInfo
	 * @Description: TODO(获取系统属性，主要用于方便开发使用)
	 * @param @return    设定文件
	 * @return String    返回类型
	 * @throws
	 */
	public static String getSystemInfoHTML(){
		StringBuffer sb=new StringBuffer();
		Properties props=System.getProperties(); //系统属性   
		sb.append("Java的运行环境版本："+props.getProperty("java.version")); 
		sb.append("<br>");
		sb.append("Java的运行环境供应商："+props.getProperty("java.vendor"));   
		sb.append("<br>");
		sb.append("Java供应商的URL："+props.getProperty("java.vendor.url"));   
		sb.append("<br>");
		sb.append("Java的安装路径："+props.getProperty("java.home"));   
		sb.append("<br>");
		sb.append("Java的虚拟机规范版本："+props.getProperty("java.vm.specification.version"));   
		sb.append("<br>");
		sb.append("Java的虚拟机规范供应商："+props.getProperty("java.vm.specification.vendor"));   
		sb.append("<br>");
		sb.append("Java的虚拟机规范名称："+props.getProperty("java.vm.specification.name"));   
		sb.append("<br>");
		sb.append("Java的虚拟机实现版本："+props.getProperty("java.vm.version"));   
		sb.append("<br>");
		sb.append("Java的虚拟机实现供应商："+props.getProperty("java.vm.vendor"));   
		sb.append("<br>");
		sb.append("Java的虚拟机实现名称："+props.getProperty("java.vm.name"));   
		sb.append("<br>");
		sb.append("Java运行时环境规范版本："+props.getProperty("java.specification.version"));   
		sb.append("<br>");
		sb.append("Java运行时环境规范供应商："+props.getProperty("java.specification.vender"));  
		sb.append("<br>"); 
		sb.append("Java运行时环境规范名称："+props.getProperty("java.specification.name"));   
		sb.append("<br>");
		sb.append("Java的类格式版本号："+props.getProperty("java.class.version"));   
		sb.append("<br>");
		sb.append("Java的类路径："+props.getProperty("java.class.path"));   
		sb.append("<br>");
		sb.append("加载库时搜索的路径列表："+props.getProperty("java.library.path"));   
		sb.append("<br>");
		sb.append("默认的临时文件路径："+props.getProperty("java.io.tmpdir"));   
		sb.append("<br>");
		sb.append("一个或多个扩展目录的路径："+props.getProperty("java.ext.dirs"));  
		sb.append("<br>"); 
		sb.append("操作系统的名称："+props.getProperty("os.name"));   
		sb.append("<br>");
		sb.append("操作系统的构架："+props.getProperty("os.arch"));   
		sb.append("<br>");
		sb.append("操作系统的版本："+props.getProperty("os.version"));   
		sb.append("<br>");
		sb.append("文件分隔符："+props.getProperty("file.separator")); 
		sb.append("<br>");  //在 unix 系统中是＂／＂   
		sb.append("路径分隔符："+props.getProperty("path.separator")); 
		sb.append("<br>");  //在 unix 系统中是＂:＂   
		sb.append("行分隔符："+props.getProperty("line.separator")); 
		sb.append("<br>");  //在 unix 系统中是＂/n＂   
		sb.append("用户的账户名称："+props.getProperty("user.name")); 
		sb.append("<br>");  
		sb.append("用户的主目录："+props.getProperty("user.home"));   
		sb.append("<br>");
		sb.append("用户的当前工作目录："+props.getProperty("user.dir"));   //bin目录
		sb.append("<br>");
		sb.append("应用程序类路径："+SystemInfoUtils.class.getResource( "/")); //类的classes路径，以file:/开头
		sb.append("<br>");
		sb.append("应用程序类路径："+SystemInfoUtils.class.getResource("/").getPath());//类的classes路径，以/开头
		sb.append("<br>");
		File   file=new File("."); 
		sb.append("应用程序文件路径："+file.getAbsolutePath()); //bin目录
		sb.append("<br>");
		sb.append("当前类完整路径："+SystemInfoUtils.class.getResource("").getPath()); //类的完整路径 
		sb.append("<br>");
		sb.append("当前应用根路径："+SystemInfoUtils.getAppPath()); //类的完整路径 
		sb.append("<br>");
		
		return sb.toString();
	}
	
	public static String getAppPath(){
		String s=SystemInfoUtils.class.getResource("").getPath();
		return s.substring(0, s.indexOf("WEB-INF"));
	}
	
}
