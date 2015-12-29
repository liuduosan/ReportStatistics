package com.dc.assess.user.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.dc.assess.user.dao.UserDao;
import com.dc.assess.user.dto.UserManage;

@Service
@Transactional
public class UserService {
	
	@Autowired
	private UserDao userDao;
	public List<String> queryByUserNameOrITCode(String UserName, String itcode) {
		List<String> returnList = new ArrayList<String>();
		List<UserManage> dtoList = new ArrayList<UserManage>();
		List<Map<String,Object>> list =  userDao.queryByUserNameOrITCode(UserName, itcode);
		if(list!=null){
		for (Map<String, Object> map : list) {
			UserManage u = new UserManage();
			u.setItCode(Null(map.get("ItCode")));
			u.setUserName(Null(map.get("UserName")));
			dtoList.add(u);
		}}
		for (UserManage dto : dtoList) {
			String strTemp = dto.getUserName()+"/"+dto.getItCode();
			returnList.add(strTemp);
		}
		return returnList;
		
	}
	public List<UserManage> queryByUserNoOrITCode(String userNo,String itcode,int currentPage, int numPerPage){
		
		List<UserManage> returnList = new ArrayList<UserManage>();
		List<Map<String,Object>> list   = new ArrayList<Map<String,Object>>();
		if(isChinese(itcode)){
			list = userDao.queryByDeptName(userNo, itcode,currentPage,numPerPage);
		}else{
			list = userDao.queryByUserNoOrITCode(userNo, itcode,currentPage,numPerPage);
		}
		for (Map<String, Object> map : list) {
			UserManage u = new UserManage();
			u.setId(Null(map.get("id")));
			u.setUserNo(Null(map.get("UserNo")));
			u.setCheckedNodes(Null(map.get("checkedNodes")));
			u.setItCode(Null(map.get("ItCode")));
			u.setUserName(Null(map.get("UserName")));
			u.setGender(Null(map.get("Gender")));
			u.setIDCard(Null(map.get("IDCard")));
			u.setFlatName(Null(map.get("FlatName")));
			u.setFlatCode(Null(map.get("FlatCode")));
			u.setCompanyCode(Null(map.get("CompanyCode")));
			u.setCompanyName(Null(map.get("CompanyName")));
			u.setDeptNO(Null(map.get("DeptNO")));
			u.setDeptName(Null(map.get("DeptName")));
			u.setTitleCode(Null(map.get("TitleCode")));
			u.setTitleName(Null(map.get("TitleName")));
			u.setPostCode(Null(map.get("PostCode")));
			u.setPost(Null(map.get("Post")));
			u.setCostCenter(Null(map.get("CostCenter")));
			u.setCostCenterName(Null(map.get("CostCenterName")));
			u.setAuthor(Null(map.get("author")));
			u.setDescription(Null(map.get("description")));
			returnList.add(u);
		}
		return returnList;
	}
	
	public int countByUserNoOrITCode(String UserName,String itcode){
		if(isChinese(itcode)){
			return userDao.countByDeptName(UserName, itcode);
		}else{
			return userDao.countByUserNoOrITCode(UserName, itcode);
		}
	}
	

/**
 * null字符串处理
 * @param obj
 * @return
 */
public String Null(Object obj) {
	if (obj == null) {
		return "";
	} else {
		return obj.toString();
	}
}

public void delete(String id){
	userDao.delete(id);
}

public void add(String itcode, String checkedNodes,String author,String description) {
	if(!Null(itcode).equals("") && !Null(checkedNodes).equals("")){
		if(!userDao.isExist(itcode, checkedNodes)  ){
			this.userDao.add( itcode,  checkedNodes,author,description);
		}
	}
}
public void update(String itcode, String checkedNodes,String author,String description, String id) {
	userDao.update( itcode,  checkedNodes,author,description,id);
}

public List<String> findNodesByITCode(String itcode){
	return userDao.findNodesByITCode(itcode);
}

//根据Unicode编码完美的判断中文汉字和符号
		private static boolean isChinese(char c) {
			Character.UnicodeBlock ub = Character.UnicodeBlock.of(c);
			if (ub == Character.UnicodeBlock.CJK_UNIFIED_IDEOGRAPHS
					|| ub == Character.UnicodeBlock.CJK_COMPATIBILITY_IDEOGRAPHS
					|| ub == Character.UnicodeBlock.CJK_UNIFIED_IDEOGRAPHS_EXTENSION_A
					|| ub == Character.UnicodeBlock.CJK_UNIFIED_IDEOGRAPHS_EXTENSION_B
					|| ub == Character.UnicodeBlock.CJK_SYMBOLS_AND_PUNCTUATION
					|| ub == Character.UnicodeBlock.HALFWIDTH_AND_FULLWIDTH_FORMS
					|| ub == Character.UnicodeBlock.GENERAL_PUNCTUATION) {
				return true;
			}
			return false;
		}

		// 完整的判断中文汉字和符号
		public static boolean isChinese(String strName) {
			char[] ch = strName.toCharArray();
			for (int i = 0; i < ch.length; i++) {
				char c = ch[i];
				if (isChinese(c)) {
					return true;
				}
			}
			return false;
		}
}
