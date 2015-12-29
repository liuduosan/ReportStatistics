package com.dc.flamingo.util;


public class PathUtil {

	/**
	 * 获取jboss服务器根目录
	 * 
	 * @return
	 */
	public static String getServerRootPath() {
		String system = System.getProperty("os.name");
		String root = PathUtil.class.getResource("").toString();
		
		if ("Linux".equalsIgnoreCase(system)) {// linux 系统
			//weblogic
			if (root.startsWith("zip:")) {
				root = root.substring("zip:".length());
			}
		} else {
			// jboss 7
			if (root.startsWith("vfs:/")) {
				root = root.substring("vfs:/".length());
			}
			
			// jboss4
			if (root.startsWith("file:/")) {
				root = root.substring("file:/".length());
			}
		}
		if(root.indexOf("WEB-INF")!=-1) {
			root = root.substring(0, root.indexOf("WEB-INF"));
		}
		root = root.replaceAll("%20", " ");
		return root;
	}
	
	public static String getFileSaveWebrootPath() {
		String system = System.getProperty("os.name");
		if ("Linux".equalsIgnoreCase(system)) {// linux 系统
			return FileUtil.FSP+"tmp"+FileUtil.FSP;
		} else {
			return getServerRootPath();
		}
	}

}
