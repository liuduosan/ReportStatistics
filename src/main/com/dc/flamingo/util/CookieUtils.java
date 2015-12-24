package com.dc.flamingo.util;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.Cookie;

/**
 * Cookie处理工具类
 * @author lizz
 *
 */

public class CookieUtils {
	
	/**
	 * 将cookies转换成Map
	 * @param cookies
	 * @return
	 */
    public static Map<String,Cookie> toMap(Cookie[] cookies){
    	if(cookies == null || cookies.length == 0)
    		return new HashMap<String,Cookie>(0);
    	
    	Map<String,Cookie> map = new HashMap<String,Cookie>(cookies.length * 2);
    	for(Cookie c : cookies) {
    		map.put(c.getName(), c);
    	}
    	return map;
    }

}
