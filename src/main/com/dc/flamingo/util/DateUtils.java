package com.dc.flamingo.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * 日期时间工具类
 * @Class Name DateUtils
 * @Author lee
 * @Create In Jun 16, 2011
 */
public class DateUtils {
    private static Log log = LogFactory.getLog(DateUtils.class);
    public static String datePattern = "yyyy-MM-dd";	//日期格式
    public static String timePattern = datePattern + " HH:mm:ss";	//时间格式
    public static final String MAXDATE = "9999-12-31";	//最大日期
    public static final String MAXTIME = "9999-12-31 23:59:59";	//最大时间
    /**
     * 得到系统格式化过的日期
     * 参数为日期类型
     * @Methods Name getDateStr
     * @Create In Jun 16, 2011 By lee
     * @param aDate
     * @return String
     */
    public static final String getDateStr(Date aDate) {
        SimpleDateFormat df = null;
        String returnValue = "";

        if (aDate != null) {
            df = new SimpleDateFormat(datePattern);
            returnValue = df.format(aDate);
        }

        return (returnValue);
    }
    /**
     * 得到年月日时分秒格式化过的日期
     * 参数为日期类型
     * @Methods Name getTimeStr
     * @Create In Jun 16, 2011 By lee
     * @param aDate
     * @return String
     */
    public static final String getTimeStr(Date aDate) {
        SimpleDateFormat df = null;
        String returnValue = "";

        if (aDate != null) {
            df = new SimpleDateFormat(timePattern);
            returnValue = df.format(aDate);
        }

        return (returnValue);
    }
    /**
     * 得到本月最后一天的日期
     * @Methods Name getLastDayOfMonth
     * @Create In Jun 16, 2011 By lee
     * @param sDate1
     * @return Date
     */
    @SuppressWarnings("deprecation")
	public static Date getLastDayOfMonth(Date sDate1)   {   
        Calendar cDay1 = Calendar.getInstance();   
        cDay1.setTime(sDate1);   
        final int lastDay = cDay1.getActualMaximum(Calendar.DAY_OF_MONTH);   
        Date lastDate = cDay1.getTime();   
        lastDate.setDate(lastDay);   
        return lastDate;   
	}   
    
    /**
     * 得到本月第一天的日期
     * @Methods Name getFirstDayOfMonth
     * @Create In Jun 16, 2011 By lee
     * @param sDate1
     * @return Date
     */
    @SuppressWarnings("deprecation")
	public static Date getFirstDayOfMonth(Date sDate1)   {   
        Calendar cDay1 = Calendar.getInstance();   
        cDay1.setTime(sDate1);   
        final   int   lastDay   =   cDay1.getActualMinimum(Calendar.DAY_OF_MONTH);   
        Date   lastDate   =   cDay1.getTime();   
        lastDate.setDate(lastDay);   
        return   lastDate;   
	}   

    /**
     * 根据提供日期格式及日期字符串加工日期
     * @Methods Name convertStringToDate
     * @Create In Jun 16, 2011 By lee
     * @param aMask	日期格式
     * @param strDate	需要格式化的日期
     * @return
     * @throws ParseException Date
     */
    public static final Date convertStringToDate(String aMask, String strDate) throws ParseException {
        SimpleDateFormat df = null;
        Date date = null;
        df = new SimpleDateFormat(aMask);

        if (log.isDebugEnabled()) {
            log.debug("converting '" + strDate + "' to date with mask '"
                      + aMask + "'");
        }

        try {
            date = df.parse(strDate);
        } catch (ParseException pe) {
            //log.error("ParseException: " + pe);
            throw new ParseException(pe.getMessage(), pe.getErrorOffset());
        }

        return (date);
    }

    /**
     * 得到Calendar型当前日期
     * @Methods Name getToday
     * @Create In Jun 16, 2011 By lee
     * @return
     * @throws ParseException Calendar
     */
    public static Calendar getToday() throws ParseException {
        Date today = new Date();
        SimpleDateFormat df = new SimpleDateFormat(datePattern);
        String todayAsString = df.format(today);
        Calendar cal = new GregorianCalendar();
        cal.setTime(convertStringToDate(todayAsString));
        return cal;
    }
    
    /**
     * 获取系统日期格式的日期
     * @Methods Name getCurrentDate
     * @Create In Jun 16, 2011 By lee
     * @return java.util.Date
     */
    public static java.util.Date getCurrentDate() {
		Date today = new Date();
		SimpleDateFormat df = new SimpleDateFormat(datePattern);
		String todayAsString = df.format(today);
		Calendar cal = new GregorianCalendar();
		cal.setTime(convertStringToDate(todayAsString));
		return (java.util.Date) cal.getTime();
	}
    
    
    /**
     * 获取系统日期格式的日期字符串
     * @Methods Name getCurrentDate
     * @Create In Jun 16, 2011 By lee
     * @return java.util.Date
     */
    public static String getCurrentDateStr() {
		return DateUtils.convertDateToString(DateUtils.getCurrentDate());
	}
    
    
    /**
     * 获取系统时间格式的日期
     * @Methods Name getCurrentDateTime
     * @Create In Jun 16, 2011 By lee
     * @return java.util.Date
     */
    public static java.util.Date getCurrentDateTime(){
   	 	Date today = new Date();
        SimpleDateFormat df = new SimpleDateFormat(DateUtils.timePattern);
        String todayAsString = df.format(today);
        Calendar cal = new GregorianCalendar();
			cal.setTime(convertStringToDate(todayAsString));
        return (java.util.Date)cal.getTime();
   }
    
