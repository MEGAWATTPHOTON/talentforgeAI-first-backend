package com.laudado.talentforgeaibackend.validation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeParseException;

public class MinimumAgeValidator implements ConstraintValidator<MinimumAge, String> {

    private int min;
    private int max;

    @Override
    public void initialize(MinimumAge constraintAnnotation) {
        this.min = constraintAnnotation.min();
        this.max = constraintAnnotation.max();
    }

    @Override
    public boolean isValid(String dateOfBirth, ConstraintValidatorContext context) {
        // Let @NotBlank handle the empty case — an unset value shouldn't
        // also fail age validation with a confusing message.
        if (dateOfBirth == null || dateOfBirth.isBlank()) {
            return true;
        }

        LocalDate dob;
        try {
            dob = LocalDate.parse(dateOfBirth);
        } catch (DateTimeParseException ex) {
            // Malformed dates are rejected by @Pattern on the field; don't
            // double-report here.
            return true;
        }

        int age = Period.between(dob, LocalDate.now()).getYears();
        return age >= min && age <= max;
    }
}
