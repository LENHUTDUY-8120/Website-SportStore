package com.LND.SportStore.service;

import java.util.List;

import com.LND.SportStore.model.Product;

public interface ProductService {

	public List<Product> getItem();
	
	public List<Product> get4Item();
	
	public List<Product> getFeatureProducts1();
	
	public List<Product> getFeatureProducts2();
	
}
