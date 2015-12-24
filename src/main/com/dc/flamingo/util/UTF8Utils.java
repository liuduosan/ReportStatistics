package com.dc.flamingo.util;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;
/**
 * UTF8字符串处理工具类
 * 
 * @Class Name StringUtils
 * @Author lizz
 * @Create In Jun 21, 2011
 * 
 *         注意：如果要增加新的方法，请优先检查此方法是否在其他的StringUtils类中，除了org.apache.commons.lang3.
 *         StringUtils，
 * 
 */
@SuppressWarnings("serial")
public class UTF8Utils extends org.apache.commons.lang3.StringUtils {
	private static final int LENGTH_OF_CHINESE = 5;
	private final static String[] hex = { "00", "01", "02", "03", "04", "05",
			"06", "07", "08", "09", "0A", "0B", "0C", "0D", "0E", "0F", "10",
			"11", "12", "13", "14", "15", "16", "17", "18", "19", "1A", "1B",
			"1C", "1D", "1E", "1F", "20", "21", "22", "23", "24", "25", "26",
			"27", "28", "29", "2A", "2B", "2C", "2D", "2E", "2F", "30", "31",
			"32", "33", "34", "35", "36", "37", "38", "39", "3A", "3B", "3C",
			"3D", "3E", "3F", "40", "41", "42", "43", "44", "45", "46", "47",
			"48", "49", "4A", "4B", "4C", "4D", "4E", "4F", "50", "51", "52",
			"53", "54", "55", "56", "57", "58", "59", "5A", "5B", "5C", "5D",
			"5E", "5F", "60", "61", "62", "63", "64", "65", "66", "67", "68",
			"69", "6A", "6B", "6C", "6D", "6E", "6F", "70", "71", "72", "73",
			"74", "75", "76", "77", "78", "79", "7A", "7B", "7C", "7D", "7E",
			"7F", "80", "81", "82", "83", "84", "85", "86", "87", "88", "89",
			"8A", "8B", "8C", "8D", "8E", "8F", "90", "91", "92", "93", "94",
			"95", "96", "97", "98", "99", "9A", "9B", "9C", "9D", "9E", "9F",
			"A0", "A1", "A2", "A3", "A4", "A5", "A6", "A7", "A8", "A9", "AA",
			"AB", "AC", "AD", "AE", "AF", "B0", "B1", "B2", "B3", "B4", "B5",
			"B6", "B7", "B8", "B9", "BA", "BB", "BC", "BD", "BE", "BF", "C0",
			"C1", "C2", "C3", "C4", "C5", "C6", "C7", "C8", "C9", "CA", "CB",
			"CC", "CD", "CE", "CF", "D0", "D1", "D2", "D3", "D4", "D5", "D6",
			"D7", "D8", "D9", "DA", "DB", "DC", "DD", "DE", "DF", "E0", "E1",
			"E2", "E3", "E4", "E5", "E6", "E7", "E8", "E9", "EA", "EB", "EC",
			"ED", "EE", "EF", "F0", "F1", "F2", "F3", "F4", "F5", "F6", "F7",
			"F8", "F9", "FA", "FB", "FC", "FD", "FE", "FF" };
	private final static byte[] val = { 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F,
			0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F,
			0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F,
			0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F,
			0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x00, 0x01,
			0x02, 0x03, 0x04, 0x05, 0x06, 0x07, 0x08, 0x09, 0x3F, 0x3F, 0x3F,
			0x3F, 0x3F, 0x3F, 0x3F, 0x0A, 0x0B, 0x0C, 0x0D, 0x0E, 0x0F, 0x3F,
			0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F,
			0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F,
			0x3F, 0x3F, 0x3F, 0x0A, 0x0B, 0x0C, 0x0D, 0x0E, 0x0F, 0x3F, 0x3F,
			0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F,
			0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F,
			0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F,
			0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F,
			0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F,
			0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F,
			0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F,
			0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F,
			0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F,
			0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F,
			0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F,
			0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F,
			0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F,
			0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F };

	private static List<Character> specialCharList = new ArrayList<Character>() {
		{
			add('-');
			add('_');
			add('.');
			add('!');
			add('~');
			add('*');
			add('\\');
			add('(');
			add(')');
		}
	};

	/**
	 * 对参数进行编码，中文转换成utf8格式
	 * 
	 * @param source
	 * @return
	 */
	public static String escape(String source) {
		if (source == null) {
			throw new IllegalArgumentException("source string must not be null");
		}

		StringBuffer sbuf = new StringBuffer();
		for (int i = 0; i < source.length(); i++) {
			int ch = source.charAt(i);
			if (dontNeedProcess(ch)) {
				sbuf.append((char) ch);
			} else if (ch <= 0x007F) {
				sbuf.append('%');
				sbuf.append(hex[ch]);
			} else {
				sbuf.append("%u");
				sbuf.append(hex[(ch >>> 8)]);
				sbuf.append(hex[(0x00FF & ch)]);
			}
		}
		return sbuf.toString();
	}

