package com.bookkeeper.library.controller;


import com.bookkeeper.library.model.Book;
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

    /**
     * Endpoint for creating a new book
     *
     * @param bookObject the book object to create
     * @return the created book object
     */
    // http://localhost:9092/api/books
    @PostMapping(path = "/books")
    public Book createBook(@RequestBody Book bookObject) {

        return bookService.createBook(bookObject);
    }
    /**
     * Endpoint for retrieving all books
     *
     * @return a list of all books
     */
    // http://localhost:9092/api/books
    @GetMapping(path = "/books")
    public List<Book> getBooks() {
        return bookService.getBooks();
    }
    /**
     * Endpoint for retrieving a single book by ID
     *
     * @param bookId the ID of the book to retrieve
     * @return the optional book object with the given ID
     */
    // http://localhost:9092/api/books/{bookId}
    @GetMapping(path = "/books/{bookId}")
    public Optional<Book> getBook(@PathVariable Long bookId) {
        return bookService.getBook(bookId);
    }
    /**
     * Endpoint for updating a single book by ID
     *
     * @param bookId the ID of the book to update
     * @param bookObject the updated book object
     * @return the updated book object
     */
    // http://localhost:9092/api/books/{bookId}
    @PutMapping(path ="/books/{bookId}")
    public Book updateBook(@PathVariable(value = "bookId") Long bookId, @RequestBody Book bookObject) {

        return bookService.updateBook(bookId, bookObject);
    }

    /**
     * Endpoint for deleting a single book by ID
     *
     * @param bookId the ID of the book to delete
     * @return the optional book object that was deleted
     */
    // http://localhost:9092/api/books/{bookId}
    @DeleteMapping(path ="/books/{bookId}")
    public Optional<Book> deleteBook(@PathVariable(value = "bookId") Long bookId) {

        return bookService.deleteBook(bookId);
    }

}
