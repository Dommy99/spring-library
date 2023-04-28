package com.bookkeeper.library.controller;

import com.bookkeeper.library.exception.InformationExistException;
import com.bookkeeper.library.exception.InformationNotFoundException;
import com.bookkeeper.library.model.Book;
import com.bookkeeper.library.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(path = "/api") // http://localhost:9092/api
public class BookController {

    private BookRepository bookRepository;

    @Autowired
    public void setBookRepository(BookRepository bookRepository){
        this.bookRepository = bookRepository;
    }



    // http://localhost:9092/api/books/
    @PostMapping("/books")
    public Book createBook(@RequestBody Book bookObject) {

        Book book = bookRepository.findByName(bookObject.getName());
        if (book != null) {
            throw new InformationExistException("A book with name " + book.getName() + " already exists");
        } else {
            return bookRepository.save(bookObject);
        }
    }

    // http://localhost:9092/api/books/
    @GetMapping(path = "/books")
    public List<Book> getBooks() {
        return bookRepository.findAll();
    }

    // http://localhost:9092/api/books/{bookId}
    @GetMapping(path = "/books/{bookId}")
    public Optional<Book> getCategory(@PathVariable Long bookId) {
        Optional<Book> book = bookRepository.findById(bookId);
        if (book.isPresent()) {
            return book;
        } else {
            throw new InformationNotFoundException("book with id " + bookId + " not found");
        }
    }

    // http://localhost:9092/api/books/{bookId}
    @PutMapping("/books/{bookId}")
    public String updateBook(@PathVariable(value = "bookId") Long bookId, @RequestBody String body) {
        return "updating the book with the id of " + bookId + body;
    }

    // http://localhost:9092/api/books/{bookId}
    @DeleteMapping("/books/{bookId}")
    public String deleteBook(@PathVariable(value = "bookId") Long bookId) {
        return "deleting the book with the id of " + bookId;
    }
}
