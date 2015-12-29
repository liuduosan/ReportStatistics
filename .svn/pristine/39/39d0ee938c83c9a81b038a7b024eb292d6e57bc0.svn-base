package com.dc.flamingo.tools.dao;

import com.dc.flamingo.core.dao.Criteria;
import com.dc.flamingo.core.dao.JdbcDao;
import com.dc.flamingo.core.dao.Restrictions;
import com.dc.flamingo.tools.entity.IdGenerator;

/**
 * 编号生成器
 * @Class Name NumbersGeneratorDao
 * @Author lee
 * @Create In May 23, 2011
 */
public class IdGeneratorDao extends JdbcDao{

	/**
	 * 根据KEY读取最新编号
	 * @Methods Name getLastId
	 * @Create In Jun 22, 2011 By lee
	 * @param key
	 * @return long
	 */
	public long getLastId(String key){
		Criteria c = super.getCriteria(IdGenerator.class);
		c.add(Restrictions.eq("idKey", key));
		IdGenerator ng = (IdGenerator) c.uniqueResult();
		if(ng!=null){
			return ng.getLastId();
		}else{
			return 0;
		}
	}
	/**
	 * 根据KEY生成新的编号并返回最新编号
	 * @Methods Name saveLastId
	 * @Create In Jun 22, 2011 By lee
	 * @param key
	 * @return long
	 */
	public synchronized long saveLastId(String key){
		Criteria c = super.getCriteria(IdGenerator.class);
		c.add(Restrictions.eq("idKey", key));
		IdGenerator ng = (IdGenerator) c.uniqueResult();
		long lastNumber =1;
		if(ng!=null){
			lastNumber = ng.getLastId()+lastNumber;
			ng.setLastId(lastNumber);
		}else{
			ng = new IdGenerator();
			ng.setIdKey(key);
			ng.setLastId(lastNumber);
		}
		super.save(ng);
		return lastNumber;
	}
	
	/**
	 * 根据KEY和最新编号更新编号生成器编号信息
	 * @Methods Name saveLastId
	 * @Create In Jun 22, 2011 By lee
	 * @param key
	 * @param lastNumber void
	 */
	public void saveLastId(String key,long lastNumber){
		Criteria c = super.getCriteria(IdGenerator.class);
		c.add(Restrictions.eq("idKey", key));
		IdGenerator ng = (IdGenerator) c.uniqueResult();
		if(ng==null){
			ng = new IdGenerator();
			ng.setIdKey(key);
		}
		ng.setLastId(lastNumber);
		super.save(ng);
	}
}
