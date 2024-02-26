package com.app.fishcompetition.services.impls.security;

import com.app.fishcompetition.common.exceptions.custom.EmailVerificationException;
import com.app.fishcompetition.common.exceptions.custom.UserAllReadyExistException;
import com.app.fishcompetition.enums.Provider;
import com.app.fishcompetition.model.dto.request.LoginRequest;
import com.app.fishcompetition.model.dto.request.SignupRequest;
import com.app.fishcompetition.model.dto.response.LoginResponse;
import com.app.fishcompetition.model.dto.response.MemberDTO;
import com.app.fishcompetition.model.entity.Member;
import com.app.fishcompetition.repositories.MemberRepository;
import com.app.fishcompetition.services.email.EmailServiceSender;
import com.app.fishcompetition.services.email.VerificationCodeService;
import com.app.fishcompetition.services.security.AddRoleToUserService;
import com.app.fishcompetition.services.security.AuthenticationService;
import com.app.fishcompetition.services.security.JwtService;
import jakarta.servlet.http.Cookie;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.NoSuchElementException;
import java.util.Random;

@Service
@RequiredArgsConstructor
public class AuthenticationServiceImpl  implements AuthenticationService {

    private final MemberRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;
    private final AddRoleToUserService addRoleToUserService;
    private final EmailServiceSender emailServiceSender;
    private final VerificationCodeService verificationCodeService;

    @Override
    public LoginResponse login( LoginRequest request) {
        try{
            Member user = userRepository.findByEmail(request.getEmail()).orElseThrow(() -> new UsernameNotFoundException("email not exist "));
            if(!user.isEnabled()){
                throw new EmailVerificationException("please verify your email to enable your account");
            }else{
                if(!user.isCredentialsNonExpired()){
                    user.setCredentialsNonExpired(true);
                    userRepository.save(user);
                }
                authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.getEmail(),request.getPassword()));
                var jwtAccessToken = jwtService.generateAccessToken(user);
                var jwtRefreshToken = jwtService.generateRefreshToken(user);
                return LoginResponse.builder().firstName(user.getFirstName()).lastName(user.getLastName()).accessToken(jwtAccessToken).refreshToken(jwtRefreshToken).build();
            }
        }catch(AuthenticationException e){
            throw new IllegalArgumentException("invalid email or password");
        }


    }

    @Override
    public MemberDTO signup(SignupRequest request) {
        userRepository.findByEmail(request.getEmail())
                .ifPresent(
                        (Member existingUser)->  {throw  new UserAllReadyExistException("User with this email already exist");}
                );
        String verificationCode = generateVerificationCode();
        var user = Member.builder().firstName(request.getFirstName()).lastName(request.getLastName()).email(request.getEmail()).verificationCode(verificationCode).nationality(request.getNationality()).identityNumber(request.getIdentityNumber()).identityDocumentType(request.getIdentityDocumentType()).enabled(false).credentialsNonExpired(true).accountNonLocked(true).accountNonExpired(true).authorities(new ArrayList<>()).provider(Provider.local).password(passwordEncoder.encode(request.getPassword())).createdAt(LocalDateTime.now()).updatedAt(LocalDateTime.now()).build();

        userRepository.save(user);
        addRoleToUserService.addRoleToUser(user.getEmail(),"ROLE_ADHERENT");
        String body = "Dear "+user.getFirstName()+ ",\n" +
                "\n" +
                "Thank you for registering with our service. To verify your email address, please use the following verification code:\n" +
                "\n" +
                "Verification Code: "+verificationCode+"\n" +
                "\n" +
                "Please enter this code on the verification page to complete the registration process.\n" +
                "\n" +
                "If you did not request this verification, please ignore this email.\n" +
                "\n" +
                "Best regards,";
        emailServiceSender.sendEmail(user.getEmail(),"Email Verification Code",body);
        return MemberDTO.builder().id(user.getId()).firstName(user.getFirstName()).lastName(user.getLastName()).email(user.getUsername()).accountNonExpired(user.isAccountNonExpired()).accountNonLocked(user.isAccountNonLocked()).credentialsNonExpired(user.isCredentialsNonExpired()).enabled(user.isEnabled()).createdAt(user.getCreatedAt()).updatedAt(user.getUpdatedAt()).build();
    }

    @Override
    public LoginResponse verifyEmail(String code) {
        var user = userRepository.findByVerificationCode(code).orElseThrow(()-> new NoSuchElementException("Wrong Code"));
        user.setEnabled(true);
        userRepository.save(user);
        var jwtAccessToken = jwtService.generateAccessToken(user);
        var jwtRefreshToken = jwtService.generateRefreshToken(user);
        return LoginResponse.builder().firstName(user.getFirstName()).lastName(user.getLastName()).accessToken(jwtAccessToken).refreshToken(jwtRefreshToken).build();
    }

    private  String generateVerificationCode() {
        int codeLength = 6;
        StringBuilder verificationCode = new StringBuilder();
        for (int i = 0; i < codeLength; i++) {
            verificationCode.append(new Random().nextInt(10));
        }
        String code = verificationCode.toString();
        if(!verificationCodeService.verifyExistCode(code)){
            generateVerificationCode();
        }
        return verificationCode.toString();
    }


}