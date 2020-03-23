package com.wuzl.test.base.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.wuzl.test.base.service.BaseService;


@Controller
public class BaseController {
	
	@Autowired(required = true)
	@Qualifier("baseServiceImpl")
	private BaseService baseService;
	
	public Logger logger;
	
	public void setLogger(Logger logger) {
		this.logger = logger;
	}

	
}
