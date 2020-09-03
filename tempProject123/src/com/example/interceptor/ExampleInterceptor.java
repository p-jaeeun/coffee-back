package com.example.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.HandlerInterceptor;

import com.example.beans.AdminBean;

public class ExampleInterceptor implements HandlerInterceptor{

	AdminBean adminBean;
	
	
	
	public ExampleInterceptor(AdminBean adminBean) {
		this.adminBean = adminBean;
	}



	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		if(!adminBean.isLoggedIn()) {
			response.sendRedirect("/home");
			return false;
		}
		return true;
	}
}
