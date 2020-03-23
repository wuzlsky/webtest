package com.wuzl.test.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.wuzl.test.base.service.impl.BaseServiceImpl;
import com.wuzl.test.service.UserService;
import com.wuzl.util.AppUtil;

@Service
public class UserServiceImpl extends BaseServiceImpl implements UserService{

	@Override
	public Map<String, Object> queryUserList(Map<String, Object> params) throws Exception {
		List<Map<String,Object>> list = null;
		Integer total = 0;
		
		String s_start = AppUtil.getStrFromMap(params, "start");
		if(s_start.equals("")){
			list = super.queryForList("userMapper.queryUserList", params);
			total = (Integer) super.queryForObject("userMapper.queryUserCount", params);
		}else{
			int start = Integer.parseInt(AppUtil.getStrFromMap(params, "start"));
			int limit = Integer.parseInt(AppUtil.getStrFromMap(params, "limit"));
			int page = start/limit;
			int rows = limit;
			list = super.queryForList("userMapper.queryUserList", params,page,rows);
			total = (Integer) super.queryForObject("userMapper.queryUserCount", params);
		}
		
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("list", list);
		map.put("total", total);
		return map;
	}






}
