package com.spring.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.spring.exception.InvalidCredentialsException;
import com.spring.model.Product;
import com.spring.model.User;
import com.spring.service.CustomerService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

@Controller
public class CustomerController {

	@Autowired
	private CustomerService customerService;

	@GetMapping("/login")
	public String showLogin() {
		return "login";
	}

	@GetMapping("/login-form")
	public String handleLogin(HttpServletRequest req, HttpSession session) {
		String username = req.getParameter("username");
		String password = req.getParameter("password");

		try {
			User user = customerService.verifyLogin(username, password);
			session.setAttribute("username", user.getUsername());
			return "dashboard";
		} catch (InvalidCredentialsException e) {
			req.setAttribute("msg", e.getMessage());
			return "dashboard";
		}
	}

	@PostMapping("/buy")
	public ModelAndView buyProduct(@RequestParam("productId") int productId, HttpSession session) {
		ModelAndView modelAndView = new ModelAndView("dashboard");

		try {
			String username = (String) session.getAttribute("username");
			customerService.buyProduct(productId, username);
			modelAndView.addObject("message", "Purchase successful!");
		} catch (Exception e) {
			modelAndView.addObject("message", "Error: " + e.getMessage());
		}

		return modelAndView;
	}

	@GetMapping("/purchased-products")
	public String fetchPurchasedProducts(HttpServletRequest req, HttpSession session) {
		String username = (String) session.getAttribute("username");
		List<Product> purchasedProducts = customerService.getPurchasedProductsByUser(username);
		req.setAttribute("purchasedProducts", purchasedProducts);
		return "purchased_products";
	}

}
