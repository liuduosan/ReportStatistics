package com.dc.flamingo.util.pdf;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

import com.dc.flamingo.util.FileUtil;


public class PDFUtil {

	/**
	 * 通过html内容创建pdf
	 * 
	 * @param htmlContent
	 * @param htmlFullFileName
	 * @param pdfFullFileName
	 * @throws Exception
	 */
	public static void createPDFByHtmlStringWithCommand(String htmlContent,
			String htmlFullFileName, String pdfFullFileName, String createFileType) throws Exception {
		writeContentToFile(htmlContent, new FileOutputStream(new File(
				htmlFullFileName))); // 将内容写入html文件
		createFileByHtmlFileWithCommand(htmlFullFileName, pdfFullFileName,createFileType);
	}

	/**
	 * 通过已有html文件创建pdf
	 * 
	 * @param htmlFullFileName
	 * @param targetFileFullFileName
	 * @throws IOException
	 * @throws InterruptedException
	 */
	public static void createFileByHtmlFileWithCommand(String htmlFullFileName,
			String targetFileFullFileName, String createFileType)
			throws IOException, InterruptedException {
		String command = getCommand(htmlFullFileName, targetFileFullFileName,
				createFileType);
		System.out.println("command==" + command);

		Process process = Runtime.getRuntime().exec(command);
		InputStream stderr = process.getErrorStream();
		InputStreamReader isr = new InputStreamReader(stderr);
		BufferedReader br = new BufferedReader(isr);
		String line = null;
		while ((line = br.readLine()) != null)
			System.out.println(line);
		process.waitFor();
	}

	private static String getCommand(String htmlNames, String pdfName,
			String createFileType) {
		String createCommand = createFileType
				.equals(FileUtil.CREATE_IMAGE_FILE) ? "wkhtmltoimage"
				: "wkhtmltopdf";
		
		String system = System.getProperty("os.name");
		if ("Linux".equalsIgnoreCase(system)) {// linux 系统
			return "/usr/local/htmltopdf/"+createCommand+"-amd64 " + htmlNames + " " + pdfName;
		} else {
			return "D:/Program Files/wkhtmltopdf/"+createCommand+".exe " + htmlNames
					+ " " + pdfName;
		}
	}

	public static void writeContentToFile(String htmlStr, FileOutputStream fos)
			throws Exception {
		BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(fos,
				"UTF-8"));
		writer.write(htmlStr);
		fos.flush();
		writer.flush();
		fos.close();
		writer.close();
	}
}