    /**
     * 获取系统时间格式的日期字符串
     * @Methods Name getCurrentDateTime
     * @Create In Jun 16, 2011 By lee
     * @return java.util.Date
     */
    public static String getCurrentDateTimeStr(){
   	 	return DateUtils.convertDateTimeToString(DateUtils.getCurrentDateTime());
   }
    
    
    /**
     * 将日期转为指定格式的字符串
     * @Methods Name getDateTime
     * @Create In Jun 16, 2011 By lee
     * @param aMask	日期格式
     * @param aDate	日期
     * @return String
     */
    public static final String getDateTime(String aMask, Date aDate) {
        SimpleDateFormat df = null;
        String returnValue = "";
        if (aDate == null) {
            log.error("aDate is null!");
        } else {
            df = new SimpleDateFormat(aMask);
            returnValue = df.format(aDate);
        }
        return (returnValue);
    }

    /**
     * 将日期转为系统格式的（年月日）日期字符串
     * @Methods Name convertDateToString
     * @Create In Jun 16, 2011 By lee
     * @param aDate
     * @return String
     */
    public static final String convertDateToString(Date aDate) {
        return getDateTime(datePattern, aDate);
    }
    
    /**
     * 将日期转成年月日 时分秒格式的字符串
     * @Methods Name convertDateTimeToString
     * @Create In Jun 16, 2011 By lee
     * @param aDate
     * @return String
     */
    public static final String convertDateTimeToString(Date aDate) {
        return getDateTime(timePattern, aDate);
    }

    /**
     * 将字符串转为对应的日期
     * @Methods Name convertStringToDate
     * @Create In Jun 16, 2011 By lee
     * @param strDate
     * @return Date
     */
    public static Date convertStringToDate(String strDate) {
        Date aDate = null;

        try {
            if (log.isDebugEnabled()) {
                log.debug("converting date with pattern: " + datePattern);
            }
            String pattern = "\\d{4}[-|/]\\d{2}[-|/]\\d{2}[ ]\\d{2}[:]\\d{2}[:]\\d{2}";	
			if(strDate.matches(pattern)) {
				if(strDate.contains("/")) {
				    aDate = convertStringToDate("yyyy/MM/dd HH:mm:ss", strDate);
				} else {
				    aDate = convertStringToDate(timePattern, strDate);
				}
			} else {
				if(strDate.contains("/")) {
				    aDate = convertStringToDate("yyyy/MM/dd", strDate);
				} else {
				    aDate = convertStringToDate(datePattern, strDate);
				}
			}
        } catch (ParseException pe) {
            log.error("Could not convert '" + strDate
                      + "' to a date, throwing exception");
            pe.printStackTrace();

        }

        return aDate;
    }

    /**
     * 在指定日期增加天数得到日期
     * @Methods Name addDays
     * @Create In Jun 16, 2011 By lee
     * @param date
     * @param days
     * @return Date
     */
    public static Date addDays(Date date, int days) {
        return add(date, days, Calendar.DATE);
    }

    /**
     * 在指定日期增加月数得到日期
     * @Methods Name addMonths
     * @Create In Jun 16, 2011 By lee
     * @param date
     * @param months
     * @return Date
     */
    public static Date addMonths(Date date, int months) {
        return add(date, months, Calendar.MONTH);
    }
    
    /**
     * 提供日期自处理方法，
     * @Methods Name add
     * @Create In Jun 16, 2011 By lee
     * @param date
     * @param amount	需要增加或减少的量
     * @param field		需要增加或减少的参数（年/月/日）
     * @return Date
     */
    private static Date add(Date date, int amount, int field) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(field, amount);
        return calendar.getTime();
    }

    /**
     * 是否是同一天
     * @Methods Name isTheSameDay
     * @Create In Jun 16, 2011 By lee
     * @param startDate
     * @param endDate
     * @return boolean
     */
	 public static boolean isTheSameDay(Date startDate, Date endDate) {
		 SimpleDateFormat myFormatter = new SimpleDateFormat(datePattern);
	       long value = 1;
	         try {
				 Date sdate = myFormatter.parse(getDateStr(startDate));
				 Date edate = myFormatter.parse(getDateStr(endDate));
				 value = (edate.getTime()-sdate.getTime());
			} catch (ParseException e) {
				e.printStackTrace();
			}
			if(value==0){
				return true;
			}
	        return false;
     }
}
