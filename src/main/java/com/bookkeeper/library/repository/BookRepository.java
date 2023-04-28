package com.bookkeeper.library.repository;

import com.bookkeeper.library.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookRepository extends JpaRepository<Book, Long> {
    Book findByName(String bookName);
}
