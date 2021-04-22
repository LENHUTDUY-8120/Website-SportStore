package com.LND.SportStore.dao;

import java.util.List;

import com.LND.SportStore.model.Product;
import com.LND.SportStore.model.ProductDetail;

public interface ProductDao {
	
	public List<Product> getItem();
	
	public List<Product> get4Item();
	
	public List<Product> getAllProduct();
	
	public List<Product> searchProducts( String s);
	
	public ProductDetail getProduct(int i);
	
	public ProductDetail getProduct2(int i);
	
	public Product getProduct1(int id);
	
	public List<Product> getBestSeller();
	
	public List<Product> getRandomProduct();
	
	public Product getRandomProduct1();
	
	public List<Product> getProductByCato(String cat);
	
	public List<Product> getProductByBrand(String brand);
	
	public List<Product> getProductsBySize(String size);
	
	public List<Product> getFeatureProducts1();
	
	public List<Product> getFeatureProducts2();
	
	public List<Product> getRelateProducts1(String cat);
	
	public List<Product> getRelateProducts2(String cat);
	
	public void addProduct(String TITLE, String CATEGORIES, String BRAND, String PRICE ,String SIZE, String IMAGE, String DESCRIPTIONS);
	
	public void deleteProduct(int id);
	
	public void updateProduct(String id, String TITLE, String CATEGORIES, String BRAND, String PRICE ,String SIZE, String IMAGE, String DESCRIPTIONS);
	
}
