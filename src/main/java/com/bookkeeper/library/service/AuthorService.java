package com.bookkeeper.library.service;

import com.bookkeeper.library.exception.InformationExistException;
import com.bookkeeper.library.model.Author;
import com.bookkeeper.library.repository.AuthorRepository;
import com.bookkeeper.library.security.MyAuthorDetails;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthorService {

    private final AuthorRepository authorRepository;
    private final PasswordEncoder passwordEncoder;

    private AuthenticationManager authenticationManager;
    private MyAuthorDetails myAuthorDetails;


    public AuthorService(AuthorRepository authorRepository, PasswordEncoder passwordEncoder, AuthenticationManager authenticationManager, MyAuthorDetails myAuthorDetails) {
        this.authorRepository = authorRepository;
        this.passwordEncoder = passwordEncoder;
        this.authenticationManager = authenticationManager;
        this.myAuthorDetails = myAuthorDetails;
    }

    /**
     *
     * @param authorObject
     * @return
     */
    public Author createAuthor(Author authorObject) {
        if (!authorRepository.existsByEmailAddress(authorObject.getEmail())) {
            authorObject.setPassword(passwordEncoder.encode(authorObject.getPassword()));
            return authorRepository.save(authorObject);
        } else {
            throw new InformationExistException("author with the email address " + authorObject.getEmail() +
                    " already exists");
        }
    }


    /**
     *
     * @param email
     * @return
     */
    public Author findAuthorByEmail(String email) {
        return authorRepository.findAuthorByEmail(email);
    }


}
