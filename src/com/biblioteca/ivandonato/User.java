package com.biblioteca.ivandonato;

import java.util.ArrayList;

public class User {
    private String id;
    private String password;
    public ArrayList<Book> myBooks;

    public User(String id, String password) {
        this.id = id;
        this.password = password;
        this.myBooks = new ArrayList<>();
    }

    public String getUserId() {
        return id;
    }

    public String getPassword() {
        return password;
    }

    public void addBookToUser(Book book) {
        myBooks.add(book);
    }
}