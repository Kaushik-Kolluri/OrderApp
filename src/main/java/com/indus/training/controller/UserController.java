package com.indus.training.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.indus.training.domain.MyUser;
import com.indus.training.persist.MyUserRepository;
import com.indus.training.webtoken.JwtService;
import com.indus.training.webtoken.LoginForm;

@RestController
public class UserController {
	
	
	@Autowired
	private MyUserRepository myUserRepository; 
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	

	
	@PostMapping("/register/user")
	public MyUser createUser(@RequestBody MyUser user) {
		
		user.setPassword(passwordEncoder.encode(user.getPassword()));
		return myUserRepository.save(user);
		
	}
	
	

}
