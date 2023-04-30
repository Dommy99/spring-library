package com.bookkeeper.library.service;

import com.bookkeeper.library.exception.InformationExistException;
import com.bookkeeper.library.model.Author;
import com.bookkeeper.library.repository.AuthorRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthorService {

    private final AuthorRepository authorRepository;
    private final PasswordEncoder passwordEncoder;

    public AuthorService(AuthorRepository authorRepository, PasswordEncoder passwordEncoder) {
        this.authorRepository = authorRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public Author createAuthor(Author authorObject) {
        System.out.println("service calling createUser ==>");
        if (!authorRepository.existsByEmailAddress(authorObject.getEmail())) {
            authorObject.setPassword(passwordEncoder.encode(authorObject.getPassword()));
            return authorRepository.save(authorObject);
        } else {
            throw new InformationExistException("author with the email address " + authorObject.getEmail() +
                    " already exists");
        }
    }
}
