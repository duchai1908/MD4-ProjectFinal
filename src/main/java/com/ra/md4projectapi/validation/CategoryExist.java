package com.ra.md4projectapi.validation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Constraint(
        validatedBy = CategoryValidateConstraint.class
)
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface CategoryExist {
    String message() default "Category Name is exist";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
