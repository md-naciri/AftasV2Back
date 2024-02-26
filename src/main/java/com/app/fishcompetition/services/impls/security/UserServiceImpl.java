package com.app.fishcompetition.services.impls.security;

import com.app.fishcompetition.repositories.MemberRepository;
import com.app.fishcompetition.services.security.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImpl  implements UserService {

    private final MemberRepository memberRepository;
    @Override
    public UserDetailsService userDetailsService() {
        return new UserDetailsService() {
            @Override
            public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
                return memberRepository.findByEmail(username).orElseThrow(() -> new UsernameNotFoundException(" user not found"));
            }
        };
    }
}
