package com.biblioteca.ivandonato;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.assertEquals;

public class BookListTest {
    public ArrayList<Book> books;

    @Before
    public void init() {
        books = new ArrayList<>();
        books.add(new Book("hey"));
        books.add(new Book("gday"));
        books.add(new Book("loose"));
    }

    @Test
    public void setBookList() {
        BookList booklist = new BookList();
        booklist.setBooklist(books);
        Book firstBook = (Book) booklist.booklist.get(1);

        assertEquals(booklist.booklist.size(), 3);
        assertEquals(firstBook.getTitle(), "gday" );
    }
}
