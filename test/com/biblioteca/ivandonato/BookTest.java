package com.biblioteca.ivandonato;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class BookTest {

    @Test
    public void setTitle() {
        Book book = new Book();
        book.setTitle("The Road");

        assertEquals("The Road", book.getTitle());
    }

    @Test
    public void getTitle() {
        Book book = new Book();
        book.setTitle("Hamlet");

        assertEquals("Hamlet", book.getTitle());
    }
}
