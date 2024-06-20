package com.indus.training.securityconfig;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractAuthenticationFilterConfigurer;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.indus.training.service.MyUserDetailsService;
import com.indus.training.webtoken.JwtAuthenticationFilter;


@Configuration
@EnableWebSecurity
public class SecurityConfig {

	@Autowired
	private MyUserDetailsService userDetailsService;
	
	@Autowired
	private JwtAuthenticationFilter jwtAuthenticationFilter;

	
	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
	    return httpSecurity
	            .csrf(httpSecurityCsrfConfigurer -> httpSecurityCsrfConfigurer.disable())
	            .authorizeHttpRequests(registry -> {
	                registry.requestMatchers("/register/**", "/orders/save", "/orders/authenticate").permitAll();
	                registry.requestMatchers("/orders/retrieveAll").hasRole("USER");
	                registry.requestMatchers("/orders/retrieve").hasRole("USER");
	                registry.requestMatchers("/orders/msgOne").hasRole("USER");
	                registry.requestMatchers("/orders/msgTwo").hasRole("USER");
	                registry.anyRequest().hasRole("USER");
	            })
	            .formLogin(formLogin -> formLogin
	            		
	            		.successHandler(new SuccessHandler())
	                    .permitAll()
	                    
	            )
	            .addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class)
	            .build();
	}

//	@Bean 
//	public UserDetailsService userDetailService() { // In Memory Auth
//
//		UserDetails regularUser = User.builder().username("kaushik")
//				.password("$2a$12$Ty/vD4AkTtqje4/Cdbr7kOHI6bkQipWDPUkkIG4rJE.8gfWg6MhJW").roles("USER").build();
//		UserDetails adminUser = User.builder().username("admin")
//				.password("$2a$12$AQoTO.mRVYhd5y5Frb8meeCaP5rmal2MB62C4P4UuIoyOymsRXL9K").roles("ADMIN").build();
//
//		return new InMemoryUserDetailsManager(regularUser, adminUser);
//	}

	@Bean
	public UserDetailsService userDetailsServiceBean() {

		return userDetailsService;

	}

	@Bean
	public AuthenticationProvider authenticationProvider() {

		DaoAuthenticationProvider provider = new DaoAuthenticationProvider();

		provider.setUserDetailsService(userDetailsService);
		provider.setPasswordEncoder(passwordEncoder());
		return provider;
	}
	
	@Bean
	public AuthenticationManager authenticationManager() {
		return new ProviderManager(authenticationProvider());
	}

	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

}
