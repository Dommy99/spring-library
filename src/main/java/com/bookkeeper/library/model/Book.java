package com.bookkeeper.library.model;

public class Book {
    private Long id;
    private String name;
    private String description;

    public Book() {
    }

    public Book(Long id, String name, String description) {
        this.id = id;
        this.name = name;
        this.description = description;
    }
}
