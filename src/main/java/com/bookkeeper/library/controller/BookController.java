package com.bookkeeper.library.controller;

import com.bookkeeper.library.model.Book;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/api") // http://localhost:9092/api
public class BookController {


    // http://localhost:9092/api/books/
    @PostMapping("/books/")
    public Book createBook(@RequestBody Book body) {
        return body;
    }

    // http://localhost:9092/api/books/
    @GetMapping(path = "/books/")
    public String getBooks() {
        return "get all books";
    }
}
