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

import com.LND.SportStore.dao.ProductDao;
import com.LND.SportStore.model.Product;
import com.LND.SportStore.model.ProductDetail;

@Repository
public class ProductDaoImpl implements ProductDao{

	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	@Override
	public List<Product> getItem() {
		
		String sql = "SELECT PRODUCT_CODE,TITLE,BRAND,PRICE,IMAGE FROM PRODUCT WHERE ENABLE=1 ORDER BY PRODUCT_CODE DESC limit 4";
		
		return jdbcTemplate.query(sql, new RowMapper<Product>() {

			@Override
			public Product mapRow(ResultSet rs, int rowNum) throws SQLException {
				
				Product product = new Product();
				product.setId(rs.getInt("product_code"));
				product.setTitle(rs.getString("title"));
				product.setBrand(rs.getString("brand"));
				product.setPrice(rs.getInt("price"));
				product.setImage(rs.getString("image"));
				
				return product;
			}
			
		});
	}

	@Override
	public List<Product> get4Item() {
		String sql = "SELECT PRODUCT_CODE,TITLE,BRAND,PRICE,IMAGE FROM PRODUCT WHERE ENABLE=1 ORDER BY PRODUCT_CODE DESC limit 4,4";
		
		return jdbcTemplate.query(sql, new RowMapper<Product>() {

			@Override
			public Product mapRow(ResultSet rs, int rowNum) throws SQLException {
				
				Product product = new Product();
				product.setId(rs.getInt("product_code"));
				product.setTitle(rs.getString("title"));
				product.setBrand(rs.getString("brand"));
				product.setPrice(rs.getInt("price"));
				product.setImage(rs.getString("image"));
				
				return product;
			}
			
		});
		
	}

	@Override
	public List<Product> getAllProduct() {
		String sql = "SELECT PRODUCT_CODE,TITLE,BRAND,PRICE,IMAGE FROM PRODUCT WHERE ENABLE=1 ORDER BY PRODUCT_CODE DESC";
		
		return jdbcTemplate.query(sql, new RowMapper<Product>() {

			@Override
			public Product mapRow(ResultSet rs, int rowNum) throws SQLException {
				
				Product product = new Product();
				product.setId(rs.getInt("product_code"));
				product.setTitle(rs.getString("title"));
				product.setBrand(rs.getString("brand"));
				product.setPrice(rs.getInt("price"));
				product.setImage(rs.getString("image"));
				
				return product;
			}			
		});
	}

	@Override
	public List<Product> getBestSeller() {
		
		String sql = "SELECT PRODUCT_CODE,TITLE,IMAGE FROM PRODUCT WHERE ENABLE=1 ORDER BY REWARD_POINT LIMIT 6;"; 
		
		return jdbcTemplate.query(sql, new RowMapper<Product>() {

			@Override
			public Product mapRow(ResultSet rs, int rowNum) throws SQLException {
				
				Product product = new Product();
				product.setId(rs.getInt("product_code"));
				product.setTitle(rs.getString("title"));
				product.setImage(rs.getString("image"));	
				return product;
			}
			
		});
	}

	@Override
	public List<Product> getRandomProduct() {
		
		String sql = "SELECT PRODUCT_CODE,TITLE,BRAND,PRICE,IMAGE FROM PRODUCT WHERE ENABLE=1 ORDER BY RAND() LIMIT 4";
		
		return jdbcTemplate.query(sql, new RowMapper<Product>() {

			@Override
			public Product mapRow(ResultSet rs, int rowNum) throws SQLException {
				
				Product product = new Product();
				product.setId(rs.getInt("product_code"));
				product.setTitle(rs.getString("title"));
				product.setBrand(rs.getString("brand"));
				product.setPrice(rs.getInt("price"));
				product.setImage(rs.getString("image"));
				
				return product;
			}		
		});
	}

	@Override
	public ProductDetail getProduct( int id ) {
		String sql = "SELECT * FROM PRODUCT WHERE PRODUCT_CODE = ? AND ENABLE=1";
		
		List<ProductDetail> product = jdbcTemplate.query(sql, new PreparedStatementSetter() {
			
			@Override
			public void setValues(PreparedStatement ps) throws SQLException {
				ps.setInt(1, id);
			}
		}, new ProductMapper());
		return product.get(0);
	}
	
