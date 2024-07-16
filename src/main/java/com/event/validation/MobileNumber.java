package com.event.validation;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = MobileNumberValidator.class)
@Target({ElementType.METHOD, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface MobileNumber {
    String message() default "Invalid mobile number";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}