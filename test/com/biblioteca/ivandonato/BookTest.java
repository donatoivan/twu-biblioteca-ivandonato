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

}
