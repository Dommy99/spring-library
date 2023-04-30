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

    /**
     * Setter method for AuthorService
     *
     * @param authorService the author service to set
     */
    @Autowired
    public void setAuthorService(AuthorService authorService) {
        this.authorService = authorService;
    }

    /**
     * Endpoint for creating a new author
     *
     * @param authorObject the author object to create
     * @return the created author object
     */
    // http://localhost:9092/auth/authors/register/
    @PostMapping("/register/")
    public Author createUser(@RequestBody Author authorObject) {
        return authorService.createAuthor(authorObject);
    }

    /**
     * Endpoint for logging in an author
     *
     * @param loginRequest the login request object
     * @return the response entity containing the logged in author object or an error message
     */
    // http://localhost:9092/auth/authors/login/
    @PostMapping("/login/")
    public ResponseEntity<?> loginUser(@RequestBody LoginRequest loginRequest) {
        return authorService.loginUser(loginRequest);
    }
}
