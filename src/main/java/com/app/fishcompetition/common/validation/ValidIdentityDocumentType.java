package com.app.fishcompetition.common.validation;

import com.app.fishcompetition.common.validation.classes.IdentityDocumentTypeValidator;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Constraint(validatedBy = IdentityDocumentTypeValidator.class)
@Target({ElementType.METHOD, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface ValidIdentityDocumentType {
    String message() default "Invalid identity document type";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
