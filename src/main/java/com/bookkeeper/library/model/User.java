package com.bookkeeper.library.model;

public class User {
    private Long id;
    private String name;
    private String userName;
    private String email;
    private String password;

    public User() {
    }

    public User(Long id, String name, String userName, String email, String password) {
        this.id = id;
        this.name = name;
        this.userName = userName;
        this.email = email;
        this.password = password;
    }
}
