package com.biblioteca.ivandonato;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class UserTest {

    @Test
    public void shouldGetUserId() {
        User user = new User("1234567");

        assertEquals("1234567", user.getUserId());
    }

    @Test
    public void shouldGetPassword() {
        User user = new User("1234567", "password123");

        assertEquals("password123", user.getPassword());
    }
}
