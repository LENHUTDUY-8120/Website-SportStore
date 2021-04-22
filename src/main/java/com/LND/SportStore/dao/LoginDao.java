package com.LND.SportStore.dao;

public interface LoginDao {

	
	public boolean isUse(String username);
	
	public boolean register(String username, String password,
							String fullname, String email, String tel);
}
