package com.app.fishcompetition.common.validation.classes;

import com.app.fishcompetition.common.validation.ValidIdentityDocumentType;
import com.app.fishcompetition.enums.IdentityDocumentType;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class IdentityDocumentTypeValidator implements ConstraintValidator<ValidIdentityDocumentType, String> {

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {

        for (IdentityDocumentType type : IdentityDocumentType.values()) {
            if (type.name().equals(value)) {
                return true;
            }
        }
        return false;
    }
}