package com.spring.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spring.exception.InvalidCredentialsException;
import com.spring.model.Product;
import com.spring.model.User;
import com.spring.repository.CustomerRepository;

@Service
public class CustomerService {

	@Autowired
	private CustomerRepository customerRepository;

	public User verifyLogin(String username, String password) throws InvalidCredentialsException {
		return customerRepository.verifyLogin(username, password);
	}

	public void buyProduct(int productId, String username) throws Exception {
		if (customerRepository.isProductAvailable(productId)) {
			customerRepository.processPurchase(productId, username);
		} else {
			throw new Exception("Product is not available.");
		}
	}

		public List<Product> getPurchasedProductsByUser(String username) {
			return customerRepository.findPurchasedProductsByUsername(username);
		}

}
