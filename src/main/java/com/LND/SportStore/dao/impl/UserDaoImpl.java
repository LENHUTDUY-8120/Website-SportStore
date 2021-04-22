package com.LND.SportStore.dao.impl;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementSetter;
import org.springframework.stereotype.Repository;

import com.LND.SportStore.dao.UserDao;
import com.LND.SportStore.model.AdminUser;
import com.LND.SportStore.model.UserDetail;

@Repository
public class UserDaoImpl implements UserDao{

	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	@Override
	public String getNameUser(String username) {
		
		String sql = "SELECT * FROM PERSONALDETAIL WHERE USERNAME = ?";
		
		List<UserDetail> user = jdbcTemplate.query(sql, new PreparedStatementSetter() {
			
			@Override
			public void setValues(PreparedStatement ps) throws SQLException {
				ps.setString(1, username);
			}
		}, new UserDetailMapper());
		
		return user.get(0).getFullname();
	}
	
	@Override
	public List<AdminUser> getListUser() {
		
		String sql = "select * FROM CUSTOMER inner join PERSONALDETAIL using(USERNAME)";
		
		return jdbcTemplate.query(sql, new AdminUserMapper());
		
	}

	@Override
	public UserDetail getUserDetail(String username) {
		
		String sql = "SELECT * FROM PERSONALDETAIL WHERE USERNAME = ?";
		
		List<UserDetail> user = jdbcTemplate.query(sql, new PreparedStatementSetter() {
			
			@Override
			public void setValues(PreparedStatement ps) throws SQLException {
				ps.setString(1, username);
			}
		}, new UserDetailMapper());
		
		return user.get(0);
	}
	
}
