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
        books.add(new Book("hey", "ho", "1"));
        books.add(new Book("gday", "good day", "2"));
        books.add(new Book("loose", "goose", "3"));
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
