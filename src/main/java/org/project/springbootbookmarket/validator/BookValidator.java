package org.project.springbootbookmarket.validator;

import jakarta.validation.ConstraintViolation;
import java.util.HashSet;
import java.util.Set;
import org.project.springbootbookmarket.domain.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;


public class BookValidator implements Validator {

    @Autowired
    private jakarta.validation.Validator beanValidator;

    public Set<Validator> springValidators;

    public BookValidator() {
        springValidators = new HashSet<>();
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return Book.class.isAssignableFrom(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        Set<ConstraintViolation<Object>> violations = beanValidator.validate(target);

        for (ConstraintViolation<Object> violation : violations) {
            String propertyPath = violation.getPropertyPath().toString();
            String message = violation.getMessage();
            errors.rejectValue(propertyPath, "", message);
        }
        springValidators.forEach(validator -> validator.validate(target, errors));
    }
}
