package com.cafe.admin.interceptor;

import com.cafe.admin.beans.AdminConnection;

import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class AdminInterceptor implements HandlerInterceptor{

    private AdminConnection adminConnection;

    public AdminInterceptor(AdminConnection adminConnection) {
        this.adminConnection = adminConnection;
    }


    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {


        if(!(adminConnection.isLoggedIn())){
            System.out.println("인터셉터 false");
            return false;
        }

        return true;
    }
}
