package com.bookkeeper.library.security;

import com.bookkeeper.library.model.Author;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.HashSet;

public class MyAuthorDetails implements UserDetails {
    
    private final Author author;

    public MyAuthorDetails(Author author) {
        this.author = author;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return new HashSet<>();
    }

    @Override
    public String getPassword() {
        return author.getPassword();
    }

    @Override
    public String getUsername() {
        return author.getEmail();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    public Author getAuthor() {
        return author;
    }
}
