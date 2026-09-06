package com.laudado.talentforgeaibackend.validation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Mirrors the frontend's age check exactly:
 *
 *   // lib/validation.ts — jobSeekerStep1Schema.dateOfBirth
 *   const age = (Date.now() - new Date(value).getTime()) / (1000*60*60*24*365.25);
 *   return age >= 16 && age <= 100;
 *
 * Applied to a String field holding an ISO date (yyyy-MM-dd).
 */
@Target({ElementType.FIELD, ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = MinimumAgeValidator.class)
public @interface MinimumAge {

    String message() default "You must be between {min} and {max} years old";

    int min() default 16;

    int max() default 100;

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
