package com.LND.SportStore.controller;


import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.LND.SportStore.model.UserDetail;
import com.LND.SportStore.service.LoginService;

@Controller
public class LoginController {
	
	@Autowired
	private LoginService loginService;

	private static final Logger log = LoggerFactory.getLogger(LoginController.class);
	
	@GetMapping("/login")
	public String login(@RequestParam(name = "error",required = false) String error,Model model) {
		log.error(error);
		return "login";
	}
		
	@PostMapping("/logout")
	public String logout(HttpServletRequest request, HttpServletResponse response) {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		if(authentication != null) {
			new SecurityContextLogoutHandler().logout(request, response, authentication);
		}
		return "redirect:/login";
	}
	
	
	@PostMapping("/register")
	public String register(@ModelAttribute UserDetail user,Model model,HttpServletRequest req, HttpServletResponse rep) {
		
		if (user.getPassword().equals(user.getConfirmPassword())) {
			if(loginService.addUser(user.getUsername(),
									user.getPassword(),
									user.getFullname(),
									user.getEmail(),
									user.getTel())) {
				try {
					req.login(user.getUsername(), user.getPassword());
				} catch (ServletException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				return "redirect:/";
			}else {
				model.addAttribute("noti", "Username already is used!!!");
				return "login";
			}
		} else {
			model.addAttribute("noti", "Confirm Password incorrect!!!");
			return "login";
		}
		
	}
}
