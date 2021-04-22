package com.LND.SportStore.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.LND.SportStore.model.ProductDetail;

public class ProductMapper implements RowMapper<ProductDetail>{

	@Override
	public ProductDetail mapRow(ResultSet rs, int rowNum) throws SQLException {
		
		ProductDetail product = new ProductDetail();
		
		product.setId(rs.getString("PRODUCT_CODE"));
		product.setTitle(rs.getString("TITLE"));
		product.setBrand(rs.getString("BRAND"));
		product.setCategori(rs.getString("CATEGORIES"));
		product.setPrice(rs.getString("PRICE"));
		product.setRewardpoint(rs.getInt("REWARD_POINT"));
		product.setAvailability(rs.getString("AVAILABILITY"));
		product.setSize(rs.getString("SIZE"));
		product.setImage(rs.getString("IMAGE"));
		product.setDescriptions(rs.getString("DESCRIPTIONS"));
		return product;
	}

}
