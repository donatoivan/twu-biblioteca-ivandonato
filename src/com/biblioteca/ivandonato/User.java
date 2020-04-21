package com.biblioteca.ivandonato;

import java.util.ArrayList;

public class User {
    private String id;
    private String password;
    public ArrayList<Book> myBooks;
    private String name;
    private String email;
    private String phoneNumber;

    public User(String id, String password, String name, String email, String phoneNumber) {
        this.id = id;
        this.password = password;
        this.myBooks = new ArrayList<>();
        this.name = name;
        this.email = email;
        this.phoneNumber = phoneNumber;
    }

    public String getUserId() {
        return id;
    }

    public String getPassword() {
        return password;
    }

    public ArrayList getMyBooks() { return myBooks; }

    public void addBookToUser(Book book) {
        myBooks.add(book);
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }
}