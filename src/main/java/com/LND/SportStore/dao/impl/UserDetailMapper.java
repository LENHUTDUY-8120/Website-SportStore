package com.LND.SportStore.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.LND.SportStore.model.UserDetail;

public class UserDetailMapper implements RowMapper<UserDetail>{

	@Override
	public UserDetail mapRow(ResultSet rs, int rowNum) throws SQLException {
		
		UserDetail user = new UserDetail();
		
		user.setUsername(rs.getString("USERNAME"));
		user.setFullname(rs.getString("FULL_NAME"));
		user.setEmail(rs.getString("EMAIL"));
		user.setTel(rs.getString("TEL"));
		
		return user;
	}
	
	
	
}
