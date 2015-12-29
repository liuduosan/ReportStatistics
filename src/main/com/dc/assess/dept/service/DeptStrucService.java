package com.dc.assess.dept.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.dc.assess.dept.dao.DeptStrucDao;
import com.dc.assess.dept.dto.DeptStrucDataDto;
import com.dc.assess.dept.dto.DeptStrucDto;
import com.dc.flamingo.core.service.DataService;

@Service
@Transactional(readOnly=true)
public class DeptStrucService extends DataService<Object> {

	@Autowired
	private DeptStrucDao deptStrucDao;
	
	
	/**
	 * <p>
	 * <pre>
	 * <b>方法描述：</b>
	 * 	查询部门树的父节点
	 * <b>作者：</b>
	 * 	wangchao1(王超)
	 * 	邮箱：<a href="mailto:wangchao1@digitalchina.com" >wangchao1@digitalchina.com</a>	
	 * <b>创建时间：</b> 
	 * 	2015年11月30日 上午10:16:19
	 * </pre>
	 * </p>
	 */
	public List<DeptStrucDto> createProdLayerTree() {
		List<DeptStrucDataDto> deptStrucLevel1 = deptStrucDao.getDeptStrucLevel1();
		DeptStrucDataDto deptStrucDataDto = deptStrucLevel1.get(0);
		return createProdLayerTreeChild(deptStrucDataDto.getDepartNo2(), null);
	}
	
	/**
	 * <p>
	 * <pre>
	 * <b>方法描述：</b>
	 * 	根据父节点创建子节点
	 * <b>作者：</b>
	 * 	wangchao1(王超)
	 * 	邮箱：<a href="mailto:wangchao1@digitalchina.com" >wangchao1@digitalchina.com</a>	
	 * <b>创建时间：</b> 
	 * 	2015年11月30日 上午10:17:10
	 * </pre>
	 * </p>
	 */
	public List<DeptStrucDto> createProdLayerTreeChild(String parentId, DeptStrucDto parentNode) {
		List<DeptStrucDataDto> allData = deptStrucDao.getAllDatafromCache();
		List<DeptStrucDto> resultList = new ArrayList<DeptStrucDto>();
		Boolean existChild = false;
		for(DeptStrucDataDto element : allData) {
			if(parentId.equals(element.getDepartNo1())) {
				existChild = true;
				DeptStrucDto node = new DeptStrucDto();
				node.setDepartNo1(element.getDepartNo1()==null?"":element.getDepartNo1());
				node.setDepartNo2(element.getDepartNo2());
				node.setDeptname(element.getDeptname());
				node.setLevel(element.getLev1());
				node.setFlatCode(element.getFlatCode());
				resultList.add(node);
				if(element.getDepartNo2()!=null&&!"".equals(element.getDepartNo2())){
					List<DeptStrucDataDto> childrenByNum = deptStrucDao.getChildrenByNum(element.getDepartNo2());
					if(childrenByNum!=null&&childrenByNum.size()>0){
						node.setChildren(createProdLayerTreeChild(element.getDepartNo2(), node));
					}
				}else{
					return resultList;
				}
			}
		}
		return resultList;
	} 
	
}
