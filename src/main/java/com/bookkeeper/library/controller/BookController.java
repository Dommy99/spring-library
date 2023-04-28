package com.bookkeeper.library.controller;

import com.bookkeeper.library.model.Book;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/api") // http://localhost:9092/api
public class BookController {


    // http://localhost:9092/api/books/
    @PostMapping("/books")
    public Book createBook(@RequestBody Book body) {
        return body;
    }

    // http://localhost:9092/api/books/
    @GetMapping(path = "/books")
    public String getBooks() {
        return "get all books";
    }

    // http://localhost:9092/api/books/{bookId}
    @GetMapping(path = "/books/{bookId}")
    public String getBook(@PathVariable Long bookId) {
        return "getting the book with the id of " + bookId;
    }

    // http://localhost:9092/api/books/{bookId}
    @PutMapping("/books/{bookId}")
    public String updateBook(@PathVariable(value = "bookId") Long bookId, @RequestBody String body) {
        return "updating the book with the id of " + bookId + body;
    }
}
