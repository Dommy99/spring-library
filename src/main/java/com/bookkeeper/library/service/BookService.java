package com.bookkeeper.library.service;

import com.bookkeeper.library.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BookService {
private BookRepository bookRepository;

    @Autowired
    public void  setBookRepository(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }
}
