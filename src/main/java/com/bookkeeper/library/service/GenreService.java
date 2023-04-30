package com.bookkeeper.library.service;

import com.bookkeeper.library.exception.InformationNotFoundException;
import com.bookkeeper.library.model.Book;
import com.bookkeeper.library.model.Genre;
import com.bookkeeper.library.repository.BookRepository;
import com.bookkeeper.library.repository.GenreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class GenreService {
    private GenreRepository genreRepository;
    private BookRepository bookRepository;
    @Autowired
    public GenreService(GenreRepository genreRepository, BookRepository bookRepository) {
        this.genreRepository = genreRepository;
        this.bookRepository = bookRepository;
    }

    /**
     * Creates a new genre for the specified book id.
     * @param bookId The id of the book for which the genre is created.
     * @param genreObject The genre object to be created.
     * @return The created genre object.
     * @throws InformationNotFoundException if the book with the specified id is not found.
     */
    public Genre createBookGenre(Long bookId, @RequestBody Genre genreObject) {
        Optional<Book> bookOptional = bookRepository.findById(bookId);

        if (bookOptional.isPresent()) {
            Book book = bookOptional.get();
            List<Book> bookList = new ArrayList<>();
            bookList.add(book);
            genreObject.setBookList(bookList);
            return genreRepository.save(genreObject);
        } else {
            throw new InformationNotFoundException("book with id " + bookId + " not found");
        }
    }




    /**
     * Returns a list of all books belonging to the genre with the specified id.
     * @param genreId The id of the genre to search for.
     * @return A list of all books belonging to the genre.
     * @throws InformationNotFoundException if the genre with the specified id is not found.
     */
    public List<Book> getAllBooksByGenreId(Long genreId) {
        if (genreRepository.existsById(genreId)) {
            return bookRepository.findByGenre_Id(genreId);
        } else {
            throw new InformationNotFoundException("genre with id " + genreId + " not found");
        }
    }

    /**
     * Deletes the genre with the specified id.
     * @param genreId The id of the genre to be deleted.
     * @throws InformationNotFoundException if the genre with the specified id is not found.
     */
    public void deleteGenre(Long genreId) {
        Optional<Genre> genreOptional = genreRepository.findById(genreId);

        if (genreOptional.isPresent()) {
            genreRepository.deleteById(genreId);
        } else {
            throw new InformationNotFoundException("genre with id " + genreId + " not found");
        }
    }




}
