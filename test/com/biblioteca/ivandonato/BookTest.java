package com.biblioteca.ivandonato;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class BookTest {

    @Test
    public void getTitle() {
        Book book = new Book("Hamlet", "William Shakespeare", "1603");

        assertEquals("Hamlet", book.getTitle());
    }

    @Test
    public void getAuthor() {
        Book book = new Book("Julius Casear", "William Shakespeare", "1599");

        assertEquals("William Shakespeare", book.getAuthor());
    }
}