	@Override
	public ProductDetail getProduct2( int id ) {
		String sql = "SELECT * FROM PRODUCT WHERE PRODUCT_CODE = ?";
		
		List<ProductDetail> product = jdbcTemplate.query(sql, new PreparedStatementSetter() {
			
			@Override
			public void setValues(PreparedStatement ps) throws SQLException {
				ps.setInt(1, id);
			}
		}, new ProductMapper());
		return product.get(0);
	}
	
	@Override
	public Product getProduct1( int id ) {
		String sql = "SELECT * FROM PRODUCT WHERE PRODUCT_CODE = ? AND ENABLE=1";
		
		List<Product> product = jdbcTemplate.query(sql, new PreparedStatementSetter() {
			
			@Override
			public void setValues(PreparedStatement ps) throws SQLException {
				ps.setInt(1, id);
			}
		}, new ProductMapper1());
		return product.get(0);
	}

	@Override
	public Product getRandomProduct1() {
		
			String sql = "SELECT PRODUCT_CODE,TITLE,BRAND,PRICE,IMAGE FROM PRODUCT WHERE ENABLE=1 ORDER BY RAND() LIMIT 1";
		
			List<Product> product =  jdbcTemplate.query(sql, new RowMapper<Product>() {

			@Override
			public Product mapRow(ResultSet rs, int rowNum) throws SQLException {
				
				Product product = new Product();
				product.setId(rs.getInt("product_code"));
				product.setTitle(rs.getString("title"));
				product.setBrand(rs.getString("brand"));
				product.setPrice(rs.getInt("price"));
				product.setImage(rs.getString("image"));
				
				return product;
			}		
		});
		return product.get(0);
	}

	@Override
	public List<Product> getProductByBrand(String brand) {
		String sql = "SELECT PRODUCT_CODE,TITLE,BRAND,PRICE,IMAGE FROM PRODUCT WHERE BRAND = ? AND ENABLE=1";
		
		List<Product> product = jdbcTemplate.query(sql, new PreparedStatementSetter() {
			
			@Override
			public void setValues(PreparedStatement ps) throws SQLException {
				ps.setString(1, brand);
			}
		}, new ProductMapper1());
		return product;
	}

	
	@Override
	public List<Product> getProductsBySize(String size) {
		String sql = "SELECT PRODUCT_CODE,TITLE,BRAND,PRICE,IMAGE FROM PRODUCT WHERE SIZE = ? AND ENABLE=1";
		
		List<Product> product = jdbcTemplate.query(sql, new PreparedStatementSetter() {
			
			@Override
			public void setValues(PreparedStatement ps) throws SQLException {
				ps.setString(1, size);
			}
		}, new ProductMapper1());
		return product;
	}

	@Override
	public List<Product> getProductByCato(String categori) {
		String sql = "SELECT PRODUCT_CODE,TITLE,BRAND,PRICE,IMAGE FROM PRODUCT WHERE CATEGORIES = ? AND ENABLE=1";
		
		List<Product> product = jdbcTemplate.query(sql, new PreparedStatementSetter() {
			
			@Override
			public void setValues(PreparedStatement ps) throws SQLException {
				ps.setString(1, categori);
			}
		}, new ProductMapper1());
		return product;
	}

	@Override
	public List<Product> getFeatureProducts1() {
		String sql = "SELECT PRODUCT_CODE,TITLE,BRAND,PRICE,IMAGE FROM PRODUCT WHERE ENABLE=1 ORDER BY REWARD_POINT LIMIT 4"; 
		
		return jdbcTemplate.query(sql, new RowMapper<Product>() {

			@Override
			public Product mapRow(ResultSet rs, int rowNum) throws SQLException {
				
				Product product = new Product();
				product.setId(rs.getInt("product_code"));
				product.setTitle(rs.getString("title"));
				product.setBrand(rs.getString("brand"));
				product.setPrice(rs.getInt("price"));
				product.setImage(rs.getString("image"));	
				return product;
			}
			
		});
	}

