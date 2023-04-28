package com.bookkeeper.library.service;

import com.bookkeeper.library.exception.InformationExistException;
import com.bookkeeper.library.exception.InformationNotFoundException;
import com.bookkeeper.library.model.Book;
import com.bookkeeper.library.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
import java.util.Optional;

@Service
public class BookService {
private BookRepository bookRepository;

    @Autowired
    public void  setBookRepository(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    /**
     *
     * @param bookObject
     * @return
     */
    public Book createBook(@RequestBody Book bookObject) {

        Book book = bookRepository.findByName(bookObject.getName());
        if (book != null) {
            throw new InformationExistException("A book with name " + book.getName() + " already exists");
        } else {
            return bookRepository.save(bookObject);
        }
    }

    /**
     *
     *
     * @return
     */
    public List<Book> getBooks() {
        return bookRepository.findAll();
    }

    /**
     *
     * @param bookId
     * @return
     */
    public Optional<Book> getBook(@PathVariable Long bookId) {
        Optional<Book> book = bookRepository.findById(bookId);
        if (book.isPresent()) {
            return book;
        } else {
            throw new InformationNotFoundException("book with id " + bookId + " not found");
        }
    }

    /**
     *
     * @param bookId
     * @param bookObject
     * @return
     */
    public Book updateBook(@PathVariable(value = "bookId") Long bookId, @RequestBody Book bookObject) {

        Optional<Book> book = bookRepository.findById(bookId);
        if (book.isPresent()) {
            if (bookObject.getName().equals(book.get().getName())) {
                throw new InformationExistException("book " + book.get().getName() + " already exists");
            } else {
                Book updateBook = bookRepository.findById(bookId).get();
                updateBook.setName(bookObject.getName());
                updateBook.setDescription(bookObject.getDescription());
                return bookRepository.save(updateBook);
            }
        } else {
            throw new InformationNotFoundException("book with id " + bookId + " not found");
        }
    }

    /**
     *
     * @param bookId
     * @return
     */
    public Optional<Book> deleteBook(@PathVariable(value = "bookId") Long bookId) {

        Optional<Book> book = bookRepository.findById(bookId);

        if (book.isPresent()) {
            bookRepository.deleteById(bookId);
            return book;
        } else {
            throw new InformationNotFoundException("book with id " + bookId + " not found");
        }
    }
}
