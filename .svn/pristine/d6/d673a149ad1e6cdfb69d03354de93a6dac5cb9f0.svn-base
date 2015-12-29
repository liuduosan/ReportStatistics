package com.dc.flamingo.util;

import java.io.File;
import java.text.MessageFormat;
import java.util.Locale;
import java.util.Properties;

import com.dc.flamingo.context.ContextHolder;
import com.dc.flamingo.core.support.Constants;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.context.NoSuchMessageException;

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
public class PropertiesUtils {
	private static final LogUtils logger = LogUtils.getLogUtil(PropertiesUtils.class);
	private static Locale locale = Locale.CHINA;

	public static void setLocale(Locale locale) {
		PropertiesUtils.locale = locale;
	}

	/**
	 * 获取资源文件信息
	 * 
	 * @param Key
	 * @param defaultValue
	 * @return
	 */
	public static String getProperty(String key, String defaultValue) {
		return ContextHolder.getApplicationContext().getMessage(key, new Object[0], defaultValue, locale);
	}

	/**
	 * 获取资源文件信息
	 * @param key
	 * @return 如果key不存在则返回空字符串
	 */
	public static String getProperty(String key) {
		try {
			return ContextHolder.getApplicationContext().getMessage(key, new Object[0], locale);
		} catch (NoSuchMessageException nme) {
			logger.warn(nme.getMessage());
			return "";
		}
	}
	
	/**
	 * 清空属性，下次则可以更新
	* @Title: refreshProperties
	* @Description: TODO(这里用一句话描述这个方法的作用)
	* @param     设定文件
	* @return void    返回类型
	* @throws
	 */
	public  static void refreshProperties(){
		ReloadableResourceBundleMessageSource s=(ReloadableResourceBundleMessageSource)ContextHolder.getApplicationContext().getBean(Constants.PROPERTIES_SPRING_BEAN_ID);
		s.clearCache();
	}
	
	
	/**
	 * 格式化资源字符串,将其中的类似{0}的变量替换为对应数据
	 * 
	 * @param source
	 * @param arg
	 * @return
	 */
	public static String format(String source, Object[] args) {
		MessageFormat formatter = new MessageFormat(source, locale);
		return formatter.format(args);
	}
	
	/**
	 * 
	* @Title: format
	* @Description: TODO(这里用一句话描述这个方法的作用)
	* @param @param message
	* @param @param params
	* @param @return    设定文件
	* @return String    返回类型
	* @throws
	 */
	public static String format(String message, String... params) {
        // 判断参数有效性
        if (params != null && message != null && params.length > 0) {
            StringBuffer retMsg = new StringBuffer();
            MessageFormat mf = new MessageFormat(message);
            retMsg.append(mf.format(params));
            return retMsg.toString();
        } else {
            // 原消息返回
            return message;
        }
    }
	
	
	/**
	 * 
	 * @Title: getSystemInfo
	 * @Description: TODO(获取系统属性)
	 * @param @return    设定文件
	 * @return String    返回类型
	 * @throws
	 */
	 public static String getSystemInfo(){
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
		sb.append("用户的当前工作目录："+props.getProperty("user.dir"));  
		sb.append("<br>");
		sb.append("应用程序路径："+PropertiesUtils.class.getResource( "/"));
		sb.append("<br>");
		File   file=new File("."); 
		sb.append("应用程序文件路径："+file.getAbsolutePath());
		sb.append("<br>");
		sb.append("应用程序文件路径："+PropertiesUtils.class.getClassLoader().getResource(".").getPath());
		sb.append("<br>");
		sb.append("应用程序文件路径："+PropertiesUtils.class.getResource("").getPath());
		sb.append("<br>");
		sb.append("应用程序文件路径："+PropertiesUtils.class.getResource("/").getPath());
		sb.append("<br>");
		
		return sb.toString();
	}

}
