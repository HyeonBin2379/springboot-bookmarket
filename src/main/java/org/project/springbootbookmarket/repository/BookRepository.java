package org.project.springbootbookmarket.repository;

import java.util.List;
import java.util.Map;
import java.util.Set;
import org.project.springbootbookmarket.domain.Book;

public interface BookRepository {

    List<Book> getAllBookList();
    Book getBookById(String bookId);
    List<Book> getBookListByCategory(String category);
    Set<Book> getBookListByFilter(Map<String, List<String>> filter);
    void setNewBook(Book book);
}
