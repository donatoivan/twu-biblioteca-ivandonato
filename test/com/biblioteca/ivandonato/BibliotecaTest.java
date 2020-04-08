package com.biblioteca.ivandonato;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.contrib.java.lang.system.ExpectedSystemExit;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.OutputStream;
import java.io.PrintStream;
import java.util.ArrayList;

import static org.junit.Assert.assertEquals;

public class BibliotecaTest {
    public ArrayList<Book> books;

    @Rule
    public final ExpectedSystemExit exit = ExpectedSystemExit.none();

    @Before
    public void initilaiseBooklist() {
        books = new ArrayList<>();
        books.add(new Book("Hamlet", "William Shakespeare", "1603"));
        books.add(new Book("Romeo & Juliet", "William Shakespeare", "1597"));
        books.add(new Book("Merchant of Venice", "William Shakespeare", "1600"));
    }

    @Test
    public void shouldDisplayWelcomeMessageTest() {
        Biblioteca biblioteca = new Biblioteca(books);
        OutputStream output = new ByteArrayOutputStream();
        PrintStream printStream = new PrintStream(output);
        System.setOut(printStream);

        biblioteca.welcomeMessage();

        assertEquals("Welcome to Biblioteca. Your one-stop-shop for great book titles in Bangalore!\n\n",
                output.toString());
    }

    @Test
    public void shouldDisplayBookList() {
        Biblioteca biblioteca = new Biblioteca(books);
        OutputStream output = new ByteArrayOutputStream();
        PrintStream printStream = new PrintStream(output);
        System.setOut(printStream);

        biblioteca.displayBookList();

        assertEquals("Hamlet | William Shakespeare | 1603\nRomeo & Juliet | William Shakespeare | 1597\nMerchant of " +
                "Venice | William Shakespeare | 1600\n", output.toString());
    }

    @Test
    public void shouldDisplayMenuOptions() {
        Biblioteca biblioteca = new Biblioteca(books);
        OutputStream output = new ByteArrayOutputStream();
        PrintStream printStream = new PrintStream(output);
        System.setOut(printStream);

        biblioteca.displayMenu();

        assertEquals("[Menu Options] (Please type a number)\n[0] Quit Application\n[1] List of books\n[2] Checkout " +
                        "book\n",
                output.toString());
    }

    @Test
    public void shouldGetInputFromUser() {
        Biblioteca biblioteca = new Biblioteca(books);
        String userInput = "1";
        System.setIn(new ByteArrayInputStream(userInput.getBytes()));

        String choice = biblioteca.getInputFromUser();

        assertEquals(choice, "1");
    }

    @Test
    public void shouldDisplayBookListWhenUserInputsOne() {
        Biblioteca biblioteca = new Biblioteca(books);
        OutputStream output = new ByteArrayOutputStream();
        PrintStream printStream = new PrintStream(output);
        System.setOut(printStream);

        biblioteca.menuController("1");

        assertEquals("Hamlet | William Shakespeare | 1603\nRomeo & Juliet | William Shakespeare | 1597\nMerchant of " +
                "Venice | William Shakespeare | 1600\n", output.toString());
    }

    @Test
    public void shouldDisplayInvalidOptionMessage() {
        Biblioteca biblioteca = new Biblioteca(books);
        OutputStream output = new ByteArrayOutputStream();
        PrintStream printStream = new PrintStream(output);
        System.setOut(printStream);

        biblioteca.menuController("asdfasdfasdf");

        assertEquals("Please select a valid option!\n", output.toString());
    }

    @Test
    public void appShouldExit() {
        exit.expectSystemExit();
        Biblioteca biblioteca = new Biblioteca(books);

        biblioteca.menuController("0");
    }

    @Test
    public void shouldNotDisplayCheckOutBook() {
        Biblioteca biblioteca = new Biblioteca(books);
        OutputStream output = new ByteArrayOutputStream();
        PrintStream printStream = new PrintStream(output);
        System.setOut(printStream);

        biblioteca.findCheckoutBook("Hamlet");
        biblioteca.displayBookList();

        assertEquals("Thank you! Enjoy the book\nRomeo & Juliet | William Shakespeare | 1597\nMerchant of " +
                "Venice | William Shakespeare | 1600\n", output.toString());
    }

    @Test
    public void checkSuccessfulCheckoutMessage() {
        Biblioteca biblioteca = new Biblioteca(books);
        OutputStream output = new ByteArrayOutputStream();
        PrintStream printStream = new PrintStream(output);
        System.setOut(printStream);

        biblioteca.findCheckoutBook("Hamlet");

        assertEquals("Thank you! Enjoy the book\n", output.toString());
    }
}
