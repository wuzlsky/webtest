package com.wuzl.test.base.dao;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

@Repository
public interface BaseDao {
	public List<Map<String,Object>> queryForList(String statement,Map<String,Object> params) throws Exception;
	
	public List<Map<String,Object>> queryForList(String statement,Map<String,Object> params,int page,int rows) throws Exception;

	public Object queryForObject(String statement, Map<String, Object> params) throws Exception;
	
	public void saveObject(String statement, Map<String, Object> params) throws Exception;
	
	public void deleteObject(String statement, Map<String, Object> params) throws Exception;
}
