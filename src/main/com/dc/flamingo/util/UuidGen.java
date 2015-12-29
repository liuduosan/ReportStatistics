package com.dc.flamingo.util;


/**
 * Uuid生成器
 * @Title: UuidGen.java
 * @Description: <br>
 *               <br>
 * @Company: DigitalChina
 * @Created on Feb 29, 2012 5:13:14 PM
 * @author lixbh
 * @version $Revision: 1.1 $
 * @since 1.0
 */
public class UuidGen {

	public static Long getUuid(Class<?> clazz) {
		return Long.valueOf((clazz.getName() + System.currentTimeMillis()).hashCode());
	}
	
}
