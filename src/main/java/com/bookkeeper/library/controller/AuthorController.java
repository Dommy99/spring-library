package com.bookkeeper.library.controller;

import com.bookkeeper.library.model.Author;
import com.bookkeeper.library.model.request.LoginRequest;
import com.bookkeeper.library.service.AuthorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/auth/authors")
public class AuthorController {

    private AuthorService authorService;

    @Autowired
    public void setAuthorService(AuthorService authorService) {
        this.authorService = authorService;
    }

    // http://localhost:9092/auth/authors/register/
    @PostMapping("/register/")
    public Author createUser(@RequestBody Author authorObject) {
        return authorService.createAuthor(authorObject);
    }

    // http://localhost:9092/auth/authors/login/
    @PostMapping("/login/")
    public ResponseEntity<?> loginUser(@RequestBody LoginRequest loginRequest) {
        return authorService.loginUser(loginRequest);
    }
}
