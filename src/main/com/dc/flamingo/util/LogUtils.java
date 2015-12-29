package com.dc.flamingo.util;


import org.apache.log4j.Logger;
/**
 * 
 * @author lizz
 * 通用日志处理
 */

public class LogUtils {
	 
    private Logger loggerDebug = null;
    private Logger loggerError = null;
    private Logger loggerWarn = null;
    private Logger loggerInfo = null;
    private Logger loggerFatal = null;
    private String o_message = null;
	 
    private LogUtils() {}
    
    public LogUtils(String msg){
    	o_message=msg;
    }
	 
    public static LogUtils getLogUtil(Object message) {
        return new LogUtils(message.toString());  
    }
	 
    public void debug(Object message) {
        if (loggerDebug == null) {
            loggerDebug = Logger.getLogger("debugfile");
        }
        loggerDebug.debug("[" + o_message + "] - " + message+"\r\n");
    }

    public void error(Object message) {
        if (loggerError == null) {
            loggerError = Logger.getLogger("errorfile");
        }
        loggerError.error("[" + o_message + "] - " + message+"\r\n");
        
    }

    public void info(Object message) {
        if (loggerInfo == null) {
            loggerInfo = Logger.getLogger("infofile");
        }
        loggerInfo.info("[" + o_message + "] - " + message+"\r\n");
        
    }

    public void warn(Object message) {
        if (loggerWarn == null) {
            loggerWarn = Logger.getLogger("warnfile");
        }
        loggerWarn.warn("[" + o_message + "] - " + message);
    }
	 
    public void fatal(Object message) {
        if (loggerFatal == null) {
            loggerFatal = Logger.getLogger("fatalfile");
        }
        loggerFatal.fatal("[" + o_message + "] - " + message);
    }
}
