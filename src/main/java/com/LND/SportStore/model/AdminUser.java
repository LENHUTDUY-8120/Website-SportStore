package com.LND.SportStore.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class AdminUser {

	private String username;
	private String password;
	private String role;
	private int enable;
	private String fullname;
	private String email;
	private String tel;
	
}
