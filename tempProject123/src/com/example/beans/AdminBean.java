package com.example.beans;

import javax.validation.constraints.NotBlank;

public class AdminBean {

	@NotBlank(message = "아이디를 입력해주세요")
	private String id;
	@NotBlank(message = "패스워를 입력해주세요")
	private String pw;
	private boolean isLoggedIn;
	
	
	
	public boolean isLoggedIn() {
		return isLoggedIn;
	}
	public void setLoggedIn(boolean isLoggedIn) {
		this.isLoggedIn = isLoggedIn;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getPw() {
		return pw;
	}
	public void setPw(String pw) {
		this.pw = pw;
	}
	@Override
	public String toString() {
		return "AdminBean [id=" + id + ", pw=" + pw + "]";
	}
	
	
	
}
