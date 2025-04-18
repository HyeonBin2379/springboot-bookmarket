package org.project.springbootbookmarket.repository;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;
import org.project.springbootbookmarket.domain.Book;
import org.project.springbootbookmarket.exception.BookIdException;
import org.springframework.stereotype.Repository;

@Repository
public class BookRepositoryImpl implements BookRepository {

    private final List<Book> listOfBooks = new ArrayList<>();

    public BookRepositoryImpl() {
        Book book1 = new Book();
        Book book2 = new Book();
        Book book3 = new Book();

        book1.setBookId("ISBN1234");
        book1.setName("자바스크립트 입문");
        book1.setUnitPrice(new BigDecimal("30000"));
        book1.setAuthor("조현영");
        book1.setDescription("자바스크립트의 기초부터 심화까지 핵심 문법을 학습한 후 12가지 프로그램을 만들면서 학습 내용을 확인 가능한 학습서");
        book1.setPublisher("길벗");
        book1.setCategory("IT전문서");
        book1.setUnitsInStock(new BigDecimal(1000));
        book1.setReleaseDate("2024/02/20");
        book1.setFileName("ISBN1234.jpg");

        book2.setBookId("ISBN1235");
        book2.setName("파이썬의 정석");
        book2.setUnitPrice(new BigDecimal("29800"));
        book2.setAuthor("조용주, 임좌상");
        book2.setDescription("머신러닝, 사물 인터넷(IoT), 데이터 분석 등 다양한 분야에 활용되는 직관적이고 간결한 문법의 파이썬 프로그래밍 언어를 학습 가능");
        book2.setPublisher("길벗");
        book2.setCategory("IT교육교재");
        book2.setUnitsInStock(new BigDecimal(1000));
        book2.setReleaseDate("2023/01/10");
        book2.setFileName("ISBN1235.jpg");

        book3.setBookId("ISBN1236");
        book3.setName("안드로이드 프로그래밍");
        book3.setUnitPrice(new BigDecimal("36000"));
        book3.setAuthor("송미영");
        book3.setDescription("안드로이드의 기본 개념을 체계적으로 익히고 이를 실습 예제를 통해 체득하면서 실전 프로젝트에서의 응용력을 키울 수 있는 학습서");
        book3.setPublisher("길벗");
        book3.setCategory("IT교육교재");
        book3.setUnitsInStock(new BigDecimal(1000));
        book3.setReleaseDate("2023/06/30");
        book3.setFileName("ISBN1236.jpg");

        listOfBooks.add(book1);
        listOfBooks.add(book2);
        listOfBooks.add(book3);
    }

    @Override
    public List<Book> getAllBookList() {
        return listOfBooks;
    }

    @Override
    public Book getBookById(String bookId) {
        return listOfBooks.stream()
                .filter(bookInfo -> bookInfo.getBookId().equals(bookId))
                .findFirst()
                .orElseThrow(() -> new BookIdException(bookId));
    }

    @Override
    public List<Book> getBookListByCategory(String category) {
        return listOfBooks.stream()
                .filter(book -> category.equalsIgnoreCase(book.getCategory()))
                .toList();
    }

    @Override
    public Set<Book> getBookListByFilter(Map<String, List<String>> filter) {
        Set<Book> booksByPublisher = new HashSet<>();
        Set<Book> booksByCategory = new HashSet<>();
        Set<String> booksByFilter = filter.keySet();

        if (booksByFilter.contains("publisher")) {
            for (String publisherName : filter.get("publisher")) {
                booksByPublisher = listOfBooks.stream()
                        .filter(book -> publisherName.equalsIgnoreCase(book.getPublisher()))
                        .collect(Collectors.toSet());
            }
        }
        if (booksByFilter.contains("category")) {
            filter.get("category").forEach(categoryName -> {
                List<Book> list = getBookListByCategory(categoryName);
                booksByCategory.addAll(list);
            });
        }

        booksByCategory.retainAll(booksByPublisher);
        return booksByCategory;
    }

    @Override
    public void setNewBook(Book book) {
        listOfBooks.add(book);
    }
}
