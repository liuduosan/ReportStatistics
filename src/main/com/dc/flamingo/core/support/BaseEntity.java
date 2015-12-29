package com.dc.flamingo.core.support;

import java.io.Serializable;

/**
 * 实体基类，要求所有实体必须继承此类
 * @Class Name BaseEntity
 * @Author lee
 * @Create In May 12, 2011
 */
public abstract class BaseEntity implements Serializable{
	private static final long serialVersionUID = 6093121651295128206L;
	protected Long oid;

	protected BaseEntity(){
	}

	public Long getOid() {
		return oid;
	}

	public void setOid(Long oid) {
		this.oid = oid;
	}
}
