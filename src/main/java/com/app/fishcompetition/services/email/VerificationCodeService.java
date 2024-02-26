package com.app.fishcompetition.services.email;

public interface VerificationCodeService {
    boolean verifyExistCode(String code);
}