	private static boolean isSpecialCharacter(int ch) {
		return specialCharList.contains((char) ch);
	}

	private static boolean isLetter(int ch) {
		return isUpperCase(ch) || isLowerCase(ch);
	}

	private static boolean isDigit(int ch) {
		return '0' <= ch && ch <= '9';
	}

	private static boolean isLowerCase(int ch) {
		return 'a' <= ch && ch <= 'z';
	}

	private static boolean isUpperCase(int ch) {
		return 'A' <= ch && ch <= 'Z';
	}

	/**
	 * 对参数进行解码,比如空格、汉字
	 * 
	 * @param source
	 * @return
	 */
	public static String unescape(String source) {
		if (source == null) {
			throw new IllegalArgumentException(
					"source string must not be null.");
		}

		StringBuffer sbuf = new StringBuffer();
		int i = 0;
		int len = source.length();
		while (i < len) {
			int ch = source.charAt(i);
			if (isChinese(source, i)) {
				sbuf.append(convertToChinese(source, i));
				i += LENGTH_OF_CHINESE;
			} else if (needProcess(source, i)) {
				sbuf.append(convert(source, i));
				i += 2;
			} else {
				sbuf.append((char) ch);
			}
			i++;
		}
		return sbuf.toString();
	}
	
	private static char convert(String source, int i) {
		int cint = 0;
		cint = (cint << 4) | val[source.charAt(i + 1)];
		cint = (cint << 4) | val[source.charAt(i + 2)];
		return (char) cint;
	}

	private static boolean needProcess(String source, int i) {
		return '%' == source.charAt(i) && 'u' != source.charAt(i + 1);
	}

	private static boolean isChinese(String source, int i) {
		return '%' == source.charAt(i) && 'u' == source.charAt(i + 1);
	}

	private static char convertToChinese(String source, int i) {
		int cint = 0;
		cint = (cint << 4) | val[source.charAt(i + 2)];
		cint = (cint << 4) | val[source.charAt(i + 3)];
		cint = (cint << 4) | val[source.charAt(i + 4)];
		cint = (cint << 4) | val[source.charAt(i + 5)];
		return (char) cint;
	}

	private static boolean dontNeedProcess(int ch) {
		return isLetter(ch) || isDigit(ch) || isSpecialCharacter(ch);
	}

	/**
	 * 
	 * @Title: utf8Encode
	 * @Description: 对字符串进行utf-8编码
	 * @param @param text
	 * @param @return 设定文件
	 * @return String 返回类型
	 * @throws
	 */
	public static String utf8Encode(String text) {
		StringBuffer result = new StringBuffer();
		for (int i = 0; i < text.length(); i++) {
			char c = text.charAt(i);
			if (c >= 0 && c <= 255) {
				result.append(c);
			} else {
				byte[] b = new byte[0];
				try {
					b = Character.toString(c).getBytes("UTF-8");
				} catch (Exception ex) {

				}
				for (int j = 0; j < b.length; j++) {
					int k = b[j];
					if (k < 0)
						k += 256;
					result.append("%" + Integer.toHexString(k).toUpperCase());
				}
			}
		}
		return result.toString();
	}

	/**
	 * 
	 * @Title: utf8Decode
	 * @Description: 对utf8字符串进行解码
	 * @param @param text
	 * @param @return 设定文件
	 * @return String 返回解码之后的字符串，还原中文
	 * @throws
	 */
	public static String utf8Decode(String text) {
		StringBuffer  result =new StringBuffer();
		int p = 0;
		if (text != null && text.length() > 0) {
			text = text.toLowerCase();
			p = text.indexOf("%e");
			if (p == -1)
				return text;
			while (p != -1) {
				result.append(text.substring(0, p));
				text = text.substring(p, text.length());
				if (text.equals("") || text.length() < 9)
					return result.toString();
				result.append(codeToWord(text.substring(0, 9)));
				text = text.substring(9, text.length());
				p = text.indexOf("%e");
			}
		}
		result.append(text);
		return result.toString();
	}

	/**
	 * 
	 * @Title: codeToWord
	 * @Description: 私有类，将一个中文编码解析成中文
	 * @param @param text
	 * @param @return 设定文件
	 * @return String 返回类型
	 * @throws
	 */
	private static String codeToWord(String text) {
		String result;
		byte[] code = new byte[3];
		code[0] = (byte) (Integer.parseInt(text.substring(1, 3), 16) - 256);
		code[1] = (byte) (Integer.parseInt(text.substring(4, 6), 16) - 256);
		code[2] = (byte) (Integer.parseInt(text.substring(7, 9), 16) - 256);
		try {
			result = new String(code, "UTF-8");
		} catch (UnsupportedEncodingException ex) {
			result = null;
		}
		return result;
	}

}
