package com.biblioteca.ivandonato;

public class User {
    private String id;
    private String password;

    public User(String id, String password) {
        this.id = id;
        this.password = password;
    }

    public String getUserId() {
        return id;
    }

    public String getPassword() {
        return password;
    }
}