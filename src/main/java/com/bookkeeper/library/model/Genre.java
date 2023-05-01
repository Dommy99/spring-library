package com.bookkeeper.library.model;

import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "genres")// SQL table name
public class Genre {
    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.IDENTITY)//primary key
    private Long id;
    @Column
    private String genreName;

    @OneToMany(mappedBy = "genre")
    @LazyCollection(LazyCollectionOption.FALSE)
    private List<Book> bookList;


    public Genre() {
    }
    /**
     * Constructs a Genre object with the given id, genre name, and book list.
     *
     * @param id        the id of the genre.
     * @param genreName the name of the genre.
     * @param bookList  the list of books that belong to this genre.
     */
    public Genre(Long id, String genreName, List<Book> bookList) {
        this.id = id;
        this.genreName = genreName;
        this.bookList = bookList;
    }
    /**
     * Returns the id of the genre.
     *
     * @return the id of the genre.
     */
    public Long getId() {
        return id;
    }
    /**
     * Sets the id of the genre.
     *
     * @param id the id of the genre.
     */
    public void setId(Long id) {
        this.id = id;
    }
    /**
     * Returns the name of the genre.
     *
     * @return the name of the genre.
     */
    public String getGenreName() {
        return genreName;
    }
    /**
     * Sets the name of the genre.
     *
     * @param genreName the name of the genre.
     */
    public void setGenreName(String genreName) {
        this.genreName = genreName;
    }

    @Override
    public String toString() {
        return "Genre{" +
                "id=" + id +
                ", genreName='" + genreName + '\'' +
                '}';
    }
    /**
     * Returns the list of books that belong to this genre.
     *
     * @return the list of books that belong to this genre.
     */
    public List<Book> getBookList() {
        return bookList;
    }
    /**
     * Sets the list of books that belong to this genre.
     *
     * @param bookList the list of books that belong to this genre.
     */
    public void setBookList(List<Book> bookList) {
        this.bookList = bookList;
    }

}
