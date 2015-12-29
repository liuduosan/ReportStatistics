package com.dc.flamingo.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


/**
 * 手机短消息发送类
 * @author yaokuo
 *
 */
public class SendMessageUtils {
	
	private static Connection getConnection(){
		Connection conn = null;
		String serverIP = "";
		String serverName = "";
		String fn = ""; 
		String url = ""; 
		String user = "";
		String password = "";
		try {
			serverIP = PropertiesUtils.getProperty("system.mobile.serverIPandPort", "172.16.1.66:1433");
			serverName = PropertiesUtils.getProperty("system.mobile.serverName", "SMSDB");
			fn = "com.microsoft.sqlserver.jdbc.SQLServerDriver"; 
			url = "jdbc:sqlserver://"+serverIP+";DatabaseName="+serverName; 
			user = PropertiesUtils.getProperty("user", "oasms");
			password = PropertiesUtils.getProperty("passwd", "0909oa");
			Class.forName(fn);
			conn = DriverManager.getConnection(url,user,password);
		}catch(Exception e){
			e.printStackTrace();
		}
		return conn;
	}
	private static void closeCon(Connection con) throws SQLException {
		if (con != null) {
			con.close();
		}
	}

	private static void closeStm(Statement stm) throws SQLException {
		if (stm != null) {
			stm.close();
		}
	}

	private static void closeRs(ResultSet rs) throws SQLException {
		if (rs != null) {
			rs.close();
		}
	}

	/**
	 * 发送短信方法
	 * @param mobile String
	 * @param content String 
	 * @throws Exception
	 */
	public static void sendMessage(String mobileTo, String content)throws Exception{
		
		LogUtils logUtil=LogUtils.getLogUtil(SendMessageUtils.class);
		// 收取短信的号码
		String numbers[] = mobileTo.split(";");
		// 获取连接对象
		Statement stmt = null;
		Connection conn = getConnection();
		// 拼装插入sql语句
		String insertStr="INSERT INTO SendToISMG_N(SMS_Style,SMS_Content,SMS_MobNum,sourceID,DATA_PATH,DOC_UNID,REQUESTID,NODEID,ITCODE) values (";
		if(null!=conn){
			stmt = conn.createStatement();
			for(String mobile : numbers){
				// 以下变量都为空就可以了。
				String dbpath = "";
				String unid = "";
				String docnum = "";
				String nodeid = "";
				String itcode = "";
				StringBuffer sb=new StringBuffer();
				sb.append(insertStr);
				sb.append("63");// 不需要修改
				sb.append(",");
				
				sb.append("'");
				sb.append(content);// 短信内容，至需要修改这里就可以了
				sb.append("'");
				sb.append(",");
							
				sb.append("'");
				sb.append(mobile);// 号码
				sb.append("'");
				sb.append(",");
				
				sb.append("'");
				sb.append("MS");
				sb.append("'");
				sb.append(",");
				
				sb.append("'");
				sb.append(dbpath);
				sb.append("'");
				sb.append(",");
				
				sb.append("'");
				sb.append(unid);
				sb.append("'");
				sb.append(",");
				
				sb.append("'");
				sb.append(docnum);
				sb.append("'");
				sb.append(",");
				
				sb.append("'");
				sb.append(nodeid);
				sb.append("'");
				sb.append(",");
								
				sb.append("'");
				sb.append(itcode);
				sb.append("'");
				
				sb.append(")");
				stmt.execute(sb.toString());
				logUtil.debug("获取发送短信成功！");
			}
			SendMessageUtils.closeStm(stmt);
			SendMessageUtils.closeCon(conn);
		}
	}

}
