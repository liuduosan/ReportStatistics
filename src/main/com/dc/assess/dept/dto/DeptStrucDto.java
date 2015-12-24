package com.dc.assess.dept.dto;

import java.util.List;


public class DeptStrucDto {

	public String departNo1;
	public String departNo2;
	public String deptname;
	public String level;
	public String flatCode;
	public List<DeptStrucDto> children;
	
	
	
	public String getDepartNo1() {
		return departNo1;
	}
	public void setDepartNo1(String departNo1) {
		this.departNo1 = departNo1;
	}
	public String getDepartNo2() {
		return departNo2;
	}
	public void setDepartNo2(String departNo2) {
		this.departNo2 = departNo2;
	}
	public String getDeptname() {
		return deptname;
	}
	public void setDeptname(String deptname) {
		this.deptname = deptname;
	}
	public String getLevel() {
		return level;
	}
	public void setLevel(String level) {
		this.level = level;
	}
	public List<DeptStrucDto> getChildren() {
		return children;
	}
	public void setChildren(List<DeptStrucDto> children) {
		this.children = children;
	}
	public String getFlatCode() {
		return flatCode;
	}
	public void setFlatCode(String flatCode) {
		this.flatCode = flatCode;
	}
	
	
}
