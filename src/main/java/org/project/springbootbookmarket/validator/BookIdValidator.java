package org.project.springbootbookmarket.validator;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.project.springbootbookmarket.domain.Book;
import org.project.springbootbookmarket.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;

public class BookIdValidator implements ConstraintValidator<BookId, String> {

    @Autowired
    private BookService bookService;

    @Override
    public void initialize(BookId constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext constraintValidatorContext) {
        Book book;
        try {
            book = bookService.getBookById(value);
        } catch (RuntimeException e) {
            return true;
        }
        return book == null;
    }
}
