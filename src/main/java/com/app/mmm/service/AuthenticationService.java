package com.app.mmm.service;

import com.app.mmm.dto.ApiResponse;
import com.app.mmm.dto.ForgotPasswordDTO;
import com.app.mmm.dto.RegisterDTO;
import com.app.mmm.dto.SignInDTO;
import com.app.mmm.dto.VerifyOtpDTO;

public interface AuthenticationService {
	String signIn(SignInDTO signInDTO);
	ApiResponse register(RegisterDTO dto);
	ApiResponse sendOtpForPasswordReset(String email);
    ApiResponse verifyOtpAndResetPassword(VerifyOtpDTO verifyOtpDTO);
}
