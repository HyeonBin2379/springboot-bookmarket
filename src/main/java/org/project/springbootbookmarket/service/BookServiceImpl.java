package org.project.springbootbookmarket.service;

import java.util.List;
import org.project.springbootbookmarket.domain.Book;
import org.project.springbootbookmarket.repository.BookRepository;
import org.springframework.stereotype.Service;

@Service
public class BookServiceImpl implements BookService {

    private final BookRepository bookRepository;

    public BookServiceImpl(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    @Override
    public List<Book> getAllBookList() {
        return bookRepository.getAllBookList();
    }
}
