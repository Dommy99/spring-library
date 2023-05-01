package com.bookkeeper.library.repository;

import com.bookkeeper.library.model.Book;
import com.bookkeeper.library.model.Genre;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface GenreRepository extends JpaRepository<Genre, Long>{
//    List<Book> findByBookId(Long genreId);

//    Genre findByNameAndAuthorIdAndIdIsNot(String genreName, Long authorId, Long genreId);
//
//    Genre findByNameAndAuthorId(String genreName, Long authorId);
Genre findByIdAndAuthorId(Long genreId, Long authorId);


}
