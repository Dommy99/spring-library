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
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class BookService {
private BookRepository bookRepository;

    @Autowired
    public void  setBookRepository(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    /**
     * Creates a new book.
     *
     * @param bookObject The book to be created.
     * @return The newly created book.
     * @throws InformationExistException If a book with the same name already exists.
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
     * Retrieves all books.
     *
     * @return A list of all books.
     */
    public List<Book> getBooks() {
        return bookRepository.findAll();
    }

    /**
     * Retrieves a book by its ID.
     *
     * @param bookId The ID of the book to be retrieved.
     * @return An Optional containing the book if found, otherwise an empty Optional.
     * @throws InformationNotFoundException If the book with the given ID is not found.
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
     * Updates a book's information.
     *
     * @param bookId The ID of the book to be updated.
     * @param bookObject The updated book information.
     * @return The updated book.
     * @throws InformationExistException If the updated book has the same name as the existing book.
     * @throws InformationNotFoundException If the book with the given ID is not found.
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
     * Deletes a book by its ID.
     *
     * @param bookId The ID of the book to be deleted.
     * @return An Optional containing the deleted book if found, otherwise an empty Optional.
     * @throws InformationNotFoundException If the book with the given ID is not found.
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
