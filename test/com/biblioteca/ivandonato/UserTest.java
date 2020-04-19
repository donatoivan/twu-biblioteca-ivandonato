package com.biblioteca.ivandonato;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class UserTest {
    public Book book;

    @Before
    public void initialise() {
        book = new Book("Waiting for Godot", "Samuel Beckett", "1963");
    }

    @Test
    public void shouldGetUserId() {
        User user = new User("1234567", "password123");

        String id = user.getUserId();

        assertEquals("1234567", id);
    }

    @Test
    public void shouldGetPassword() {
        User user = new User("1234567", "password123");

        String password = user.getPassword();

        assertEquals("password123", password);
    }

    @Test
    public void shouldAddBookToUser() {
        User user = new User("1234567", "password123");

        user.addBookToUser(book);

        assertEquals(1, user.myBooks.size());
    }
}
