package com.bookkeeper.library.controller;

import com.bookkeeper.library.model.Book;
import com.bookkeeper.library.model.Genre;
import com.bookkeeper.library.repository.BookRepository;
import com.bookkeeper.library.repository.GenreRepository;
import com.bookkeeper.library.service.GenreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Optional;

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


    /**
     * Endpoint for creating a new genre for a book
     *
     * @param bookId the ID of the book to add the genre to
     * @param genreObject the genre object to add to the book
     * @return the created genre object
     */
    // http://localhost:9092/api/books/{bookId}/genre
    @PostMapping("/books/{bookId}/genre")
    public Genre createBookGenre(@PathVariable(value = "bookId") Long bookId, @RequestBody Genre genreObject) {
        return genreService.createBookGenre(bookId, genreObject);
    }

    /**
     * Endpoint for creating a new book
     * @param genreId
     * @param bookObject
     * @return
     */
//     http://localhost:9092/api/genres/{genreId}/books
    @PostMapping("/genres/{genreId}/books")
    public Book createBook(@PathVariable(value = "genreId") Long genreId, @RequestBody Book bookObject) {
        return genreService.createBook(genreId, bookObject);
    }

    /**
     * Endpoint for retrieving all books with a given genre ID
     *
     * @param genreId the ID of the genre to retrieve books for
     * @return a list of all books with the given genre ID
     */
    // http://localhost:9092/api/genres/{genreId}/books
    @GetMapping("/genres/{genreId}/books")
    public Optional<Genre> getAllBooksByGenreId(@PathVariable(value = "genreId") Long genreId) {
        return genreService.getAllBooksByGenreId(genreId);
    }



    /**
     * Endpoint for deleting a genre by ID
     *
     * @param genreId the ID of the genre to delete
     * @return a response entity with a status message indicating whether the delete was successful
     */
    // http://localhost:9092/api/genres/{genreId}
    @DeleteMapping("/genres/{genreId}")
    public ResponseEntity<HashMap<String, String>> deleteGenreById(@PathVariable(value = "genreId") Long genreId) {
        genreService.deleteGenre(genreId);
        HashMap<String, String> responseMessage = new HashMap<>();
        responseMessage.put("status", "genre with id: " + genreId + " was successfully deleted.");
        return new ResponseEntity<>(responseMessage, HttpStatus.OK);
    }


}
