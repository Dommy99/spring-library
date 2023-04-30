package com.bookkeeper.library.repository;

import com.bookkeeper.library.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BookRepository extends JpaRepository<Book, Long> {
    Book findByName(String bookName);

    Book findByNameAndIdIsNot(String bookName, Long bookId);

    List<Book> findByGenre_Id(Long genreId);

//    List<Book> findByBookId(Long bookId);
}
