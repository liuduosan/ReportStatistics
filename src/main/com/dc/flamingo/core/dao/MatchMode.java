package com.dc.flamingo.core.dao;

import org.apache.commons.lang3.StringUtils;

/**
 * 匹配方式
 * 用于字符串类型查询配置时选择匹配方式
 * @Class Name MatchMode
 * @Author lee
 * @Create In 2012-2-8
 */
public enum MatchMode {
	  START {
		@Override
		public String toMatchString(String paramString) {
			if(StringUtils.isBlank(paramString)){
				paramString = "";
			}
			return paramString+"%";
		}
	}, END {
		@Override
		public String toMatchString(String paramString) {
			if(StringUtils.isBlank(paramString)){
				paramString = "";
			}
			return "%"+paramString;
		}
	}, ANYWHERE {
		@Override
		public String toMatchString(String paramString) {
			if(StringUtils.isBlank(paramString)){
				paramString = "";
			}
			return "%"+paramString+"%";
		}
	};
	  public abstract String toMatchString(String paramString);
}
