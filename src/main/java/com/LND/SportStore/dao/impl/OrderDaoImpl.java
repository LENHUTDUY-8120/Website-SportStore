package com.LND.SportStore.dao.impl;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementSetter;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcCall;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.LND.SportStore.dao.OrderDao;
import com.LND.SportStore.model.Item;
import com.LND.SportStore.model.Order;

@Repository
public class OrderDaoImpl implements OrderDao{

	@Autowired
	JdbcTemplate jdbcTemplate;
	
	@Override
	public List<Order> getListOrder() {
		
		String sql = "SELECT * FROM ORDERS order by id desc;";
		return jdbcTemplate.query(sql, new RowMapper<Order>() {

			@Override
			public Order mapRow(ResultSet rs, int rowNum) throws SQLException {
				
				Order order = new Order();
				
				order.setId(rs.getInt("id"));
				order.setFullname(rs.getString("FULL_NAME"));
				order.setAddress(rs.getString("ADDRESS"));
				order.setTel(rs.getString("TEL"));
				order.setEmail(rs.getString("EMAIL"));
				order.setTotal(rs.getInt("TOTAL"));
				order.setAdditional_info(rs.getString("ADDITONAL_INFO"));
				order.setProcess(rs.getInt("PROCESS_TT"));
				
				return order;
			}
			
		});
	}
	
	@Override
	public Order getOrder(int id) {
		
		String sql = "SELECT * FROM ORDERS WHERE ID=?";
		
		List<Order> order = jdbcTemplate.query(sql, new PreparedStatementSetter() {
			
			@Override
			public void setValues(PreparedStatement ps) throws SQLException {
				ps.setInt(1, id);
				
			}
		}, new RowMapper<Order>() {

			@Override
			public Order mapRow(ResultSet rs, int rowNum) throws SQLException {
				Order order = new Order();
				
				order.setId(rs.getInt("id"));
				order.setFullname(rs.getString("FULL_NAME"));
				order.setAddress(rs.getString("ADDRESS"));
				order.setTel(rs.getString("TEL"));
				order.setEmail(rs.getString("EMAIL"));
				order.setTotal(rs.getInt("TOTAL"));
				order.setAdditional_info(rs.getString("ADDITONAL_INFO"));
				
				return order;
			}
			
		});
		
		return order.get(0);
	}
	
	@Override
	public List<Item> getListItem(int id) {
			
		String sql = "SELECT  B.product_code,title,BRAND,P.PRICE,QUANTITY,IMAGE,process_tt FROM\r\n"
				+ "	((ORDERS A INNER JOIN ORDER_DETAIL B ON A.ID = B.ID_ORDER)\r\n"
				+ "	inner join Product P on B.product_code = P.product_code) where A.ID = ?;";
		
		return jdbcTemplate.query(sql, new PreparedStatementSetter() {
			
			@Override
			public void setValues(PreparedStatement ps) throws SQLException {
				ps.setInt(1, id);	
			}
		}, new ItemMapper());
	}

	@Override
	public void checkOrder(int id) {
		
		String sql = "UPDATE ORDERS SET PROCESS_TT=1 WHERE ID=?";
		
		jdbcTemplate.update(sql, id);
		
		return;
		
	}

	@Override
	@Transactional(rollbackFor = { SQLException.class })
	public void addOrder(Order order1, Order order2) {	
			
		SimpleJdbcCall simpleJdbcCall = new SimpleJdbcCall(jdbcTemplate.getDataSource()).withProcedureName("ADD_ORDER");
		
		Map<String,String> parameter = new HashMap<String,String>();
		parameter.put("FULL_NAME", order1.getFullname());
		parameter.put("ADDRESS", order1.getAddress());
		parameter.put("TEL", order1.getTel());
		parameter.put("EMAIL", order1.getEmail());
		parameter.put("TOTAL", String.valueOf(order2.getTotal()));
		parameter.put("ADDITONAL_INFO", order1.getAdditional_info());
		
		SqlParameterSource in = new MapSqlParameterSource().addValues(parameter);
		simpleJdbcCall.execute(in);
		
		String sql = "SELECT MAX(ID) FROM ORDERS";
		int id = jdbcTemplate.queryForObject(sql, Integer.class);
		
		List<Item> listItem = order2.getItem();
		for (Item item : listItem) {
			addOrderDetail(item, id);
		}
	}

	@Override
	public void addOrderDetail(Item item, int id) {
		
		SimpleJdbcCall simpleJdbcCall = new SimpleJdbcCall(jdbcTemplate.getDataSource()).withProcedureName("ADD_ORDER_DETAIL");
		
		Map<String, Integer> parameter = new HashMap<String, Integer>();
		parameter.put("ID_ORDER",id);
		parameter.put("PRODUCT_CODE", item.getId());
		parameter.put("QUANTITY", item.getQuantity());
		parameter.put("PRICE", item.getPrice());
		
		SqlParameterSource in = new MapSqlParameterSource().addValues(parameter);	
		simpleJdbcCall.execute(in);
	}

}
