package com.biblioteca.ivandonato;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class BookTest {

    @Test
    public void getTitle() {
        Book book = new Book("Hamlet");

        assertEquals("Hamlet", book.getTitle());
    }
}
