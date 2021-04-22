package com.LND.SportStore.controller;

import java.security.Principal;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.LND.SportStore.dao.OrderDao;
import com.LND.SportStore.dao.UserDao;
import com.LND.SportStore.model.Item;
import com.LND.SportStore.model.Order;
import com.LND.SportStore.model.UserDetail;

@Controller
public class CheckoutController {

	@Autowired
	UserDao userDao;
	
	@Autowired
	OrderDao orderDao;
	
	@GetMapping("/checkout")
	public String checkout( Model model, Authentication authentication, Principal principal, HttpSession session) {
		 
		if(principal != null) {
			
			UserDetail userDetail = userDao.getUserDetail(authentication.getName());
			model.addAttribute("user", userDetail);
		}
		Order order = (Order) session.getAttribute("Order");
		List<Item> listItem = order.getItem();
		model.addAttribute("ListItem", listItem);
		
		model.addAttribute("Order", new Order());
		
		return "checkout";
	}
	
	@PostMapping("/confirm")
	public String confirm(@ModelAttribute Order order1, HttpSession session) {
		
		Order order2 = (Order)session.getAttribute("Order");
		orderDao.addOrder(order1, order2);
		order2 = null;
		session.setAttribute("Order", order2);
		return "redirect:/product";
	}
	
}
