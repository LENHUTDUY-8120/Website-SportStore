package com.LND.SportStore.controller;


import java.security.Principal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.LND.SportStore.dao.UserDao;
import com.LND.SportStore.model.Product;
import com.LND.SportStore.service.ProductService;

@Controller
public class HomeController {

	@Autowired
	private ProductService productService;
	
	@Autowired
	private UserDao userDao;
	
	@GetMapping("/")
	public String home(Model model, Principal principal,Authentication authentication) {
		
		List<Product> product1 = productService.getItem();
		List<Product> product2 = productService.get4Item();
		List<Product> product3 = productService.getFeatureProducts1();
		List<Product> product4 = productService.getFeatureProducts2();
		
		
		model.addAttribute("product1",product1);
		model.addAttribute("product2",product2);
		model.addAttribute("product3",product3);
		model.addAttribute("product4",product4);
		if(principal != null) {
			model.addAttribute("acc",userDao.getNameUser(authentication.getName()));			
		}
		return "home";
	}
	
}
