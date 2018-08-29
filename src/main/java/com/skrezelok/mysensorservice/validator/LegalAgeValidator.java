package com.skrezelok.mysensorservice.validator;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.time.LocalDate;

public class LegalAgeValidator implements ConstraintValidator<LegalAge, Integer> {
    @Override
    public void initialize(LegalAge constraintAnnotation) {

    }

    @Override
    public boolean isValid(Integer yearOfBirth, ConstraintValidatorContext constraintValidatorContext) {
        return yearOfBirth != null && LocalDate.now().getYear() - yearOfBirth >= 18;
    }
}
