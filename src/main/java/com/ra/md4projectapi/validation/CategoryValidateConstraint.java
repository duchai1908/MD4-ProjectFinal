package com.ra.md4projectapi.validation;

import com.ra.md4projectapi.model.service.ICategoryService;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CategoryValidateConstraint implements ConstraintValidator<CategoryExist, String> {
    private final ICategoryService categoryService;
    @Override
    public boolean isValid(String s, ConstraintValidatorContext constraintValidatorContext) {
        return !categoryService.existByCategoryName(s);
    }
}
