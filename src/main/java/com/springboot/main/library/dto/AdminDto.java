package com.springboot.main.library.dto;

public class AdminDto {
	private String name;
	private String email;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	@Override
	public String toString() {
		return "AdminDto [name=" + name + ", email=" + email + "]";
	}
	
	

}
