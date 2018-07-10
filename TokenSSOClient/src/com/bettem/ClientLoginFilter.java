package com.bettem;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * 拦截子系统未登录用户请求，跳转至sso认证中心
 */
public class ClientLoginFilter implements Filter {
	
	private String token_soo_server_url = null;
	
	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		System.out.println("ClientLoginFilter.init");
		//初始化web.xml配置
		token_soo_server_url = filterConfig.getInitParameter("token_soo_server_url");
		System.out.println("token_soo_server_url"+token_soo_server_url);
	}
	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		System.out.println("ClientLoginFilter.doFilter");
		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse resp = (HttpServletResponse) response;
		HttpSession session = req.getSession();
		String token = req.getParameter("token");
		if(token!=null){//检测token
			System.out.println("ClientLoginFilter放行");
			chain.doFilter(request, response);
	        return;
		}else{//不检测token
			Object isLogin = session.getAttribute("isLogin");
			if(isLogin!= null&&(Boolean)isLogin){//放行，进行下一步token检测
				System.out.println("ClientLoginFilter已登录");
				chain.doFilter(request, response);
		        return;
			}else{
				System.out.println("ClientLoginFilter未登录");
				StringBuffer requestUrl = req.getRequestURL();
				token_soo_server_url = token_soo_server_url+"?clientUrl="+requestUrl.toString();
				resp.sendRedirect(token_soo_server_url);
			}
		}
	}
	@Override
	public void destroy() {
		System.out.println("ClientLoginFilter.destroy");
	}
}
