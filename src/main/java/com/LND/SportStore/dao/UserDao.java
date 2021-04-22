package com.LND.SportStore.dao;

import java.util.List;

import com.LND.SportStore.model.AdminUser;
import com.LND.SportStore.model.UserDetail;

public interface UserDao {

	public String getNameUser( String username);
	
	public List<AdminUser> getListUser();
	
	public UserDetail getUserDetail( String username);
}
