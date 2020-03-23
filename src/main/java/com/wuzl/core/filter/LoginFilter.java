package com.wuzl.core.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;


/**
 * <p>
 * <strong>RightsFilter</strong>访问控制
 * </p>
 * 
 * @author xxx
 * @version 1.0
 * @see javax.servlet.http.HttpServlet
 */
public class LoginFilter extends HttpServlet implements Filter {
	
	private static Logger logger = Logger.getLogger(LoginFilter.class);
	
	private static final long serialVersionUID = 7907397116914719491L;
	
	public LoginFilter() {

	}
	
	public void init(FilterConfig filterConfig) throws ServletException {
		
	}
	
	public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
		// HttpServletResponse继承了ServletResponse接口，并提供了与Http协议有关的方法，这些方法的主要功能是设置HTTP状态码和管理Cookie。
		HttpServletRequest request = (HttpServletRequest) servletRequest;
		HttpServletResponse response = (HttpServletResponse) servletResponse;
		HttpSession session = request.getSession();
		String url1 = request.getRequestURL().toString();
	//	logger.info("url 1: " + url1);
		String url2 = url1.split("\\?", -1)[0];
	//	logger.info("url 2: " + url2);
		
		// 截取斜岗前面的数据
		// String[] tokens = url.split("/", -1);
		// // 取得网址最后的字段
		// url = tokens[tokens.length - 1];
		// logger.info("url 2: " + url);
		// 特许不必通过验证
		
		// 或者写多一个特例，比如其他inito.jsp out.jsp这样jsp结尾的进行拦截(不写就让用户看到界面)
		
		// 必须通过验证（看session中是否有用户）
		
		String userid = String.valueOf(session.getAttribute("userid"));
	
		
		if (url2.endsWith("webtest") == true || url2.endsWith("webtest/") == true) {
			response.sendRedirect("login.html");
			return;
		} else if (url2.indexOf("test.shtml") != -1 || url2.indexOf("login.htm") != -1 || url2.endsWith("login.shtml") || url2.endsWith("logout.shtml") || url2.endsWith("regist.shtml") || url2.endsWith("verify.shtml") || url2.endsWith("validateVerify.shtml") || url2.endsWith("validateCode.shtml")) {
			filterChain.doFilter(request, response);
			return;
		} else if (url2.endsWith(".shtml") == true || url2.endsWith(".jsp") == true || url2.endsWith(".html") == true) {
			
			if (userid.equals("null")) { // 未登录
				
				if(url2.endsWith("login.shtml") || url2.endsWith("logout.shtml") || url2.endsWith("regist.shtml")){
					filterChain.doFilter(request, response);
				}else if(url2.endsWith("index.jsp")){
					response.sendRedirect(request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + request.getContextPath() + "/login.html");
				}else{
					response.getWriter().print("{loginout:true}");
				}
				
				return;
			}
		}
		filterChain.doFilter(request, response);
	}
	
	public void destroy() {}
	
}
