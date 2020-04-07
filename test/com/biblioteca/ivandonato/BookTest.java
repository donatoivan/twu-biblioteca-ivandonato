package com.biblioteca.ivandonato;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class BookTest {

    @Test
    public void getTitle() {
        Book book = new Book("Hamlet");

        assertEquals("Hamlet", book.getTitle());
    }

    @Test
    public void getAuthor() {
        Book book = new Book("Julius Caesar", author: "William Shakespeare", year: "1599");

        assertEquals("William Shakespeare", book.getAuthor());
    }
}
