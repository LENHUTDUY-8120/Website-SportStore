package com.LND.SportStore.controller;

import java.security.Principal;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.support.PagedListHolder;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

import com.LND.SportStore.dao.ProductDao;
import com.LND.SportStore.dao.UserDao;
import com.LND.SportStore.model.Product;

@Controller
public class ProductController {

	@Autowired
	private UserDao userDao;
	
	@Autowired
	private ProductDao productDao;
	
	
	@GetMapping("/product")
	public RedirectView product( Model model,HttpServletRequest request,
			RedirectAttributes redirect,@RequestParam(name = "by",required = false) String by,@RequestParam(name = "name",required = false) String name
			) {
		
		if(request.isUserInRole("ROLE_ADMIN")) {
			return new RedirectView("admin");
		} else {
			request.getSession().setAttribute("productlist", null);
			
			if(model.asMap().get("success") != null) {
				redirect.addFlashAttribute("success",model.asMap().get("success").toString());
			}
			
			redirect.addAttribute("by", by);
			redirect.addAttribute("name", name);
			return new RedirectView("products/page/1");
		}
	}
	
	@GetMapping("/products/page/{pageNumber}")
	public String showEmployeePage(HttpServletRequest request,Principal principal, Authentication authentication,
			@PathVariable int pageNumber, Model model, @RequestParam(name = "by", required = false ) String by,@RequestParam(name = "name",required = false) String name) {
		PagedListHolder<?> pages = (PagedListHolder<?>) request.getSession().getAttribute("productlist");
		
		int pagesize = 9;
		
		List<Product> list = null;
		
		Product list3 = productDao.getRandomProduct1();
		List<Product> list2 = productDao.getRandomProduct();
		List<Product> list1 = productDao.getBestSeller();
		
		if (by!=null) {
			if ( by.equals("categori")) {
				list = productDao.getProductByCato(name);
			}else if (by.equals("brand")){
				list = productDao.getProductByBrand(name);
			}
			else {
				list = productDao.getProductsBySize(name);
			}
		}else {
			list = productDao.getAllProduct();
		}
		
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
		int totalPageCount = pages.getPageCount();
		String baseUrl = "/products/page/";

		model.addAttribute("beginIndex", begin);
		model.addAttribute("endIndex", end);
		model.addAttribute("currentIndex", current);
		model.addAttribute("totalPageCount", totalPageCount);
		model.addAttribute("baseUrl", baseUrl);
		model.addAttribute("productlist", pages);
		model.addAttribute("bestseller",list1);
		model.addAttribute("random",list2);
		model.addAttribute("random1",list3);
		if(principal != null) {
			model.addAttribute("acc",userDao.getNameUser(authentication.getName()));			
		}

		return "products";
	}
	
	@GetMapping("/products/search")
	public String a(Model model, HttpSession session,
			@RequestParam(name="search",defaultValue = "!@#$%^&*", required = false) String s) {
		
		session.setAttribute("search", s);
		
		return "redirect:/products/search/1";
	}
	
	@GetMapping("/products/search/{pageNumber}")	
	public String search(Model model, HttpServletRequest request, Principal principal, Authentication authentication,	
			@PathVariable int pageNumber) {	
		
		String s = request.getSession().getAttribute("search").toString();
		
		if (s.equals("!@#$%^&*")) {	
			return "redirect:/product";	
		}	
		
		
		Product list3 = productDao.getRandomProduct1();
		List<Product> list2 = productDao.getRandomProduct();
		List<Product> list1 = productDao.getBestSeller();
		
		List<Product> list = productDao.searchProducts(s);
		
		PagedListHolder<?> pages = (PagedListHolder<?>) request.getSession().getAttribute("productlist");	
		int pagesize = 9;	
		pages = new PagedListHolder<>(list);	
		pages.setPageSize(pagesize);	
			
		final int goToPage = pageNumber - 1;	
		if (goToPage <= pages.getPageCount() && goToPage >= 0) {	
			pages.setPage(goToPage);	
		}	
		
		request.getSession().setAttribute("productlist", pages);
		
		int current = pages.getPage() + 1;	
		int begin = Math.max(1, current - list.size());	
		int end = Math.min(begin + 5, pages.getPageCount());	
		int totalPageCount = pages.getPageCount();
		
		String baseUrl = "/products/search/";	
		
		model.addAttribute("beginIndex", begin);	
		model.addAttribute("endIndex", end);	
		model.addAttribute("currentIndex", current);	
		model.addAttribute("totalPageCount", totalPageCount);	
		model.addAttribute("baseUrl", baseUrl);	
		model.addAttribute("productlist", pages);
		model.addAttribute("bestseller",list1);
		model.addAttribute("random",list2);
		model.addAttribute("random1",list3);
		model.addAttribute("result", "search results for "+s);
		
		if(principal != null) {
			model.addAttribute("acc",userDao.getNameUser(authentication.getName()));			
		}
		
		return "products";	
	}
}
