package com.LND.SportStore.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.LND.SportStore.dao.ProductDao;
import com.LND.SportStore.model.Product;
import com.LND.SportStore.service.ProductService;

@Service
public class ProductServiceImpl implements ProductService{

	@Autowired
	private ProductDao productDao;
	
	@Override
	public List<Product> getItem() {
		
		return productDao.getItem();
	}

	@Override
	public List<Product> get4Item() {
		return productDao.get4Item();
	}

	@Override
	public List<Product> getFeatureProducts1() {
		return productDao.getFeatureProducts1();
	}

	@Override
	public List<Product> getFeatureProducts2() {
		return productDao.getFeatureProducts2();
	}


}
