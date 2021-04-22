package com.LND.SportStore.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.LND.SportStore.dao.OrderDao;
import com.LND.SportStore.dao.ProductDao;
import com.LND.SportStore.dao.UserDao;
import com.LND.SportStore.model.AdminUser;
import com.LND.SportStore.model.Item;
import com.LND.SportStore.model.Order;
import com.LND.SportStore.model.ProductDetail;

@Controller
public class AdminController {
	
	@Autowired
	UserDao userDao;
	
	@Autowired
	ProductDao productDao;
	
	@Autowired
	OrderDao orderDao;
	
	@GetMapping("/admin")
	public String admin() {
		return "admin";
	}
	

	@GetMapping("/admin/list_user")
	public String listuser(Model model) {
		
		List<AdminUser> list = userDao.getListUser();
		
		model.addAttribute("listUser",list);
		
		System.out.println(list);
		
		return "listuser";
	}
	
	@GetMapping("/admin/list_order")
	public String listOder(Model model) {
		
		List<Order> listOrder = orderDao.getListOrder();
		
		model.addAttribute("listorder", listOrder);
		
		return "listOrder";
	}
	
	@GetMapping("/admin/list_order_detail")
	public String listOrderDetail(Model model, @RequestParam("id") int id) {
			Order order = orderDao.getOrder(id);
			List<Item> listItem = orderDao.getListItem(id);
			
			for (Item item : listItem) {				
				ProductDetail productDetail = productDao.getProduct2(item.getId());
				item.setProduct(productDetail);
			}
			
			model.addAttribute("order", order);
			model.addAttribute("listItem", listItem);
		return "orderDetail";
	}
	
	@GetMapping("/admin/checkOrder")
	public String checkOrder(@RequestParam(name = "id") int id) {
			orderDao.checkOrder(id);		
		return "redirect:/admin/list_order";
	}
}
