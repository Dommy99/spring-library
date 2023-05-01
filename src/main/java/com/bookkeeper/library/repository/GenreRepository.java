package com.bookkeeper.library.repository;

import com.bookkeeper.library.model.Genre;
import org.springframework.data.jpa.repository.JpaRepository;



public interface GenreRepository extends JpaRepository<Genre, Long>{

Genre findByIdAndAuthorId(Long genreId, Long authorId);


}
