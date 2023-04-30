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

    /**
     * Constructor for the AuthorService class.
     *
     * @param authorRepository the AuthorRepository object
     * @param passwordEncoder the PasswordEncoder object
     * @param authenticationManager the AuthenticationManager object
     * @param myAuthorDetails the MyAuthorDetails object
     * @param jwtUtils the JWTUtils object
     */
    public AuthorService(AuthorRepository authorRepository, PasswordEncoder passwordEncoder, AuthenticationManager authenticationManager, MyAuthorDetails myAuthorDetails, JWTUtils jwtUtils) {
        this.authorRepository = authorRepository;
        this.passwordEncoder = passwordEncoder;
        this.authenticationManager = authenticationManager;
        this.myAuthorDetails = myAuthorDetails;
        this.jwtUtils = jwtUtils;
    }

    /**
     * Creates a new author in the database.
     *
     * @param authorObject the Author object to be saved
     * @return the saved Author object
     * @throws InformationExistException if an author with the same email address already exists
     */
    public Author createAuthor(Author authorObject) {
        // Check if an author with the same email address already exists
        if (!authorRepository.existsByEmail(authorObject.getEmail())) {
            // Encode the author's password before saving
            authorObject.setPassword(passwordEncoder.encode(authorObject.getPassword()));
            // Save the author object in the repository
            return authorRepository.save(authorObject);
        } else {
            throw new InformationExistException("author with the email address " + authorObject.getEmail() +
                    " already exists");
        }
    }


    /**
     * Finds an author by their email address.
     *
     * @param email the email address of the author
     * @return the found Author object or null if not found
     */
    public Author findAuthorByEmail(String email) {
        return authorRepository.findAuthorByEmail(email);
    }

    /**
     * Authenticates a user and generates a JWT token if the authentication is successful.
     *
     * @param loginRequest the LoginRequest object containing the user's email and password
     * @return a ResponseEntity object with either the generated JWT token or an error message
     */
    public ResponseEntity<?> loginUser(LoginRequest loginRequest) {
        try {
            // Authenticate the user with the provided email and password
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(loginRequest.getEmail(), loginRequest.getPassword()));
            // Set the authenticated user in the security context
            SecurityContextHolder.getContext().setAuthentication(authentication);
            // Get the authenticated user details
            myAuthorDetails = (MyAuthorDetails) authentication.getPrincipal();

            // Generate a JWT token for the authenticated user
            final String JWT = jwtUtils.generateJwtToken(myAuthorDetails);
            // Return the generated JWT token in the response
            return ResponseEntity.ok(new LoginResponse(JWT));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(new LoginResponse("Error: username or password is incorrect"));
        }
    }
}
