package com.dc.flamingo.util.pdf;

import java.io.File;
import java.io.IOException;

import com.dc.flamingo.util.FileUtil;
import com.dc.flamingo.util.PathUtil;

import freemarker.template.Configuration;

public class FreemarkerConfiguration {

	private static Configuration config = null;

	/**
	 * Static initialization.
	 * 
	 * Initialize the configuration of Freemarker.
	 */
	static {
		config = new Configuration();
		String directoryPath = PathUtil.getServerRootPath() + "app"
				+ FileUtil.FSP + "clientAuthenticate" + FileUtil.FSP
				+ "html_template";
		try {
			config.setDirectoryForTemplateLoading(new File(directoryPath));
			config.setDefaultEncoding("UTF-8");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static Configuration getConfiguation() {
		return config;
	}

}
