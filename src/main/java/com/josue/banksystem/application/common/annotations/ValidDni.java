package com.josue.banksystem.application.common.annotations;

import com.josue.banksystem.application.common.validations.DniValidator;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

@Documented
@Constraint(validatedBy = {DniValidator.class})
@Target({ElementType.FIELD})
@Retention(java.lang.annotation.RetentionPolicy.RUNTIME)
public @interface ValidDni {
    String message() default "Invalid DNI format";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
