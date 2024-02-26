package com.app.fishcompetition.services.impls.email;

import com.app.fishcompetition.repositories.MemberRepository;
import com.app.fishcompetition.services.email.VerificationCodeService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class VerificationCodeServiceImpl implements VerificationCodeService {

    private final MemberRepository userRepository;
    @Override
    public boolean verifyExistCode(String code) {
        return userRepository.findByVerificationCode(code).isEmpty();
    }
}
