package com.biblioteca.ivandonato;

import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.OutputStream;
import java.io.PrintStream;
import java.util.ArrayList;

import static org.junit.Assert.assertEquals;

public class BibliotecaTest {
    public ArrayList<Book> books;

    @Before
    public void init() {
        books = new ArrayList<>();
        books.add(new Book("Hamlet"));
        books.add(new Book("Romeo & Juliet"));
        books.add(new Book("Merchant of Venice"));
    }

    @Test
    public void welcomeMessageTest() {
        Biblioteca biblioteca = new Biblioteca();

        OutputStream output = new ByteArrayOutputStream();
        PrintStream printStream = new PrintStream(output);
        System.setOut(printStream);

        biblioteca.welcomeMessage();

        assertEquals("Welcome to Biblioteca. Your one-stop-shop for great book titles in Bangalore!\n",
                output.toString());
    }

    @Test
    public void displayBookList() {
        Biblioteca biblioteca = new Biblioteca();
        BookList booklist = new BookList();
        booklist.setBooklist(books);

        OutputStream output = new ByteArrayOutputStream();
        PrintStream printStream = new PrintStream(output);
        System.setOut(printStream);

        biblioteca.displayBookList(booklist.booklist);


        assertEquals("Hamlet\nRomeo & Juliet\nMerchant of Venice\n", output.toString());

    }

}
