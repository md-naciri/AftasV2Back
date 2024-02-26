package com.app.fishcompetition.services.impls.security;

import com.app.fishcompetition.model.entity.AppRole;
import com.app.fishcompetition.model.entity.Member;
import com.app.fishcompetition.repositories.MemberRepository;
import com.app.fishcompetition.repositories.RoleRepository;
import com.app.fishcompetition.services.security.AddRoleToUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
public class AddRoleToUserServiceImpl implements AddRoleToUserService {

    private final MemberRepository userRepository;
    private final RoleRepository roleRepository;

    @Override
    @Transactional
    public void addRoleToUser(String email, String roleName) {
        Member user= userRepository.findByEmail(email).orElseThrow(()-> new NoSuchElementException("User not found"));
        AppRole role =  roleRepository.findByAuthority(roleName).orElseThrow(() -> new NoSuchElementException("Role not found"));

        if (user.getAuthorities() != null ) {
            user.getAuthorities().add(role);
            role.getUsers().add(user);
        }

    }

}

