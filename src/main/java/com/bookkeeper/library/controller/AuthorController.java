package com.bookkeeper.library.controller;

import com.bookkeeper.library.service.AuthorService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/auth/authors")
public class AuthorController {

    private AuthorService authorService;


}
