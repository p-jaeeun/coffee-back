package com.example.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.beans.AdminBean;

@RestController   //레스폰스바디를 한번에
public class RestExampleController {
	
	@GetMapping("/restExample")
	public AdminBean restExample() {
		AdminBean adminBean = new AdminBean();
		adminBean.setId("daniel");
		adminBean.setPw("1234");
		return adminBean;
	}
	
	@GetMapping("/restExample1")
	public AdminBean restExample1() {
		AdminBean adminBean = new AdminBean();
		adminBean.setId("jaewoo");
		adminBean.setPw("4321");
		return adminBean;
	}

}
