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
    /**
     * Returns an empty set of GrantedAuthority as the author doesn't have any authorities.
     * @return Empty set of GrantedAuthority.
     */
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return new HashSet<>();
    }
    /**
     * Returns the author's password.
     * @return Author's password.
     */
    @Override
    public String getPassword() {
        return author.getPassword();
    }
    /**
     * Returns the author's email as the username.
     * @return Author's email.
     */
    @Override
    public String getUsername() {
        return author.getEmail();
    }
    /**
     * Returns true as the author's account never expires.
     * @return True.
     */
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }
    /**
     * Returns true as the author's account is never locked.
     * @return True.
     */
    @Override
    public boolean isAccountNonLocked() {
        return true;
    }
    /**
     * Returns true as the author's credentials never expire.
     * @return True.
     */
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }
    /**
     * Returns true as the author's account is always enabled.
     * @return True.
     */
    @Override
    public boolean isEnabled() {
        return true;
    }
    /**
     * Returns the author associated with this MyAuthorDetails.
     * @return Author object.
     */
    public Author getAuthor() {
        return author;
    }
}
