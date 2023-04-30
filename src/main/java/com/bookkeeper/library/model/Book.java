package com.bookkeeper.library.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;

@Entity
@Table(name = "books")// specifies the table name for this entity in the database
public class Book {
    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.IDENTITY)// specifies that the primary key is generated automatically
    private Long id;
    @Column
    private String name;
    @Column
    private String description;

    @ManyToOne
    @JoinColumn(name = "author_id") // specifies the foreign key column name in the database
    @JsonIgnore // specifies that the author field should be ignored when serializing to JSON
    private Author author;

    @ManyToOne
    @JoinColumn(name = "genre_id")// specifies the foreign key column name in the database
    private Genre genre;

    public Book() {
    }
    /**
     * Constructor for Book
     *
     * @param id the book ID
     * @param name the book name
     * @param description the book description
     */
    public Book(Long id, String name, String description) {
        this.id = id;
        this.name = name;
        this.description = description;
    }
    // Getter and setter methods for all fields

    /**
     * Getter for the id field
     *
     * @return the id field value
     */

    public Long getId() {
        return id;
    }
    /**
     * Setter for the id field
     *
     * @param id the value to set for the id field
     */
    public void setId(Long id) {
        this.id = id;
    }
    /**
     * Getter for the name field
     *
     * @return the name field value
     */
    public String getName() {
        return name;
    }
    /**
     * Setter for the name field
     *
     * @param name the value to set for the name field
     */
    public void setName(String name) {
        this.name = name;
    }
    /**
     * Getter for the description field
     *
     * @return the description field value
     */
    public String getDescription() {
        return description;
    }
    /**
     * Setter for the description field
     *
     * @param description the value to set for the description field
     */
    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "Book{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
    /**
     * Getter for the author field
     *
     * @return the author field value
     */
    public Author getAuthor() {
        return author;
    }
    /**
     * Setter for the author field
     *
     * @param author the value to set for the author field
     */
    public void setAuthor(Author author) {
        this.author = author;
    }
    /**
     * Getter for the genre field
     *
     * @return the genre field value
     */
    public Genre getGenre() {
        return genre;
    }
    /**
     * Setter for the genre field
     *
     * @param genre the value to set for the genre field
     */
    public void setGenre(Genre genre) {
        this.genre = genre;
    }
}
