package com.dc.assess.validate.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.dc.assess.validate.dao.ValidateUserDao;
import com.dc.flamingo.core.service.DataService;

@Service
@Transactional(readOnly=true)
public class ValidateUserService extends DataService<Object> {

	@Autowired
	private ValidateUserDao validateUserDao;
	
	
	
	/**
	 * <p>
	 * <pre>
	 * <b>方法描述：</b>
	 * 	登录验证用户权限
	 * <b>作者：</b>
	 * 	wangchao1(王超)
	 * 	邮箱：<a href="mailto:wangchao1@digitalchina.com" >wangchao1@digitalchina.com</a>	
	 * <b>创建时间：</b> 
	 * 	2015年12月11日 下午4:25:07
	 * </pre>
	 * </p>
	 */
	public boolean validateItCode(String itcode) {
			List<Map<java.lang.String, Object>>  list = validateUserDao.validateItcode(itcode);
			if(list.size()>0){
				if(list.get(0).get("checkedNodes")!=null){
					return true;
				}
			}
		return false;
	}
	
	
	/**
	 * <p>
	 * <pre>
	 * <b>方法描述：</b>
	 * 	根据itcode查询用户权限
	 * <b>作者：</b>
	 * 	wangchao1(王超)
	 * 	邮箱：<a href="mailto:wangchao1@digitalchina.com" >wangchao1@digitalchina.com</a>	
	 * <b>创建时间：</b> 
	 * 	2015年12月11日 下午4:26:02
	 * </pre>
	 * </p>
	 */
	public String getItcode(String itcode){
		List<Map<java.lang.String, Object>>  list = validateUserDao.validateItcode(itcode);
		  String checkedNodes="";
		  for (int i = 0; i < list.size(); i++) {
				if(list.size()-1==i){
					checkedNodes+=list.get(i).get("checkedNodes");
		    	}else{
		    		checkedNodes+=list.get(i).get("checkedNodes")+ ",";
		    	}
			}
		return checkedNodes;
	}
	
	/**
	 * <p>
	 * <pre>
	 * <b>方法描述：</b>
	 * 	根据itcode查询是否是系统管理员
	 * <b>作者：</b>
	 * 	wangchao1(王超)
	 * 	邮箱：<a href="mailto:wangchao1@digitalchina.com" >wangchao1@digitalchina.com</a>	
	 * <b>创建时间：</b> 
	 * 	2015年12月17日 上午11:18:12
	 * </pre>
	 * </p>
	 */
	public List<Map<java.lang.String, Object>> getItcodeAdministrators(String itcode) {
		List<Map<java.lang.String, Object>>  list = validateUserDao.getItcodeAdministrators(itcode);
		return list;
	}
	
}
