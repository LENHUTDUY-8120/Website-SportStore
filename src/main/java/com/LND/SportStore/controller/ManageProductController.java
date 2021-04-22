package com.LND.SportStore.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.support.PagedListHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

import com.LND.SportStore.dao.ProductDao;
import com.LND.SportStore.model.Product;
import com.LND.SportStore.model.ProductDetail;

@Controller
public class ManageProductController {

	@Autowired
	private ProductDao productDao;
	
	@GetMapping("/admin/manageproduct")
	public RedirectView addProduct(HttpServletRequest request, RedirectAttributes redirect, Model model,@RequestParam(name="page",defaultValue = "1") String page) {
		request.getSession().setAttribute("productlist", null);
		
		if(model.asMap().get("success") != null) {
			redirect.addFlashAttribute("success",model.asMap().get("success").toString());
		}
		return new RedirectView("manage-product/page/1");
	}
	
	@GetMapping("/admin/manage-product/page/{pageNumber}")
	public String showEmployeePage(HttpServletRequest request,
			@PathVariable int pageNumber, Model model) {
		
		PagedListHolder<?> pages = (PagedListHolder<?>) request.getSession().getAttribute("productlist");
		
		int pagesize = 9;
		
		List<Product> list = productDao.getAllProduct();
		
		if (pages == null) {
			pages = new PagedListHolder<>(list);
			pages.setPageSize(pagesize);
		} else {
			final int goToPage = pageNumber - 1;
			if (goToPage <= pages.getPageCount() && goToPage >= 0) {
				pages.setPage(goToPage);
			}
		}
		 
		request.getSession().setAttribute("productlist", pages);
		
		int current = pages.getPage() + 1;
		int begin = Math.max(1, current - list.size());
		int end = Math.min(begin + 5, pages.getPageCount());
//		int totalPageCount = pages.getPageCount();
		String baseUrl = "/admin/manage-product/page/";

		model.addAttribute("beginIndex", begin);
		model.addAttribute("endIndex", end);
		model.addAttribute("currentIndex", current);
		model.addAttribute("baseUrl", baseUrl);
		model.addAttribute("productlist", pages);
		
		int from = 1;
		for(int i=1;i<pageNumber;i++) {
				from += 9;
		}
		
		int to = from + 8;  
		if (from+9 > list.size()) {
			to = from + list.size()-from;
		}
		model.addAttribute("to", to);
		model.addAttribute("from", from);
		model.addAttribute("totalProduct", list.size());
		return "manageproduct";
	}

	
	@PostMapping("/admin/addproduct")
	public String addProduct(@ModelAttribute ProductDetail product) {
		
		productDao.addProduct(product.getTitle(),
							product.getCategori(),
							product.getBrand(),
							product.getPrice(),
							product.getSize(),
							product.getImage(),
							product.getDescriptions());
		return "redirect:/admin/manageproduct";
	}
	
	@GetMapping("/admin/deleteproduct")
	public String deleteProduct(@RequestParam("id") int id) {
		
		productDao.deleteProduct(id);
		
		return "redirect:/admin/manageproduct";
		
	}
	
	@GetMapping("/admin/edit")
	public String edit( @RequestParam("id") int id, Model model ) {
		
		ProductDetail productDetail = productDao.getProduct(id);
		
		model.addAttribute("product", productDetail);
		
		return "edit";
		
	}
	
	@PostMapping("/admin/editproduct")
	public String editproduct(@ModelAttribute ProductDetail product) {
		
		productDao.updateProduct(product.getId(),
								 product.getTitle(),
								 product.getCategori(),
								 product.getBrand(),
								 product.getPrice(),
								 product.getSize(),
								 product.getImage(),
								 product.getDescriptions());
		
		return "redirect:/admin/manageproduct";
		
	}
	
}
