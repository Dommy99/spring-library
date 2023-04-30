package com.bookkeeper.library.controller;

import com.bookkeeper.library.exception.InformationExistException;
import com.bookkeeper.library.model.Book;
import com.bookkeeper.library.model.Genre;
import com.bookkeeper.library.repository.BookRepository;
import com.bookkeeper.library.repository.GenreRepository;
import com.bookkeeper.library.service.BookService;
import com.bookkeeper.library.service.GenreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/api") // http://localhost:9092/api
public class GenreController {

    private BookRepository bookRepository;
    private GenreRepository genreRepository;
    private GenreService genreService;

    @Autowired
    public void setBookRepository(BookRepository bookRepository){
        this.bookRepository = bookRepository;
    }

    @Autowired
    public void setGenreRepository(GenreRepository genreRepository){
        this.genreRepository = genreRepository;
    }

    @Autowired
    public void setGenreService(GenreService genreService){
        this.genreService = genreService;
    }



    // http://localhost:9092/api/books/{bookId}/genre
    @PostMapping("/books/{bookId}/genre")
    public Genre createBookGenre(@PathVariable(value = "bookId") Long bookId, @RequestBody Genre genreObject) {
        return genreService.createBookGenre(bookId, genreObject);
    }

    @GetMapping("/books/{bookId}/genre")
    public List<Genre> getBookGenre(@PathVariable(value = "bookId") Long bookId) {

        return genreService.getBookGenre(bookId);
    }
}
