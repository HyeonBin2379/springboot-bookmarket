package org.project.springbootbookmarket.service;

import java.util.List;
import org.project.springbootbookmarket.domain.Book;

public interface BookService {

    List<Book> getAllBookList();
    Book getBookById(String bookId);
    List<Book> getBookListByCategory(String category);
}
