package com.app.mmm.serviceimple;

import java.util.HashSet;
import java.util.Set;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.app.mmm.dto.ApiResponse;
import com.app.mmm.dto.ForgotPasswordDTO;
import com.app.mmm.dto.RegisterDTO;
import com.app.mmm.dto.SignInDTO;
import com.app.mmm.entity.Citizen;
import com.app.mmm.entity.Role;
import com.app.mmm.exception.ResourceNotFoundException;
import com.app.mmm.repository.CitizenRepository;
import com.app.mmm.repository.RoleRepository;
import com.app.mmm.security.JwtTokenProvider;
import com.app.mmm.service.AuthenticationService;

@Service
@Transactional
public class AuthenticationServiceImple implements AuthenticationService {

	@Autowired
    private CitizenRepository citizenRepository;
	
	@Autowired
	private RoleRepository roleRepository;
	
	@Autowired
	private PasswordEncoder encoder;
	
	@Autowired
	private AuthenticationManager authenticationManager;
	
	@Autowired
	private JwtTokenProvider jwtTokenProvider;
	
	@Override
	public String signIn(SignInDTO signInDTO) {
		
		Authentication authenticate = authenticationManager
				.authenticate(new UsernamePasswordAuthenticationToken(signInDTO.getUsernameOrEmail(), signInDTO.getPassword()));
		
		SecurityContextHolder.getContext().setAuthentication(authenticate);
		
		String token = jwtTokenProvider.generateToken(authenticate);
		
	    return token;
	}

	@Override
	public ApiResponse register(RegisterDTO regisetrDTO) {

		if(citizenRepository.existsByUsername(regisetrDTO.getUsername())) {
			throw new ResourceNotFoundException(HttpStatus.BAD_REQUEST, "Username already exists");
		}
		
		if(citizenRepository.existsByEmail(regisetrDTO.getEmail())) {
			throw new ResourceNotFoundException(HttpStatus.BAD_REQUEST, "Email already exists");
		}
		
		Citizen citizen = new Citizen();
        citizen.setFirstName(regisetrDTO.getFirstName());
        citizen.setLastName(regisetrDTO.getLastName());
        citizen.setUsername(regisetrDTO.getUsername());
        citizen.setEmail(regisetrDTO.getEmail());
        citizen.setPassword(encoder.encode(regisetrDTO.getPassword()));

        Set<Role> roles = new HashSet<>();
        Role citizenRole = roleRepository.findByName("ROLE_CITIZEN").orElseThrow(() -> new ResourceNotFoundException("ROLE NOT FOUND"));
        roles.add(citizenRole);
        
        citizen.setRoles(roles);
        
        citizenRepository.save(citizen);
        return new ApiResponse("Registration successful");
	}

	@Override
	public ApiResponse forgotPassword(ForgotPasswordDTO forgotPasswordDTO) {
		if (!forgotPasswordDTO.getPassword().equals(forgotPasswordDTO.getConfirmPassword())) {
            throw new IllegalArgumentException("Passwords do not match");
        }

        Citizen citizen = citizenRepository.findByEmail(forgotPasswordDTO.getEmail())
                .orElseThrow(() -> new ResourceNotFoundException("Email not found"));

        citizen.setPassword(encoder.encode(forgotPasswordDTO.getPassword()));
        citizenRepository.save(citizen);

        return new ApiResponse("Password reset successful");
	}

}
