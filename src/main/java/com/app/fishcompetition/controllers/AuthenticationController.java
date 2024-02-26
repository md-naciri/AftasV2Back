package com.app.fishcompetition.controllers;

import com.app.fishcompetition.common.responses.RequestResponseWithDetails;
import com.app.fishcompetition.common.responses.RequestResponseWithoutDetails;
import com.app.fishcompetition.model.dto.request.LoginRequest;
import com.app.fishcompetition.model.dto.request.SignupRequest;
import com.app.fishcompetition.model.dto.request.TokenValidationRequest;
import com.app.fishcompetition.model.dto.request.VerificationEmailRequest;
import com.app.fishcompetition.model.dto.response.LoginResponse;
import com.app.fishcompetition.services.MemberService;
import com.app.fishcompetition.services.security.AuthenticationService;
import com.app.fishcompetition.services.security.JwtService;
import com.app.fishcompetition.services.security.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/auth")
public class AuthenticationController {
    private final AuthenticationService authenticationService;
    private final RequestResponseWithoutDetails responseWithoutDetails;
    private final RequestResponseWithDetails responseWithDetails;
    private final UserService userService;
    private final JwtService jwtService;

    @PostMapping("/signup")
    public ResponseEntity<RequestResponseWithDetails> signup(@RequestBody @Valid SignupRequest request){
        Map<String,Object> user = new HashMap<>();
        user.put("user",authenticationService.signup(request));
        responseWithDetails.setTimestamp(LocalDateTime.now());
        responseWithDetails.setStatus("200");
        responseWithDetails.setMessage("we send a code to your email");
        responseWithDetails.setDetails(user);
        return ResponseEntity.status(HttpStatus.CREATED).body(responseWithDetails);
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(@RequestBody @Valid LoginRequest request){
        return ResponseEntity.ok(authenticationService.login(request));
    }

    @PostMapping("/verify-email")
    public ResponseEntity<LoginResponse> verifyEmail(@RequestBody @Valid VerificationEmailRequest request){
        return ResponseEntity.ok(authenticationService.verifyEmail(request.getCode()));
    }
    @PostMapping("/validate-token")
    public ResponseEntity<Boolean> validateToken(@RequestBody TokenValidationRequest validationRequest) {
        String token = validationRequest.getToken();

        try {
            UserDetails userDetails = userService.userDetailsService()
                    .loadUserByUsername(jwtService.extractUserName(token));

            if (jwtService.isTokenValid(token, userDetails)) {
                return ResponseEntity.ok(true);
            } else {
                return ResponseEntity.ok(false);
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return ResponseEntity.ok(false);
        }
    }
    /*@PostMapping("/refresh-token")
    public ResponseEntity<?> refreshToken(@RequestBody String refreshToken) {
        UserDetails userDetails = userDetailsService.loadUserByUsername(jwtService.extractUserName(refreshToken));
        String newAccessToken = jwtService.generateNewAccessToken(refreshToken, userDetails);
        return ResponseEntity.ok(newAccessToken);
    }*/

}
