package com.wuzl.test.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.wuzl.core.view.JsonView;
import com.wuzl.test.base.controller.BaseController;
import com.wuzl.test.model.User;
import com.wuzl.test.service.UserService;
import com.wuzl.util.AppUtil;

@Controller
public class UserController extends BaseController{
	
	public UserController() {
		super.setLogger(Logger.getLogger(UserController.class));
	}

	@Autowired
	private UserService serviceClient;
	

	@RequestMapping(value = "/test.shtml")
	public ModelAndView test(HttpServletRequest request, HttpServletResponse response) throws Exception{
		Map<String, Object> resultMap = new HashMap<String, Object>();
		List<Map<String, Object>> list = null;
		String msg = "";
		try {
			Map<String, Object> params = AppUtil.requestToObjectMap(request);
			String username = new String (AppUtil.getStrFromMap(params, "username").getBytes("ISO-8859-1"),"UTF-8");
			params.put("username", username);
			Map<String,Object> userMap = serviceClient.queryUserList(params);
			list = (List<Map<String, Object>>) userMap.get("list");

			resultMap.put("list", list);
			resultMap.put("total", userMap.get("total"));
			msg = "查询用户列表成功!";
			logger.info(msg);
		} catch (Exception e) {
			e.printStackTrace();
			String errorMsg = e.getMessage();
			resultMap.put("success", false);
			msg = "查询用户列表失败!";
			logger.error(msg+errorMsg);
		}
		
		return new ModelAndView(new JsonView(resultMap));
	}
	
	
	@RequestMapping(value = "/user/queryUserList.shtml")
	public ModelAndView queryUserList(HttpServletRequest request, HttpServletResponse response) throws Exception{
		Map<String, Object> resultMap = new HashMap<String, Object>();
		List<Map<String, Object>> list = null;
		String msg = "";
		try {
			Map<String, Object> params = AppUtil.requestToObjectMap(request);
			String username = new String (AppUtil.getStrFromMap(params, "username").getBytes("ISO-8859-1"),"UTF-8");
			params.put("username", username);
			Map<String,Object> userMap = serviceClient.queryUserList(params);
			list = (List<Map<String, Object>>) userMap.get("list");
			for(Map<String,Object> map:list){
				map.put("regist_time", AppUtil.getStrFromMap(map, "regist_time"));
				map.put("login_time", AppUtil.getStrFromMap(map, "login_time"));
				map.put("last_login_time", AppUtil.getStrFromMap(map, "last_login_time"));
			}
			resultMap.put("list", list);
			resultMap.put("total", userMap.get("total"));
			msg = "查询用户列表成功!";
			logger.info(msg);
		} catch (Exception e) {
			e.printStackTrace();
			String errorMsg = e.getMessage();
			resultMap.put("success", false);
			msg = "查询用户列表失败!";
			logger.error(msg+errorMsg);
		}
		
		return new ModelAndView(new JsonView(resultMap));
	}
	
	
	@RequestMapping(value = "/user/queryUser.shtml")
	public ModelAndView queryUser(HttpServletRequest request, HttpServletResponse response) throws Exception{
		Map<String, Object> resultMap = new HashMap<String, Object>();
		List<Map<String, Object>> list = null;
		try {
			Map<String, Object> params = AppUtil.requestToObjectMap(request);
			User user = (User) serviceClient.queryForObject("userDao.queryUserByUserid", params);
			resultMap.put("user", user);
			logger.info("查询用户成功!");
		} catch (Exception e) {
			e.printStackTrace();
			String errorMsg = e.getMessage();
			resultMap.put("success", false);
			logger.error("查询用户失败!"+errorMsg);
		}
		
		return new ModelAndView(new JsonView(resultMap));
	}

	
	

	
	
	@RequestMapping(value = "/user/addUser.shtml")
	public ModelAndView addUser(HttpServletRequest request, HttpServletResponse response) throws Exception{
		Map<String, Object> resultMap = new HashMap<String, Object>();
		String msg = "";
		List<Map<String, Object>> list = null;
		try {
			Map<String, Object> params = AppUtil.requestToObjectMap(request);
			params.put("regist_time", AppUtil.getNormalStrDate(new Date()));
			
			serviceClient.saveObject("userDao.addUser", params);
			msg = "增加用户成功!";
			logger.info(msg);
		} catch (Exception e) {
			e.printStackTrace();
			msg = "增加用户失败!";
			String errorMsg = e.getMessage();
			if(errorMsg.indexOf("Duplicate entry") != -1){
				msg = "ID重复！";
			}
			resultMap.put("success", false);
			logger.error(msg+errorMsg);
		}
		resultMap.put("msg",msg);
		return new ModelAndView(new JsonView(resultMap));
	}
	
	
	@RequestMapping(value = "/user/editUser.shtml")
	public ModelAndView editUser(HttpServletRequest request, HttpServletResponse response) throws Exception{
		Map<String, Object> resultMap = new HashMap<String, Object>();
		String msg = "";
		List<Map<String, Object>> list = null;
		try {
			Map<String, Object> params = AppUtil.requestToObjectMap(request);
			serviceClient.saveObject("userDao.editUser", params);
			msg = "修改用户成功!";
			logger.info(msg);
		} catch (Exception e) {
			e.printStackTrace();
			msg = "修改用户失败!";
			String errorMsg = e.getMessage();
			if(errorMsg.indexOf("Duplicate entry") != -1){
				msg = "ID重复！";
			}
			resultMap.put("success", false);
			logger.error(msg+errorMsg);
		}
		resultMap.put("msg",msg);
		return new ModelAndView(new JsonView(resultMap));
	}
	
	
	@RequestMapping(value = "/user/deleteUser.shtml")
	public ModelAndView deleteUser(HttpServletRequest request, HttpServletResponse response) throws Exception{
		Map<String, Object> resultMap = new HashMap<String, Object>();
		String msg = "";
		List<Map<String, Object>> list = null;
		try {
			Map<String, Object> params = AppUtil.requestToObjectMap(request);
			String ids = AppUtil.getStrFromMap(params, "ids");
			params.put("idArr", ids.split(","));
			serviceClient.deleteObject("userDao.deleteUser", params);
			serviceClient.deleteObject("userDao.deleteUr", params);
			msg = "删除用户成功!";

			logger.info(msg);
		} catch (Exception e) {
			e.printStackTrace();
			msg = "删除用户失败!";
			String errorMsg = e.getMessage();
			resultMap.put("success", false);
			logger.error(msg+errorMsg);
		}
		resultMap.put("msg",msg);
		return new ModelAndView(new JsonView(resultMap));
	}
	

}
