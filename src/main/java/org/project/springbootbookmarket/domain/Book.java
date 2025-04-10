package org.project.springbootbookmarket.domain;

import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import java.math.BigDecimal;
import lombok.Data;
import org.project.springbootbookmarket.validator.BookId;
import org.springframework.web.multipart.MultipartFile;

@Data
public class Book {

    @BookId
    @Pattern(regexp = "ISBN[1-9]+", message = "{Pattern.book.bookId}")
    private String bookId;              // 도서 ID

    @Size(min = 4, max = 50, message = "{Size.book.name}")
    private String name;                // 도서 제목

    @Min(value = 0, message = "{Min.book.unitPrice}")
    @Digits(integer = 8, fraction = 2, message = "{Digits.book.unitPrice}")
    @NotNull(message = "{NotNull.book.unitPrice}")
    private BigDecimal unitPrice;       // 가격

    private String author;              // 저자
    private String description;         // 설명
    private String publisher;           // 출판사
    private String category;            // 분류
    private BigDecimal unitsInStock;    // 재고수
    private String releaseDate;         // 출판일(월/년)
    private String condition;           // 신규도서 or 중고도서 or 전자책
    private String fileName;            // 도서 이미지 파일명
    private MultipartFile bookImage;    // 도서 이미지
}
