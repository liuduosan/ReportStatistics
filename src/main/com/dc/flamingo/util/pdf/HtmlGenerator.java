package com.dc.flamingo.util.pdf;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.StringWriter;
import java.io.Writer;
import java.util.Map;

import com.dc.flamingo.util.FileUtil;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;

public class HtmlGenerator {
	/**
	 * Generate html string.
	 * 
	 * @param template
	 *            the name of freemarker teamlate.
	 * @param variables
	 *            the data of teamlate.
	 * @return htmlStr
	 * @throws Exception
	 */
	public static String generateHtmlString(String template,
			Map<String, Object> variables) throws Exception {
		Configuration config = FreemarkerConfiguration.getConfiguation();
		Template tp = config.getTemplate(template);
		StringWriter stringWriter = new StringWriter();
		BufferedWriter writer = new BufferedWriter(stringWriter);
		tp.setEncoding("UTF-8");
		tp.process(variables, writer);
		String htmlStr = stringWriter.toString();
		writer.flush();
		writer.close();
		return htmlStr;
	}

	/**
	 * 根据模板、数据生成静态html文件到指定路径
	 * 
	 * @param template
	 *            html模板
	 * @param variables
	 *            模板要插入的动态数据
	 * @param htmlFilePath
	 *            指定路径
	 * @param htmlFileName
	 *            html文件名
	 * @return
	 */
	public static boolean generateHtmlFile(String template,
			Map<String, Object> variables, String htmlFilePath,
			String htmlFileName) {
		Configuration config = FreemarkerConfiguration.getConfiguation();
		try {
			Template tp = config.getTemplate(template);
			tp.setEncoding("UTF-8");

			FileUtil.createDirectory(htmlFilePath);

			File htmlFile = new File(htmlFilePath + FileUtil.FSP + htmlFileName);
			Writer out = new BufferedWriter(new OutputStreamWriter(
					new FileOutputStream(htmlFile), "UTF-8"));
			tp.process(variables, out);
			out.flush();
			out.close();
		} catch (TemplateException ex) {
			ex.printStackTrace();
			return false;
		} catch (IOException e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}
}
