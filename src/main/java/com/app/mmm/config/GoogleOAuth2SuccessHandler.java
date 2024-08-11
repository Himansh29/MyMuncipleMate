package com.app.mmm.config;

import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import com.app.mmm.entity.Citizen;
import com.app.mmm.entity.Role;
import com.app.mmm.exception.ResourceNotFoundException;
import com.app.mmm.repository.CitizenRepository;
import com.app.mmm.repository.RoleRepository;

@Component
public class GoogleOAuth2SuccessHandler implements AuthenticationSuccessHandler {

	@Autowired
	private RoleRepository roleRepository;
	
	@Autowired
	private CitizenRepository citizenRepository;
	
	private RedirectStrategy redirectStrategy = new DefaultRedirectStrategy();
	
	@Override
	public void onAuthenticationSuccess(HttpServletRequest httpServletrequest, HttpServletResponse httpServletresponse,
			Authentication authentication) throws IOException, ServletException {
		
		OAuth2AuthenticationToken token = (OAuth2AuthenticationToken) authentication;
		String email = token.getPrincipal().getAttributes().get("email").toString();
		if(citizenRepository.findByEmail(email).isPresent()) {
			
		}else {
			Citizen citizen= new Citizen();
			citizen.setFirstName(token.getPrincipal().getAttributes().get("given_name").toString());
			citizen.setLastName(token.getPrincipal().getAttributes().get("family_name").toString());
			citizen.setEmail(email);
			Set<Role> roles = new HashSet<>();
			Role citizenRole = roleRepository.findByName("ROLE_CITIZEN")
					.orElseThrow(() -> new ResourceNotFoundException("ROLE NOT FOUND"));
			roles.add(citizenRole);
			citizen.setRoles(roles);
			citizenRepository.save(citizen);
		}
		
		redirectStrategy.sendRedirect(httpServletrequest, httpServletresponse, "/");
	}

	@Override
	public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, FilterChain chain,
			Authentication authentication) throws IOException, ServletException {
		AuthenticationSuccessHandler.super.onAuthenticationSuccess(request, response, chain, authentication);
	}

}
