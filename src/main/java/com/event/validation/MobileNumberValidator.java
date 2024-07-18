package com.event.validation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.regex.Pattern;


// Currently we are not using custom validation functionality.
public class MobileNumberValidator implements ConstraintValidator<MobileNumber, String> {
    private static final Pattern MOBILE_NUMBER_PATTERN = Pattern.compile("^\\d{10}$");

    @Override
    public void initialize(MobileNumber constraintAnnotation) {}

    @Override
    public boolean isValid(String mobileNumber, ConstraintValidatorContext constraintValidatorContext) {
        return mobileNumber != null && MOBILE_NUMBER_PATTERN.matcher(mobileNumber).matches();
    }
}