	@Override
	public List<Product> getFeatureProducts2() {
		String sql = "SELECT PRODUCT_CODE,TITLE,BRAND,PRICE,IMAGE FROM PRODUCT WHERE ENABLE=1 ORDER BY REWARD_POINT LIMIT 4,4"; 
		
		return jdbcTemplate.query(sql, new RowMapper<Product>() {

			@Override
			public Product mapRow(ResultSet rs, int rowNum) throws SQLException {
				
				Product product = new Product();
				product.setId(rs.getInt("product_code"));
				product.setTitle(rs.getString("title"));
				product.setBrand(rs.getString("brand"));
				product.setPrice(rs.getInt("price"));
				product.setImage(rs.getString("image"));	
				return product;
			}
			
		});
	}

	@Override
	public List<Product> getRelateProducts1(String cat) {
		String sql = "SELECT PRODUCT_CODE,TITLE,BRAND,PRICE,IMAGE FROM PRODUCT WHERE CATEGORIES = ? AND ENABLE=1 limit 3";
		
		List<Product> product = jdbcTemplate.query(sql, new PreparedStatementSetter() {
			
			@Override
			public void setValues(PreparedStatement ps) throws SQLException {
				ps.setString(1, cat);
			}
		}, new ProductMapper1());
		return product;
	}

	@Override
	public List<Product> getRelateProducts2(String cat) {
		String sql = "SELECT PRODUCT_CODE,TITLE,BRAND,PRICE,IMAGE FROM PRODUCT WHERE CATEGORIES = ? AND ENABLE=1 limit 3,3";
		
		List<Product> product = jdbcTemplate.query(sql, new PreparedStatementSetter() {
			
			@Override
			public void setValues(PreparedStatement ps) throws SQLException {
				ps.setString(1, cat);
			}
		}, new ProductMapper1());
		return product;
	}

	@Override
	public void addProduct(String TITLE, String CATEGORIES, String BRAND, String PRICE, String SIZE, String IMAGE,
			String DESCRIPTIONS) {
		
		SimpleJdbcCall simpleJdbcCall = new SimpleJdbcCall(jdbcTemplate.getDataSource()).withProcedureName("ADD_PRODUCT");
		
		Map<String, String> parameter = new HashMap<String, String>();
		
		parameter.put("TITLE", TITLE);
		parameter.put("CATEGORIES", CATEGORIES);
		parameter.put("BRAND", BRAND);
		parameter.put("PRICE", PRICE);
		parameter.put("SIZE", SIZE);
		parameter.put("IMAGE", IMAGE);
		parameter.put("DESCRIPTIONS", DESCRIPTIONS);
		
		SqlParameterSource in = new MapSqlParameterSource().addValues(parameter);
		
		simpleJdbcCall.execute(in);
		
	}

	@Override
	public void deleteProduct(int id) {
		
		String sql = "UPDATE PRODUCT SET ENABLE=0 WHERE PRODUCT_CODE=?";
		
		jdbcTemplate.update(sql, id);
			
	}

	@Override
	public void updateProduct(String id,String TITLE, String CATEGORIES, String BRAND, String PRICE, String SIZE, String IMAGE,
			String DESCRIPTIONS) {
		SimpleJdbcCall simpleJdbcCall = new SimpleJdbcCall(jdbcTemplate.getDataSource()).withProcedureName("UPDATE_PRODUCT");
		
		Map<String, String> parameter = new HashMap<String, String>();
		
		parameter.put("ID", id);
		parameter.put("STITLE", TITLE);
		parameter.put("SCATEGORIES", CATEGORIES);
		parameter.put("SBRAND", BRAND);
		parameter.put("SPRICE", PRICE);
		parameter.put("SSIZE", SIZE);
		parameter.put("SIMAGE", IMAGE);
		parameter.put("SDESCRIPTIONS", DESCRIPTIONS);
		
		SqlParameterSource in = new MapSqlParameterSource().addValues(parameter);
		
		simpleJdbcCall.execute(in);
		
	}

	@Override
	public List<Product> searchProducts(String s) {
		String sql = "SELECT * FROM PRODUCT WHERE\r\n"
				+ "MATCH(TITLE,CATEGORIES,BRAND,DESCRIPTIONS)\r\n"
				+ "AGAINST (? WITH QUERY EXPANSION)"
				+ "AND ENABLE = 1";
		
		List<Product> product = jdbcTemplate.query(sql, new PreparedStatementSetter() {
			
			@Override
			public void setValues(PreparedStatement ps) throws SQLException {
				ps.setString(1, s);
			}
		}, new ProductMapper1());
		return product;
	}
}
