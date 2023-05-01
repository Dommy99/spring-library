package com.bookkeeper.library.repository;

import com.bookkeeper.library.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BookRepository extends JpaRepository<Book, Long> {
    Book findByName(String bookName);
    List<Book> findByAuthorId(Long authorId);
    Book findByIdAndAuthorId(Long bookId, Long authorId);


    Book findByAuthorIdAndName(Long authorId, String bookName);
//    List<Book> findByGenre_Id(Long genreId);

}
