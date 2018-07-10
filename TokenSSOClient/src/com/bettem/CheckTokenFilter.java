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

import org.apache.http.Consts;
import org.apache.http.HttpStatus;
import org.apache.http.client.HttpResponseException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import net.sf.json.JSONObject;

public class CheckTokenFilter implements Filter{

	private String token_soo_server_url = null;
	
	private String token_soo_server_verify_url = null;
	@Override
	public void destroy() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse resp = (HttpServletResponse) response;
		HttpSession session = req.getSession();
		String token = req.getParameter("token");
		if (token != null) {
			// 去sso认证中心校验token
			CloseableHttpClient httpClient = HttpClients.createDefault();
			HttpPost httpPost = new HttpPost(token_soo_server_verify_url);
			String requestStr = "{\"token\":\""+token+"\"}";
			httpPost.setEntity(new StringEntity(requestStr, ContentType.create("application/json", Consts.UTF_8)));
			CloseableHttpResponse httpResponse = httpClient.execute(httpPost);
			
			int code = httpResponse.getStatusLine().getStatusCode();  
		    if (code != HttpStatus.SC_OK) { 
		        throw new HttpResponseException(code, "响应异常");  
		    }  
		    
			String result=EntityUtils.toString(httpResponse.getEntity(),"UTF-8");
			System.out.println(result);
			
			JSONObject jsonResult = JSONObject.fromObject(result);
			boolean verifyResult = jsonResult.getBoolean("verifyResult");
//			sso-client还需将当前会话id与令牌绑定，表示这个会话的登录状态与令牌相关，此关系可以用java的hashmap保存，
//			保存的数据用来处理sso认证中心发来的注销请求
			httpResponse.close();
			if (!verifyResult) {//如果校验不通过，返回服务器登录页
				System.out.println("CheckTokenFilter发送服务端verifyResult检测无效");
				System.out.println("重定向到serverLogin");
				resp.sendRedirect(token_soo_server_url);
		        return;
		    }else{
		    	System.out.println("CheckTokenFilter发送服务端verifyResult检测有效");
		    	System.out.println("CheckTokenFilter放行");
		    	session.setAttribute("isLogin",true);
			    chain.doFilter(request, response);
		    }
		}else{
			Object isLogin = session.getAttribute("isLogin");
			if(isLogin!= null&&(Boolean)isLogin){
				System.out.println("CheckTokenFilter放行");
				chain.doFilter(request, response);
		        return;
			}else{
				System.out.println("未登录，重定向到serverLogin");
				resp.sendRedirect(token_soo_server_url);//token是空，报错网络异常或返回服务器登录页
			}
		}
	}

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		// TODO Auto-generated method stub
		token_soo_server_url = filterConfig.getInitParameter("token_soo_server_url");
		token_soo_server_verify_url = filterConfig.getInitParameter("token_soo_server_verify_url");
		
	}

}
