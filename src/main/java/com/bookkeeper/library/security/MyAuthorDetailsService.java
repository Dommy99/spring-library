package com.bookkeeper.library.security;


import com.bookkeeper.library.model.Author;
import com.bookkeeper.library.service.AuthorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class MyAuthorDetailsService implements UserDetailsService {
    private AuthorService authorService;

    @Autowired
    public void setAuthorService(AuthorService authorService){
        this.authorService = authorService;
    }
    /**
     * Loads the UserDetails object for a given email.
     *
     * @param email the email of the user to load
     * @return the UserDetails object for the given email
     * @throws UsernameNotFoundException if the email is not found in the system
     */
    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Author author = authorService.findAuthorByEmail(email);
        return new MyAuthorDetails(author);
    }
}
