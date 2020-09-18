package com.example.validator;

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.example.beans.AdminBean;

public class ExampleValidator implements Validator{

	@Override
	public boolean supports(Class<?> clazz) {
	
		return AdminBean.class.isAssignableFrom(clazz);
	}

	@Override
	public void validate(Object o, Errors errors) {
		AdminBean adminBean = (AdminBean) o;
		String id = adminBean.getId();
		String pw = adminBean.getPw();
		if(!(id.equals("admin")&&pw.equals("1234"))){
			errors.reject("not same", "아이디와 비밀번호가 잘못 되었습니다" );
		}
		
	}

}
