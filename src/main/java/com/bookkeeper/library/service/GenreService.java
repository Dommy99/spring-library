package com.bookkeeper.library.service;

import com.bookkeeper.library.exception.InformationExistException;
import com.bookkeeper.library.exception.InformationNotFoundException;
import com.bookkeeper.library.model.Author;
import com.bookkeeper.library.model.Book;
import com.bookkeeper.library.model.Genre;
import com.bookkeeper.library.repository.BookRepository;
import com.bookkeeper.library.repository.GenreRepository;
import com.bookkeeper.library.security.MyAuthorDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.ArrayList;
import java.util.List;
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
    public static Author getCurrentLoggedInAuthor() {
        MyAuthorDetails authorDetails = (MyAuthorDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return authorDetails.getAuthor();
    }

    /**
     * Creates a new genre for the specified book id.
     * @param bookId The id of the book for which the genre is created.
     * @param genreObject The genre object to be created.
     * @return The created genre object.
     * @throws InformationNotFoundException if the book with the specified id is not found.
     */
    public Genre createBookGenre(Long bookId, @RequestBody Genre genreObject) {
        Author currentAuthor = getCurrentLoggedInAuthor();
        Optional<Book> bookOptional = Optional.ofNullable(bookRepository.findByIdAndAuthorId(bookId, currentAuthor.getId()));

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
    public Book createBook(Long genreId, Book bookObject) {
        Book book = bookRepository.findByAuthorIdAndName(BookService.getCurrentLoggedInAuthor().getId(), bookObject.getName());
        if (book != null) {
            throw new InformationExistException("Book with that name already exists.");
        } else {

            bookObject.setAuthor(getCurrentLoggedInAuthor());
            return bookRepository.save(bookObject);
        }
    }



    /**
     * Returns a list of all books belonging to the genre with the specified id.
     *
     * @param genreId The id of the genre to search for.
     * @return A list of all books belonging to the genre.
     * @throws InformationNotFoundException if the genre with the specified id is not found.
     */

    public Optional<Genre> getAllBooksByGenreId(Long genreId) {
        Author currentAuthor = getCurrentLoggedInAuthor();
        Optional<Genre> genreList = Optional.ofNullable(genreRepository.findByIdAndAuthorId(genreId, currentAuthor.getId()));

        if (genreList.isEmpty()) {
            throw new InformationNotFoundException("genre with id " + genreId + " not found for author with id " );
        } else {
            return genreList;
        }
    }




    /**
     * Deletes the genre with the specified id.
     * @param genreId The id of the genre to be deleted.
     * @throws InformationNotFoundException if the genre with the specified id is not found.
     */
    public void deleteGenre(Long genreId) {
        Author currentAuthor = getCurrentLoggedInAuthor();
        Optional<Genre> genreOptional = Optional.ofNullable(genreRepository.findByIdAndAuthorId(genreId, currentAuthor.getId()));

        if (genreOptional.isPresent()) {
            genreRepository.deleteById(genreId);
        } else {
            throw new InformationNotFoundException("genre with id " + genreId + " not found");
        }
    }




}
