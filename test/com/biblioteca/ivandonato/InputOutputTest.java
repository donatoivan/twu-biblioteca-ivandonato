package com.biblioteca.ivandonato;

import org.junit.Before;
import org.junit.Test;

import java.io.*;
import java.util.ArrayList;

import static org.junit.Assert.assertEquals;

public class InputOutputTest {
    public ArrayList<Book> books;

    @Before
    public void initialiseBookList() {
        books = new ArrayList<>();
        books.add(new Book("Hamlet", "William Shakespeare", "1603"));
        books.add(new Book("Romeo & Juliet", "William Shakespeare", "1597"));
        books.add(new Book("Merchant of Venice", "William Shakespeare", "1600"));

    }

    @Test
    public void shouldDisplayWelcomeMessageTest() {
        ByteArrayOutputStream mockOutput = new ByteArrayOutputStream();
        InputOutput inputOutput = new InputOutput(new PrintStream(mockOutput));

        inputOutput.welcomeMessage();

        assertEquals("Welcome to Biblioteca. Your one-stop-shop for great book titles in Bangalore!\n\n",
                mockOutput.toString());
    }

    @Test
    public void shouldDisplayMenuOptions() {
        ByteArrayOutputStream mockOutput = new ByteArrayOutputStream();
        InputOutput inputOutput = new InputOutput(new PrintStream(mockOutput));

        inputOutput.displayMenu();

        assertEquals("[Menu Options] (Please type a number)\n[0] Quit Application\n[1] List of books\n[2] Checkout " +
                        "book\n[3] Return book\n",
                mockOutput.toString());
    }

    @Test
    public void shouldDisplayBookList() {
        ByteArrayOutputStream mockOutput = new ByteArrayOutputStream();
        InputOutput inputOutput = new InputOutput(new PrintStream(mockOutput));

        inputOutput.displayBookList(books);

        assertEquals("Hamlet | William Shakespeare | 1603\nRomeo & Juliet | William Shakespeare | 1597\nMerchant of " +
                "Venice | William Shakespeare | 1600\n", mockOutput.toString());
    }
}
