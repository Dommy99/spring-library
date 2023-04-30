package com.bookkeeper.library.service;

import com.bookkeeper.library.exception.InformationExistException;
import com.bookkeeper.library.model.Author;
import com.bookkeeper.library.model.reponse.LoginResponse;
import com.bookkeeper.library.model.request.LoginRequest;
import com.bookkeeper.library.repository.AuthorRepository;
import com.bookkeeper.library.security.JWTUtils;
import com.bookkeeper.library.security.MyAuthorDetails;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthorService {

    private final AuthorRepository authorRepository;
    private final PasswordEncoder passwordEncoder;

    private AuthenticationManager authenticationManager;
    private MyAuthorDetails myAuthorDetails;
    private JWTUtils jwtUtils;


    public AuthorService(AuthorRepository authorRepository, PasswordEncoder passwordEncoder, AuthenticationManager authenticationManager, MyAuthorDetails myAuthorDetails, JWTUtils jwtUtils) {
        this.authorRepository = authorRepository;
        this.passwordEncoder = passwordEncoder;
        this.authenticationManager = authenticationManager;
        this.myAuthorDetails = myAuthorDetails;
        this.jwtUtils = jwtUtils;
    }

    /**
     *
     * @param authorObject
     * @return
     */
    public Author createAuthor(Author authorObject) {
        if (!authorRepository.existsByEmail(authorObject.getEmail())) {
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

    public ResponseEntity<?> loginUser(LoginRequest loginRequest) {
        try {
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(loginRequest.getEmail(), loginRequest.getPassword()));
            SecurityContextHolder.getContext().setAuthentication(authentication);
            myAuthorDetails = (MyAuthorDetails) authentication.getPrincipal();

            final String JWT = jwtUtils.generateJwtToken(myAuthorDetails);
            return ResponseEntity.ok(new LoginResponse(JWT));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(new LoginResponse("Error: username or password is incorrect"));
        }
    }
}
