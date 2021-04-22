package com.LND.SportStore.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.LND.SportStore.dao.ProductDao;
import com.LND.SportStore.model.Product;
import com.LND.SportStore.model.ProductDetail;

@Controller
public class ProductDetailController {

	
	@Autowired
	ProductDao productDao;
	
	@GetMapping("/productDT")
	public String ProductDetail(@RequestParam(name = "id") int id, Model model) {
		
		ProductDetail product1 = productDao.getProduct(id);
		List<Product> product2 = productDao.getBestSeller();
		List<Product> product3 = productDao.getRelateProducts1(product1.getCategori());
		List<Product> product4 = productDao.getRelateProducts2(product1.getCategori());
		Product list3 = productDao.getRandomProduct1();
		List<Product> list2 = productDao.getRandomProduct();
				
		model.addAttribute("product", product1);
		model.addAttribute("bestseller", product2);
		model.addAttribute("random",list2);
		model.addAttribute("random1",list3);
		model.addAttribute("relate",product3);
		model.addAttribute("relate1",product4);
		
		return "product_detail";
	}
	
}
