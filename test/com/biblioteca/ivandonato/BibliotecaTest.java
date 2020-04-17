package com.biblioteca.ivandonato;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.contrib.java.lang.system.ExpectedSystemExit;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;

import static org.junit.Assert.assertEquals;

public class BibliotecaTest {
    private ArrayList<Book> books;
    @Rule
    public final ExpectedSystemExit exit = ExpectedSystemExit.none();
    private Biblioteca biblioteca;
    private ByteArrayOutputStream mockOutput;

    @Before
    public void initilaiseBooklist() {
        books = new ArrayList<>();
        books.add(new Book("Hamlet", "William Shakespeare", "1603"));
        books.add(new Book("Romeo & Juliet", "William Shakespeare", "1597"));
        books.add(new Book("Merchant of Venice", "William Shakespeare", "1600"));

        mockOutput = new ByteArrayOutputStream();
        Librarian librarian = new Librarian(new InputOutput(new PrintStream(mockOutput)));
        biblioteca = new Biblioteca(books, librarian);
    }


    @Test
    public void shouldDisplayBookListWhenUserInputsOne() {
        biblioteca.actionOn("1");

        assertEquals("Hamlet | William Shakespeare | 1603\nRomeo & Juliet | William Shakespeare | 1597\nMerchant of " +
                "Venice | William Shakespeare | 1600\n", mockOutput.toString());
    }

    @Test
    public void shouldDisplayInvalidOptionMessage() {
        biblioteca.actionOn("cdfda");

        assertEquals("Please select a valid option!\n", mockOutput.toString());
    }

    @Test
    public void appShouldExit() {
        exit.expectSystemExit();

        biblioteca.actionOn("0");
    }

}