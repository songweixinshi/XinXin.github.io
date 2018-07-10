package com.bettem;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ServerLogin extends HttpServlet{
	private static final long serialVersionUID = 1L;
	
	
	public ServerLogin() {
		super();
	}
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doPost(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		//登录
		String userName = req.getParameter("userName");
		if(userName!=null&&!"".equals(userName)){
			//校验userName是否存在
			
			//如果存在
			req.getSession().setAttribute("isLogin", true);
			//创建令牌
			String token = TokenGenerateUtil.getToken();
			TokenManager.addToken(token,userName);
			String clientAddress = req.getParameter("clientUrl");//通过客户端传递来
			if(clientAddress!=null&&!"".equals(clientAddress)){
				String redirectAddress = clientAddress+"?+userName="+userName+"&token="+token;
				resp.sendRedirect(redirectAddress);
			}else{
				resp.sendRedirect(req.getContextPath() + "/serverLoginSuccess.jsp");
			}
		}else{
			resp.sendRedirect(req.getContextPath() + "/serverLogin.jsp");
		}
	}
}
