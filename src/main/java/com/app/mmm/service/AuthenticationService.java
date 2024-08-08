package com.app.mmm.service;

import com.app.mmm.dto.ApiResponse;
import com.app.mmm.dto.ForgotPasswordDTO;
import com.app.mmm.dto.RegisterDTO;
import com.app.mmm.dto.SignInDTO;

public interface AuthenticationService {
	String signIn(SignInDTO signInDTO);
	ApiResponse register(RegisterDTO dto);
	ApiResponse forgotPassword(ForgotPasswordDTO forgotPasswordDTO);
}
