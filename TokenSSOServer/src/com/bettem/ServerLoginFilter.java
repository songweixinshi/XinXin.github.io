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

public class ServerLoginFilter implements Filter{

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		System.out.println("ServerLoginFilter：init");
	}
	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		System.out.println("ServerLoginFilter：doFilter");
		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse resp = (HttpServletResponse) response;
		
		String clientUrl = req.getParameter("clientUrl");
		
        HttpSession session = req.getSession();
        String currentURL = req.getRequestURI();
        String targetURL = currentURL.substring(currentURL.indexOf("/",1),currentURL.length());
        Object isLogin = session.getAttribute("isLogin");
        if(targetURL.indexOf("login")>-1||targetURL.indexOf("verifyToken")>-1){
        	if(isLogin != null&&(Boolean)isLogin){
        		req.getRequestDispatcher("/serverLoginSuccess.jsp").forward(req, resp);
        		return;
        	}else{
        		System.out.println("登录和token检测ServerLoginFilter放行");
            	chain.doFilter(request, response);
            	return;
        	}
        }
        
        if(isLogin != null&&(Boolean)isLogin) {
        	System.out.println("ServerLoginFilter已登录，放行");
        	chain.doFilter(request, response);
        	return;
        }else{
        	System.out.println("ServerLoginFilter未登录，进入serverLogin.jsp");
        	req.setAttribute("clientUrl",clientUrl);
    		req.getRequestDispatcher("/serverLogin.jsp").forward(req, resp); 
        }
	}
	@Override
	public void destroy() {
		System.out.println("ServerLoginFilter：destroy");
	}
}
