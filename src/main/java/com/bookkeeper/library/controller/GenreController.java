package com.bookkeeper.library.controller;

import com.bookkeeper.library.exception.InformationExistException;
import com.bookkeeper.library.model.Book;
import com.bookkeeper.library.model.Genre;
import com.bookkeeper.library.repository.BookRepository;
import com.bookkeeper.library.repository.GenreRepository;
import com.bookkeeper.library.service.BookService;
import com.bookkeeper.library.service.GenreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
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


    // http://localhost:9092/api/genres/{genreId}/books
    @GetMapping("/genres/{genreId}/books")
    public List<Book> getAllBooksByGenreId(@PathVariable(value = "genreId") Long genreId) {
        return genreService.getAllBooksByGenreId(genreId);
    }

    // http://localhost:9092/api/genres/{genreId}
    @DeleteMapping("/genres/{genreId}")
    public ResponseEntity<HashMap<String, String>> deleteGenreById(@PathVariable(value = "genreId") Long genreId) {
        genreService.deleteGenre(genreId);
        HashMap<String, String> responseMessage = new HashMap<>();
        responseMessage.put("status", "genre with id: " + genreId + " was successfully deleted.");
        return new ResponseEntity<>(responseMessage, HttpStatus.OK);
    }


}
