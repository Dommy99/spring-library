package com.bookkeeper.library.repository;

import com.bookkeeper.library.model.Author;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AuthorRepository  extends JpaRepository<Author, Long> {
    // to register
    boolean existsByEmailAddress(String userEmailAddress);

    // to login
    Author findUserByEmailAddress(String userEmailAddress);
}
