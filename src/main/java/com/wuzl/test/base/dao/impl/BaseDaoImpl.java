package com.wuzl.test.base.dao.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;


import org.apache.ibatis.session.RowBounds;
import org.apache.log4j.Logger;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Repository;

import com.wuzl.test.base.dao.BaseDao;


@Repository
public class BaseDaoImpl implements BaseDao{
public Logger logger = Logger.getLogger(BaseDaoImpl.class);

	public void setLogger(Logger logger) {
		this.logger = logger;
	}

	@Resource(name="sqlSession")
    public SqlSessionTemplate sqlSession; 
	 
	

	@Override
	public List<Map<String, Object>> queryForList(String statement,Map<String, Object> params) throws Exception {
		return  sqlSession.selectList(statement, params);
	}
	
	@Override
	public List<Map<String, Object>> queryForList(String statement,Map<String, Object> params,int page,int rows) throws Exception {
		RowBounds rowBounds = new RowBounds((page-1)*rows,rows);
	//	params.put("limitCondition", "limit "+(page-1)*rows+","+rows);
		return  sqlSession.selectList(statement, params,rowBounds);
	}

	@Override
	public Object queryForObject(String statement, Map<String, Object> params) throws Exception {
		return sqlSession.selectOne(statement, params);
	}

	@Override
	public void saveObject(String statement, Map<String, Object> params) throws Exception {
		sqlSession.insert(statement, params);
		
	}

	@Override
	public void deleteObject(String statement, Map<String, Object> params) throws Exception {
		sqlSession.delete(statement, params);
		
	}
	

}
