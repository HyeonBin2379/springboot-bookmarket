package org.project.springbootbookmarket.repository;

import java.util.List;
import org.project.springbootbookmarket.domain.Book;

public interface BookRepository {

    List<Book> getAllBookList();
}
