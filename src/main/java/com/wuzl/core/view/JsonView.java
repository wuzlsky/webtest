package com.wuzl.core.view;

import java.io.PrintWriter;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

/**
 * 
 * @author liws
 * 
 */
public class JsonView implements org.springframework.web.servlet.View {
	
	public static final String DEFAULT_ENCODING = "UTF-8";
	// public static final String DEFAULT_CONTENT_TYPE = "application/json;charset=UTF-8";
	public static final String DEFAULT_CONTENT_TYPE = "text/html;charset=UTF-8";
	public Object jsonObject = null;
	
	public JsonView(Object jsonObject) {
		this.jsonObject = jsonObject;
	}
	
	public JsonView() {

	}
	
	public String getContentType() {
		return DEFAULT_CONTENT_TYPE;
	}
	
	@SuppressWarnings("unchecked")
	public void render(Map model, HttpServletRequest request, HttpServletResponse response) throws Exception {
		// 使用Gson将Java对象转换为JSON
		String jsonString = new Gson().toJson(this.jsonObject);
		// 设置输入属性,防止有乱码呀，出现其他不知的情况呀
		response.setCharacterEncoding(DEFAULT_ENCODING);
		response.setHeader("Pragma", "no-cache");
		response.setHeader("Expires", "0");
		response.setHeader("Cache-Control", "no-store");
		response.setContentType(this.getContentType());
		// 取得输出句柄
		PrintWriter out = response.getWriter();
		out.print(jsonString);
		out.flush();
		out.close();
	}
	
}
