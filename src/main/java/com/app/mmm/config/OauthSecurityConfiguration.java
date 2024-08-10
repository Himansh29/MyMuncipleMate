package com.app.mmm.config;

import org.springframework.boot.autoconfigure.security.SecurityProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class OauthSecurityConfiguration {

	@Bean
	@Order(SecurityProperties.BASIC_AUTH_ORDER)
	SecurityFilterChain oauthFilterSecuriyChain(HttpSecurity http) throws Exception {
		http.authorizeHttpRequests()
        .antMatchers("/api/auth/google-login-success", "/api/auth/google-login-failure").permitAll()
        .anyRequest().authenticated()
        .and()
        .oauth2Login(oauth2 -> oauth2
            .defaultSuccessUrl("/api/auth/google-login-success", true) 
            .failureUrl("/api/auth/google-login-failure") 
        );

    return http.build();
	}
}
