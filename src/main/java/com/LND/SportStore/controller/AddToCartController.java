package com.LND.SportStore.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.LND.SportStore.dao.ProductDao;
import com.LND.SportStore.model.Item;
import com.LND.SportStore.model.Order;
import com.LND.SportStore.model.Product;

@Controller
public class AddToCartController {

	@Autowired
	ProductDao productDao;
	
	@GetMapping("/addCart")
	public String addProducttoCart(@RequestParam(name="id", required = true) int id, @RequestParam(name="quantity", required = false) int quantity,
			HttpSession session,
			HttpServletRequest req) {
		Product product = productDao.getProduct1(id);
		
		if(product!= null) {
			session = req.getSession();
			if(session.getAttribute("Order")==null) {
				Order order = new Order();
				List<Item> listItem = new ArrayList<Item>();
				Item item = new Item();
				item.setId(id);
				item.setQuantity(quantity);
				item.setTitle(product.getTitle());
				item.setImage(product.getImage());
				item.setPrice(product.getPrice());
				listItem.add(item);
				order.setItem(listItem);
				int total = 0;
				for (Item items : order.getItem()) {
					total += items.getPrice() * items.getQuantity();
				}
				order.setTotal(total);
				session.setAttribute("Order", order);
			} else {
				Order order = (Order) session.getAttribute("Order");
				List<Item> listItem = order.getItem();
				boolean check = false;
				for(Item item : listItem) {
					if(item.getId()==product.getId()) {
						item.setQuantity(item.getQuantity()+quantity);
						check = true;
					}
				}
				if(check==false) {
					Item item = new Item();
					item.setId(id);
					item.setQuantity(quantity);
					item.setTitle(product.getTitle());
					item.setImage(product.getImage());
					item.setPrice(product.getPrice());
					listItem.add(item);
				}
				int total = 0;
				for (Item items : order.getItem()) {
					total += items.getPrice() * items.getQuantity();
				}
				order.setTotal(total);
				session.setAttribute("Order", order);
			}
		}
		return "redirect:/cart";
	}
}
