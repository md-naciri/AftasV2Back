package com.app.fishcompetition.services.security;

import com.app.fishcompetition.model.dto.request.LoginRequest;
import com.app.fishcompetition.model.dto.request.SignupRequest;
import com.app.fishcompetition.model.dto.response.LoginResponse;
import com.app.fishcompetition.model.dto.response.MemberDTO;

public interface AuthenticationService  {
    LoginResponse login(LoginRequest request);
    MemberDTO signup(SignupRequest request);

    LoginResponse verifyEmail(String code);
}
