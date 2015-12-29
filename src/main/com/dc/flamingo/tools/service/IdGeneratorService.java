package com.dc.flamingo.tools.service;

import com.dc.flamingo.tools.dao.IdGeneratorDao;

public class IdGeneratorService {
	private IdGeneratorDao idGeneratorDao;
	/**
	 * 根据KEY读取最新编号
	 * @Methods Name getLastId
	 * @Create In Jun 22, 2011 By lee
	 * @param key
	 * @return long
	 */
	public long getLastId(String key){
		return idGeneratorDao.getLastId(key);
	}

	/**
	 * 根据KEY生成新的编号并返回最新编号
	 * @Methods Name saveLastId
	 * @Create In Jun 22, 2011 By lee
	 * @param key
	 * @return long
	 */
	public long saveLastId(String key){
		return idGeneratorDao.saveLastId(key);
	}

	public IdGeneratorDao getIdGeneratorDao() {
		return idGeneratorDao;
	}

	public void setIdGeneratorDao(IdGeneratorDao idGeneratorDao) {
		this.idGeneratorDao = idGeneratorDao;
	}

}
