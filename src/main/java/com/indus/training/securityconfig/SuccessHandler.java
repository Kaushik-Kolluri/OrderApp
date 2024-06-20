package com.indus.training.securityconfig;

import java.io.IOException;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class SuccessHandler extends SavedRequestAwareAuthenticationSuccessHandler{
	
	@Override
	public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
			Authentication authentication) throws ServletException, IOException {
		
		boolean isUser = authentication.getAuthorities().stream()
							.anyMatch(grantedAuthority -> grantedAuthority.getAuthority().equals("ROLE_USER"));
		if(isUser) {
			
			setDefaultTargetUrl("/RetrieveOrderForm.html");
			
		}
		
		super.onAuthenticationSuccess(request, response, authentication);
	}

}
