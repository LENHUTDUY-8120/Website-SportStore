package com.LND.SportStore.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.LND.SportStore.model.Product;

public class ProductMapper1 implements RowMapper<Product>{

	@Override
	public Product mapRow(ResultSet rs, int rowNum) throws SQLException {
		Product product = new Product();
		
		product.setId(rs.getInt("PRODUCT_CODE"));
		product.setTitle(rs.getString("TITLE"));
		product.setBrand(rs.getString("BRAND"));
		product.setPrice(rs.getInt("PRICE"));
		product.setImage(rs.getString("IMAGE"));
		return product;
	}

}
