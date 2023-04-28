package com.bookkeeper.library.controller;


import com.bookkeeper.library.model.Book;
import com.bookkeeper.library.model.Genre;
import com.bookkeeper.library.repository.BookRepository;
import com.bookkeeper.library.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(path = "/api") // http://localhost:9092/api
public class BookController {

    private BookRepository bookRepository;
    private BookService bookService;

    @Autowired
    public void setBookRepository(BookRepository bookRepository){
        this.bookRepository = bookRepository;
    }

    @Autowired
    public void setBookService(BookService bookService){
        this.bookService = bookService;
    }


    // http://localhost:9092/api/books/
    @PostMapping("/books")
    public Book createBook(@RequestBody Book bookObject) {

        return bookService.createBook(bookObject);
    }

    // http://localhost:9092/api/books/
    @GetMapping(path = "/books")
    public List<Book> getBooks() {
        return bookService.getBooks();
    }

    // http://localhost:9092/api/books/{bookId}
    @GetMapping(path = "/books/{bookId}")
    public Optional<Book> getBook(@PathVariable Long bookId) {
        return bookService.getBook(bookId);
    }

    // http://localhost:9092/api/books/{bookId}
    @PutMapping("/books/{bookId}")
    public Book updateBook(@PathVariable(value = "bookId") Long bookId, @RequestBody Book bookObject) {

        return bookService.updateBook(bookId, bookObject);
    }


    // http://localhost:9092/api/books/{bookId}
    @DeleteMapping("/books/{bookId}")
    public Optional<Book> deleteBook(@PathVariable(value = "bookId") Long bookId) {

        return bookService.deleteBook(bookId);
    }

}
