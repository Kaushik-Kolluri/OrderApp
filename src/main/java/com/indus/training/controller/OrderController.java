package com.indus.training.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;

import com.indus.training.domain.Orders;
import com.indus.training.service.MyUserDetailsService;
import com.indus.training.service.OrderService;
import com.indus.training.webtoken.JwtService;
import com.indus.training.webtoken.LoginForm;

@RestController
@RequestMapping("/orders")
public class OrderController {

	@Autowired
	private OrderService orderService;
	
	@Autowired
	private AuthenticationManager authenticationManager; //authenticates the user with username and password explicitly.
	
	@Autowired
	private JwtService jwtService;  
	
	@Autowired
	private MyUserDetailsService myUserDetailsService;

	@PostMapping("/save")
	public Orders saveOrder(@RequestBody Orders order) {
		return orderService.saveOrder(order);
	}

	@GetMapping("/retrieve")
	public Orders retrieveOrder(@RequestParam("orderID") Integer orderID) {
		return orderService.retrieveById(orderID);
	}

	@GetMapping("/retrieveAll")
	public List<Orders> retrieveAll() {
		return orderService.retrieveAll();
	}

	@DeleteMapping("/delete/{orderID}")
	public void deleteOrder(@PathVariable Integer orderID) {

		orderService.deleteById(orderID);

	}
	
	@GetMapping("/msgOne")
	public String demoMessageOne() {
		
		return "Ciao, Io Sono Kaushik di India.";
	}
	
	@GetMapping("/msgTwo")
	public String demoMessageTwo() {
		
		return "Hello, I am Kaushik from India.";
	}
	
	@PostMapping("/authenticate")
	public String authenticateAndGetToken(@RequestBody LoginForm loginForm) {
		
		Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginForm.username(), loginForm.password()));
		
		if(authentication.isAuthenticated()) {
			
			return jwtService.generateToken(myUserDetailsService.loadUserByUsername(loginForm.username()));
			
		} else {
			
			throw new UsernameNotFoundException("Invalid Credentials");
		}
		
	}
}
