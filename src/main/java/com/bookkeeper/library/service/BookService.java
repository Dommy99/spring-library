package com.bookkeeper.library.service;

import com.bookkeeper.library.exception.InformationExistException;
import com.bookkeeper.library.model.Book;
import com.bookkeeper.library.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@Service
public class BookService {
private BookRepository bookRepository;

    @Autowired
    public void  setBookRepository(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    public Book createBook(@RequestBody Book bookObject) {

        Book book = bookRepository.findByName(bookObject.getName());
        if (book != null) {
            throw new InformationExistException("A book with name " + book.getName() + " already exists");
        } else {
            return bookRepository.save(bookObject);
        }
    }

    public List<Book> getBooks() {
        return bookRepository.findAll();
    }
}
