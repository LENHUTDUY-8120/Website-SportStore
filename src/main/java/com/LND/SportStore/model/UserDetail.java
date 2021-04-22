package com.LND.SportStore.model;

import lombok.Data;

@Data
public class UserDetail {
	private String username;
	private String password;
	private String confirmPassword;
	private String fullname;
	private String email;
	private String tel;
	
}
