package com.app.mmm.serviceimple;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserService;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

import com.app.mmm.entity.Citizen;
import com.app.mmm.entity.CustomeUserDetails;
import com.app.mmm.repository.CitizenRepository;

@Service
public class CustomUserDetailService implements OAuth2UserService<OAuth2UserRequest, OAuth2User>   {

	@Autowired
	private CitizenRepository repository;

	@Override
	public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {
		OAuth2UserService<OAuth2UserRequest, OAuth2User> delegate = new DefaultOAuth2UserService();
        OAuth2User oAuth2User = delegate.loadUser(userRequest);

        // Extract user information from OAuth2User
        Map<String, Object> attributes = oAuth2User.getAttributes();
        String email = (String) attributes.get("email");

        // Check if the user already exists
        Optional<Citizen> userOptional = repository.findByEmail(email);
        Citizen user;
        
        if (userOptional.isPresent()) {
            user = userOptional.get();
        } else {
            // If the user doesn't exist, create a new user
        	user = new Citizen();
        	user.setEmail(email);
            user.setFirstName((String) attributes.get("given_name")); // Google provides 'given_name' for first name
            user.setLastName((String) attributes.get("family_name")); // Google provides 'family_name' for last name
            user.setUsername(email); // Set username as email or some unique identifier
            user.setPassword(""); // Set a default or empty password, as OAuth2 does not provide passwords
            // Save the new user
            repository.save(user);
        }

        return (OAuth2User) new CustomeUserDetails(user);
	}
	
	

}
