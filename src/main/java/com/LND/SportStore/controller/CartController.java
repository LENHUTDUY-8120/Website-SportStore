package com.LND.SportStore.controller;

import java.security.Principal;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.LND.SportStore.dao.ProductDao;
import com.LND.SportStore.dao.UserDao;
import com.LND.SportStore.model.Item;
import com.LND.SportStore.model.Order;
import com.LND.SportStore.model.Product;


@Controller
public class CartController {

	@Autowired
	ProductDao productDao;
	
	@Autowired
	UserDao userDao;
	
	@GetMapping("/cart")
	public String cart(Model model, Principal principal, Authentication authentication, HttpSession session) {
		
		Product list3 = productDao.getRandomProduct1();
		List<Product> list2 = productDao.getRandomProduct();
		
		model.addAttribute("random",list2);
		model.addAttribute("random1",list3);
		if(principal != null) {
			model.addAttribute("acc",userDao.getNameUser(authentication.getName()));			
		}
		
		if (session.getAttribute("Order")!=null) {
			Order order = (Order) session.getAttribute("Order");
			if (order.getItem().isEmpty()) {
				model.addAttribute("mess", "Don't have any product");
			} else {
				List<Item> listItem = order.getItem();
				model.addAttribute("ListItem", listItem);
			}
		} else {
			model.addAttribute("mess", "Don't have any product");
		}
		
		return "cart";
	}
	
	
	@GetMapping("/deleteCart")
	public String delete(@RequestParam("id") int id , HttpSession session) {
		
		Order order = (Order) session.getAttribute("Order");
		List<Item> listItem = order.getItem();
		
		Item idItem = null;
		
		for(Item item : listItem) {
			if(item.getId()==id) {
				idItem = item;
				break;
			}
		}
		
		listItem.remove(idItem);
		
		session.setAttribute("Order", order);
		
		return "redirect:/cart";
	}
}
