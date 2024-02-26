package com.app.fishcompetition.services.impls.security;

import com.app.fishcompetition.repositories.MemberRepository;
import com.app.fishcompetition.services.security.JwtService;
import com.app.fishcompetition.services.security.LogoutService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class LogoutServiceImpl implements LogoutService {

    private final JwtService jwtService;
    private final MemberRepository userRepository;

    @Override
    public void logout(HttpServletRequest request, HttpServletResponse response, Authentication authentication) {

        final String authHeader = request.getHeader("Authorization");
        final String jwt;
        final String email;
        if(StringUtils.isEmpty(authHeader) || !StringUtils.startsWith(authHeader,"Bearer ")){
            return;
        }
        jwt = authHeader.substring(7);
        email =jwtService.extractUserName(jwt);

        var user = userRepository.findByEmail(email).orElse(null);
        if(user != null){
            user.setCredentialsNonExpired(false);
            userRepository.save(user);
        }
    }
}
