package com.LND.SportStore.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.LND.SportStore.model.Item;

public class ItemMapper implements RowMapper<Item>{
	
	@Override
	public Item mapRow(ResultSet rs, int rowNum) throws SQLException {

		Item item = new Item();

		item.setId(rs.getInt("product_code"));
		item.setTitle(rs.getString("title"));
		item.setImage(rs.getString("IMAGE"));
		item.setPrice(rs.getInt("PRICE"));
		item.setQuantity(rs.getInt("QUANTITY"));
		return item;
	}

}
