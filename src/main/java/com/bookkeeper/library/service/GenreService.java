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
    public void setBookRepository(BookRepository bookRepository){
        this.bookRepository = bookRepository;
    }

    @Autowired
    public void setGenreRepository(GenreRepository genreRepository){
        this.genreRepository = genreRepository;
    }

    /**
     *
     * @param bookId
     * @param genreObject
     * @return
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

//    public List<Genre> getBookGenre(Long bookId){
//        return
//    }

    public List<Book> getAllBooksByGenreId(Long genreId) {
        if (genreRepository.existsById(genreId)) {
            return bookRepository.findByGenre_Id(genreId);
        } else {
            throw new InformationNotFoundException("genre with id " + genreId + " not found");
        }
    }

//    public void deleteBookGenre(Long bookId, Long genreId) {
//        try {
//            List<Book> books = bookRepository.findByGenre_Id(genreId);
//            Optional<Book> book = books.stream().filter(p -> p.getId().equals(bookId)).findFirst();
//
//            if (book.isPresent()) {
//                Genre genre = book.get().getGenre();
//                genreRepository.deleteById(genre.getId());
//            } else {
//                throw new InformationNotFoundException("book with id " + bookId + " not found");
//            }
//        } catch (NoSuchElementException e) {
//            throw new InformationNotFoundException("genre with id " + genreId + " not found");
//        }
//    }


}
