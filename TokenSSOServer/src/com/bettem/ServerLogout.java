package com.bettem;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class ServerLogout extends HttpServlet{
	private static final long serialVersionUID = 1L;
	public ServerLogout() {
		super();
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doPost(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		HttpSession session = req.getSession();
	    if (session != null) {
	        session.invalidate();//触发LogoutListener
	    }
	    resp.sendRedirect("http://192.168.2.52/TokenSSOServer/login");
	}
}
