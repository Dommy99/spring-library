package com.bookkeeper.library.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/api") // http://localhost:9092/api
public class BookController {


    // http://localhost:9092/api/books/
    @PostMapping("/books/")
    public String createBook(@RequestBody String body) {
        return "creating a book " + body;
    }
}
