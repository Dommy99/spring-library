package com.bookkeeper.library.repository;

import com.bookkeeper.library.model.Book;
import com.bookkeeper.library.model.Genre;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface GenreRepository extends JpaRepository<Genre, Long>{
//
//    Genre findByNameAndUserIdAndIdIsNot(String genreName, Long bookId, Long genreId);
//
//    Genre findByNameAndUserId(String genreName, Long genreId);
//    List<Genre> findByGenreId(Long genreId);
}
