package com.bookkeeper.library.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "authors")// specifies the table name for this entity in the database
public class Author {
    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.IDENTITY) // specifies that the primary key is generated automatically
    private Long id;

    @Column
    private String name;

    @Column
    private String userName;

    @Column(unique = true) // specifies that the email field must be unique in the database
    private String email;

    @Column
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY) // specifies that the password field should not be read when deserializing JSON
    private String password;

    @OneToMany(mappedBy = "author") // specifies that the Book entity has a ManyToOne relationship with the Author entity
    @LazyCollection(LazyCollectionOption.FALSE) // specifies that the bookList field should be eagerly loaded
    private List<Book> bookList;

    public Author() {
    }
    /**
     * Constructor for Author
     *
     * @param id the author ID
     * @param name the author name
     * @param userName the author username
     * @param email the author email
     * @param password the author password
     */
    public Author(Long id, String name, String userName, String email, String password) {
        this.id = id;
        this.name = name;
        this.userName = userName;
        this.email = email;
        this.password = password;
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
     * Getter for the userName field
     *
     * @return the userName field value
     */
    public String getUserName() {
        return userName;
    }
    /**
     * Setter for the userName field
     *
     * @param userName the value to set for the userName field
     */
    public void setUserName(String userName) {
        this.userName = userName;
    }
    /**
     * Getter for the email field
     *
     * @return the email field value
     */
    public String getEmail() {
        return email;
    }
    /**
     * Setter for the email field
     *
     * @param email the value to set for the email field
     */
    public void setEmail(String email) {
        this.email = email;
    }
    /**
     * Getter for the password field
     *
     * @return the password field value
     */
    public String getPassword() {
        return password;
    }
    /**
     * Setter for the password field
     *
     * @param password the value to set for the password field
     */
    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "Author{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", userName='" + userName + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                '}';
    }

    public List<Book> getBookList() {
        return bookList;
    }

    public void setBookList(List<Book> bookList) {
        this.bookList = bookList;
    }


}
