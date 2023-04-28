package com.bookkeeper.library.controller;

import com.bookkeeper.library.model.Book;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/api") // http://localhost:9092/api
public class BookController {


    // http://localhost:9092/api/books/
    @PostMapping("/books/")
    public Book createBook(@RequestBody Book body) {
        return body;
    }
}
