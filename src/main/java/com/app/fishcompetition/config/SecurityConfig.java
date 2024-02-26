package com.app.fishcompetition.config;

import com.app.fishcompetition.filter.JwtAuthenticationFilter;
import com.app.fishcompetition.oauth2.filter.TokenAuthenticationFilter;
import com.app.fishcompetition.oauth2.service.CustomOAuth2UserService;
import com.app.fishcompetition.oauth2.user.UserPrincipal;
import com.app.fishcompetition.services.security.UserService;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.core.user.DefaultOAuth2User;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.logout.LogoutHandler;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {

    private final JwtAuthenticationFilter jwtAuthenticationFilter;
    private final UserService userService;
    private final LogoutHandler logoutHandler;
    private final CustomOAuth2UserService customOAuth2UserService;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception{

        http
                .csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(request -> request.requestMatchers("/api/v1/auth/**")
                        .permitAll().anyRequest().authenticated())
                .sessionManagement(manager -> manager.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authenticationProvider(authenticationProvider())
                .addFilterBefore(
                        jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class
                ).oauth2Login(oauth2Login -> oauth2Login
                        .authorizationEndpoint(authorizationEndpoint -> authorizationEndpoint.baseUri("/api/v1/oauth2/authorize"))
                        .redirectionEndpoint(redirectionEndpoint -> redirectionEndpoint.baseUri("/api/v1/oauth2/callback"))
                        .userInfoEndpoint(userInfoEndpoint ->
                                userInfoEndpoint
                                        .userService(customOAuth2UserService)
                        ).successHandler((request, response, authentication) -> {
                            Object principal = authentication.getPrincipal();
                            if (principal instanceof DefaultOAuth2User) {
                                DefaultOAuth2User defaultOAuth2User = (DefaultOAuth2User) principal;
                                System.out.println("ok user default oauth2 user");

                                response.sendRedirect("/competition");
                            } else if (principal instanceof UserPrincipal) {
                                UserPrincipal userPrincipal = (UserPrincipal) principal;
                                System.out.println("ok user principal");
                                response.sendRedirect("http://localhost:4200/competitions");
                            } else {
                                throw new IllegalArgumentException("Principal must be an instance of DefaultOAuth2User or UserPrincipal");
                            }
                        })
                )
                .addFilterBefore(tokenAuthenticationFilter(),UsernamePasswordAuthenticationFilter.class)
                .logout(
                        (logout) -> logout.logoutUrl("/api/v1/auth/logout")
                                .addLogoutHandler(logoutHandler)
                                .logoutSuccessHandler((request, response, authentication) -> {
                                    SecurityContextHolder.clearContext();
                                    response.setStatus(HttpServletResponse.SC_OK);
                                })
                );
        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }
    @Bean
    public AuthenticationProvider authenticationProvider(){
        DaoAuthenticationProvider authProvider =new DaoAuthenticationProvider();
        authProvider.setUserDetailsService(userService.userDetailsService());
        authProvider.setPasswordEncoder(passwordEncoder());
        return authProvider;
    }
    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception{
        return  config.getAuthenticationManager();
    }
    @Bean
    public TokenAuthenticationFilter tokenAuthenticationFilter() {
        return new TokenAuthenticationFilter();
    }
}