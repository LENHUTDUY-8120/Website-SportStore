package com.LND.SportStore.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.LND.SportStore.model.AdminUser;

public class AdminUserMapper implements RowMapper<AdminUser>{

	@Override
	public AdminUser mapRow(ResultSet rs, int rowNum) throws SQLException {
		
		AdminUser user = new AdminUser(
								rs.getString("USERNAME"),
								rs.getString("PASSWORD"),
								rs.getString("ROLE"),
								rs.getInt("ENABLE"),
								rs.getString("FULL_NAME"),
								rs.getString("EMAIL"),
								rs.getString("TEL"));
		return user;
	}

}
