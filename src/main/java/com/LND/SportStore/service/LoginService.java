package com.LND.SportStore.service;

public interface LoginService {

//	public List<User> login(String username, String password);
	
	public boolean addUser(String username, String password,
			String fullname, String email, String tel);
}
