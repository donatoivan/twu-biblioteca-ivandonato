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
        User user = new User("1234567", "password123","Adam", "t@t.com", "123456");

        String id = user.getUserId();

        assertEquals("1234567", id);
    }

    @Test
    public void shouldGetPassword() {
        User user = new User("1234567", "password123","Adam", "t@t.com", "123456");

        String password = user.getPassword();

        assertEquals("password123", password);
    }

    @Test
    public void shouldAddBookToUser() {
        User user = new User("1234567", "password123","Adam", "t@t.com", "123456");

        user.addBookToUser(book);

        assertEquals(1, user.myBooks.size());
    }

    @Test
    public void shouldGetName() {
        User user = new User("1234567", "password123","Terry", "t@t.com", "123456");

        String name = user.getName();

        assertEquals("Terry", name);
    }

    @Test
    public void shouldGetEmail() {
        User user = new User("1234567", "password123","Adam", "a@a.com", "123456");

        String email = user.getEmail();

        assertEquals("a@a.com", email);
    }

    @Test
    public void shouldGetPhoneNumber() {
        User user = new User("1234567", "password123","Adam", "t@t.com", "0432234234");

        String phoneNumber = user.getPhoneNumber();

        assertEquals("0432234234", phoneNumber);
    }
}
