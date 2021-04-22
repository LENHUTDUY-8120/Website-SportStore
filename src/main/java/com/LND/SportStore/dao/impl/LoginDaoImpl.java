package com.LND.SportStore.dao.impl;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementSetter;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcCall;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Repository;

import com.LND.SportStore.dao.LoginDao;
import com.LND.SportStore.model.User;

@Repository
public class LoginDaoImpl implements LoginDao{

	@Autowired
	private JdbcTemplate jdbcTemplate; 
	

	@Override
	public boolean register(String username, String password, String fullname, String email, String tel) {
		
		PasswordEncoder encoder = PasswordEncoderFactories.createDelegatingPasswordEncoder();
		
		if(isUse(username)) {
			SimpleJdbcCall simpleJdbcCall = new SimpleJdbcCall(jdbcTemplate.getDataSource()).withProcedureName("ADD_USER");
			Map<String, String> parameter = new HashMap<String, String>();
			parameter.put("USERNAME", username);
			parameter.put("PWD", encoder.encode(password));
			parameter.put("FULL_NAME", fullname);
			parameter.put("TEL", tel);
			parameter.put("EMAIL", email);
			
			SqlParameterSource in = new MapSqlParameterSource().addValues(parameter);
			
			simpleJdbcCall.execute(in);
			
			return true;
		}else {
			return false;
		}
	}


	@Override
	public boolean isUse(String username) {
		String sql = "Select username from Customer where username = ?";
		
		List<User> user = jdbcTemplate.query(sql, new PreparedStatementSetter() {
			
			@Override
			public void setValues(PreparedStatement ps) throws SQLException {
				ps.setString(1, username);
			}
		}, new UserMapper());
		
		if(user.isEmpty()) {
			return true;
		} else {
			return false;
		}
	}

	
}
