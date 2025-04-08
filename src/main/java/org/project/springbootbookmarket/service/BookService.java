package org.project.springbootbookmarket.service;

import java.util.List;
import java.util.Map;
import java.util.Set;
import org.project.springbootbookmarket.domain.Book;

public interface BookService {

    List<Book> getAllBookList();
    Book getBookById(String bookId);
    List<Book> getBookListByCategory(String category);
    Set<Book> getBookListByFilter(Map<String, List<String>> filter);
}
