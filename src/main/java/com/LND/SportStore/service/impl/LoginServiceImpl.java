package com.LND.SportStore.service.impl;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.LND.SportStore.dao.LoginDao;
import com.LND.SportStore.service.LoginService;

@Service
public class LoginServiceImpl implements LoginService{

	@Autowired
	private LoginDao loginDao;
	
//	@Override
//	public List<User> login(String username, String password) {
//		
//		return loginDao.Login(username, password);
//	}

	@Override
	public boolean addUser(String username, String password, String fullname, String email, String tel) {
		return loginDao.register(username, password, fullname, email, tel);
	}

}
