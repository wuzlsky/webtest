package com.wuzl.test.base.service.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.wuzl.test.base.dao.BaseDao;
import com.wuzl.test.base.service.BaseService;


@Transactional
@Service
public class BaseServiceImpl implements BaseService{
public Logger logger = Logger.getLogger(BaseServiceImpl.class);
	
	
	@Resource(name="baseDaoImpl")
	public BaseDao baseDao;
	
	public BaseDao getBaseDao() {
		return baseDao;
	}

	public void setLogger(Logger logger) {
		this.logger = logger;
	}
	
	public BaseServiceImpl() {

	}

	@Override
	public List<Map<String, Object>> queryForList(String statement,Map<String, Object> params) throws Exception {
		List<Map<String,Object>> list = baseDao.queryForList(statement, params);
		return list;
	}
	
	@Override
	public List<Map<String, Object>> queryForList(String statement,Map<String, Object> params,int page,int rows) throws Exception {
		List<Map<String,Object>> list = baseDao.queryForList(statement, params,page,rows);
		return list;
	}

	@Override
	public Object queryForObject(String statement, Map<String, Object> params) throws Exception {
		Object obj  = baseDao.queryForObject(statement, params);
		return obj;
	}

	@Override
	public void saveObject(String statement, Map<String, Object> params) throws Exception {
		baseDao.saveObject(statement, params);
	}

	@Override
	public void deleteObject(String statement, Map<String, Object> params) throws Exception {
		baseDao.deleteObject(statement, params);
		
	}
}
