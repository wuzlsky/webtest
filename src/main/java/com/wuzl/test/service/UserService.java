package com.wuzl.test.service;


import java.util.Map;

import com.wuzl.test.base.service.BaseService;

public interface UserService extends BaseService{

	public Map<String, Object> queryUserList(Map<String, Object> params) throws Exception;

}
