package com.app.mmm.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;

import com.app.mmm.security.JwtAuthenticationEntryPoint;
import com.app.mmm.security.JwtAuthenticationFilter;

import lombok.AllArgsConstructor;

@Configuration
@EnableMethodSecurity
@AllArgsConstructor
public class SpringSecurityConfig {

	@Autowired
	private UserDetailsService userDetailsService;

	@Autowired
	private JwtAuthenticationEntryPoint authenticationEntryPoint;

	@Autowired
	private JwtAuthenticationFilter authenticationFilter;

	@Bean
	private static PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
	    http.cors().and() 
	        .csrf().disable()
	        .authorizeHttpRequests(authorize -> {
	            authorize.antMatchers(HttpMethod.POST, "/api/auth/**").permitAll(); // Corrected the endpoint pattern
	            authorize.antMatchers("/api/admin/**").hasRole("ADMIN");
	            authorize.antMatchers("/api/teams/**").hasRole("ADMIN");
	            authorize.antMatchers("/api/citizens/**").authenticated();
	            authorize.antMatchers("/api/complaints/**").authenticated();
	            authorize.antMatchers("/api/feedback/**").authenticated();
	            authorize.anyRequest().authenticated();
	        })
	        .exceptionHandling(exception -> exception
	                .authenticationEntryPoint(authenticationEntryPoint))
	        .addFilterBefore(authenticationFilter, UsernamePasswordAuthenticationFilter.class)
	        .cors(cors -> cors
	            .configurationSource(request -> {
	                CorsConfiguration corsConfiguration = new CorsConfiguration();
	                corsConfiguration.addAllowedOrigin("http://localhost:3000");
	                corsConfiguration.addAllowedMethod("*");
	                corsConfiguration.addAllowedHeader("*");
	                corsConfiguration.setAllowCredentials(true);
	                return corsConfiguration;
	            })
	        );
	    
	    return http.build();
	}


	@Bean
	public AuthenticationManager authenticationManager(AuthenticationConfiguration configuration) throws Exception {
		return configuration.getAuthenticationManager();
	}

//	@Bean
//	public UserDetailsService userDetailsService() {
//		UserDetails user = User.builder()
//				.username("himanshu")
//				.password(passwordEncoder().encode("password"))
//				.roles("USER")
//				.build();
//		
//		UserDetails user2 = User.builder()
//				.username("sanket")
//				.password(passwordEncoder().encode("password"))
//				.roles("ADMIN")
//				.build();
//		
//		UserDetails user3 = User.builder()
//				.username("saurabh")
//				.password(passwordEncoder().encode("CITIZEN"))
//				.roles("ADMIN")
//				.build();
//		
//		return new InMemoryUserDetailsManager(user, user2);
//	}
}