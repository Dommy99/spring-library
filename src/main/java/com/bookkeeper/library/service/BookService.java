package com.bookkeeper.library.service;

import com.bookkeeper.library.exception.InformationExistException;
import com.bookkeeper.library.exception.InformationNotFoundException;
import com.bookkeeper.library.model.Author;
import com.bookkeeper.library.model.Book;
import com.bookkeeper.library.repository.BookRepository;
import com.bookkeeper.library.security.MyAuthorDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;


import java.util.List;
import java.util.Optional;

@Service
public class BookService {
private BookRepository bookRepository;

    @Autowired
    public void  setBookRepository(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }
    public static Author getCurrentLoggedInAuthor() {
        MyAuthorDetails authorDetails = (MyAuthorDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return authorDetails.getAuthor();
    }
    /**
     * Creates a new book.
     *
     * @param bookObject The book to be created.
     * @return The newly created book.
     * @throws InformationExistException If a book with the same name already exists.
     */

    public Book createBook(Book bookObject) {
        Book book = bookRepository.findByAuthorIdAndName(BookService.getCurrentLoggedInAuthor().getId(), bookObject.getName());
        if (book != null) {
            throw new InformationExistException("Book with that name already exists.");
        } else {

            bookObject.setAuthor(getCurrentLoggedInAuthor());
            return bookRepository.save(bookObject);
        }
    }
    /**
     * Retrieves all books.
     *
     * @return A list of all books.
     */
    public List<Book> getBooks() {
        List<Book> books = bookRepository.findByAuthorId(BookService.getCurrentLoggedInAuthor().getId());
        if (books.isEmpty()) {
            throw new InformationNotFoundException("no books found for author id " + BookService.getCurrentLoggedInAuthor().getId());
        } else {
            return books;
        }
    }
    /**
     * Retrieves a book by its ID.
     *
     * @param bookId The ID of the book to be retrieved.
     * @return An Optional containing the book if found, otherwise an empty Optional.
     * @throws InformationNotFoundException If the book with the given ID is not found.
     */

    public Optional<Book> getBook(Long bookId) {
        Book book = bookRepository.findByIdAndAuthorId(bookId, BookService.getCurrentLoggedInAuthor().getId());
        if (book == null) {
            throw new InformationNotFoundException("book with id " + bookId + " not found");
        } else {
            return Optional.of(book);
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

    public Book updateBook(Long bookId, Book bookObject) {
        Book book = bookRepository.findByIdAndAuthorId(bookId, BookService.getCurrentLoggedInAuthor().getId());
        if (book != null) {
            if (bookObject.getName().equals(book.getName())) {
                throw new InformationExistException("Book" + book.getName() + " is already exists");
            } else {
                Book updateBook = bookRepository.findByIdAndAuthorId(bookId, BookService.getCurrentLoggedInAuthor().getId());
                updateBook.setName(bookObject.getName());
                updateBook.setDescription(bookObject.getDescription());
                return bookRepository.save(updateBook);
            }
        } else {
            throw new InformationNotFoundException("Book with id: " + bookId + "not found");
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
