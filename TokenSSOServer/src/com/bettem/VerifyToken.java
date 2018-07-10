package com.bettem;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

public class VerifyToken extends HttpServlet{
	private static final long serialVersionUID = 1L;

	public VerifyToken() {
		super();
	}
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doPost(req, resp);
	}
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setCharacterEncoding("UTF-8");
		response.setContentType("application/json; charset=utf-8");
		
		//System.out.println(request.getParameter("token"));
        BufferedReader br = new BufferedReader(new InputStreamReader(request.getInputStream(),"utf-8"));  
        String line = null;  
        StringBuilder sb = new StringBuilder();  
        while ((line = br.readLine()) != null) {  
            sb.append(line);  
        }
        if(sb.length()!=0){
        	JSONObject json=JSONObject.fromObject(sb.toString()); 
            String token = json.getString("token");
            if(token!=null&&!"".equals(token)){
            	boolean result = TokenManager.validate(token);
                PrintWriter out = response.getWriter();
                String jsonStr = "{\"verifyResult\":"+result+"}";
                out.write(jsonStr);
            }else{
            	System.out.println("服务端接收到token为空");
            	return;
            }
        }else{
        	System.out.println("服务端接收到请求参数为空");
        	return;
        }
	}
}


