package com.biblioteca.ivandonato;

import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.OutputStream;
import java.io.PrintStream;
import java.util.ArrayList;

import static org.junit.Assert.assertEquals;

public class InputOutputTest {
    public ArrayList<Book> books;

    @Before
    public void initilaiseBooklist() {
        books = new ArrayList<>();
        books.add(new Book("Hamlet", "William Shakespeare", "1603"));
        books.add(new Book("Romeo & Juliet", "William Shakespeare", "1597"));
        books.add(new Book("Merchant of Venice", "William Shakespeare", "1600"));

    }

    @Test
    public void shouldDisplayWelcomeMessageTest() {
        InputOutput inputOutput = new InputOutput();
        OutputStream output = new ByteArrayOutputStream();
        PrintStream printStream = new PrintStream(output);
        System.setOut(printStream);

        inputOutput.welcomeMessage();

        assertEquals("Welcome to Biblioteca. Your one-stop-shop for great book titles in Bangalore!\n\n",
                output.toString());
    }

    @Test
    public void shouldDisplayMenuOptions() {
        InputOutput inputOutput = new InputOutput();
        OutputStream output = new ByteArrayOutputStream();
        PrintStream printStream = new PrintStream(output);
        System.setOut(printStream);

        inputOutput.displayMenu();

        assertEquals("[Menu Options] (Please type a number)\n[0] Quit Application\n[1] List of books\n[2] Checkout " +
                        "book\n[3] Return book\n",
                output.toString());
    }

    @Test
    public void shouldGetInputFromUser() {
        InputOutput inputOutput = new InputOutput();
        String userInput = "1";
        System.setIn(new ByteArrayInputStream(userInput.getBytes()));

        String choice = inputOutput.getInputFromUser();

        assertEquals(choice, "1");
    }

    @Test
    public void shouldDisplayBookList() {
        InputOutput inputOutput = new InputOutput();
        OutputStream output = new ByteArrayOutputStream();
        PrintStream printStream = new PrintStream(output);
        System.setOut(printStream);

        inputOutput.displayBookList(books);

        assertEquals("Hamlet | William Shakespeare | 1603\nRomeo & Juliet | William Shakespeare | 1597\nMerchant of " +
                "Venice | William Shakespeare | 1600\n", output.toString());
    }

}
