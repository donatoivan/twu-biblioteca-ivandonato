package com.biblioteca.ivandonato;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.contrib.java.lang.system.ExpectedSystemExit;

import java.io.ByteArrayOutputStream;
import java.io.OutputStream;
import java.io.PrintStream;
import java.util.ArrayList;

import static org.junit.Assert.assertEquals;

public class LibrarianTest {
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
    public void shouldDisplayBookListWhenUserInputsOne() {
        Librarian librarian = new Librarian();

        OutputStream output = new ByteArrayOutputStream();
        PrintStream printStream = new PrintStream(output);
        System.setOut(printStream);

        librarian.menuController("1", books);

        assertEquals("Hamlet | William Shakespeare | 1603\nRomeo & Juliet | William Shakespeare | 1597\nMerchant of " +
                "Venice | William Shakespeare | 1600\n", output.toString());
    }

    @Test
    public void shouldDisplayInvalidOptionMessage() {
        Librarian librarian = new Librarian();
        OutputStream output = new ByteArrayOutputStream();
        PrintStream printStream = new PrintStream(output);
        System.setOut(printStream);

        librarian.menuController("asdfasdfasdf", books);

        assertEquals("Please select a valid option!\n", output.toString());
    }

    @Test
    public void appShouldExit() {
        exit.expectSystemExit();
        Librarian librarian = new Librarian();

        librarian.menuController("0", books);
    }

    @Test
    public void shouldNotDisplayCheckOutBook() {
        Librarian librarian = new Librarian();
        InputOutput inputOutput = new InputOutput();
        OutputStream output = new ByteArrayOutputStream();
        PrintStream printStream = new PrintStream(output);
        System.setOut(printStream);

        librarian.findCheckoutBook("Hamlet", books);
        inputOutput.displayBookList(books);

        assertEquals("Thank you! Enjoy the book\nRomeo & Juliet | William Shakespeare | 1597\nMerchant of " +
                "Venice | William Shakespeare | 1600\n", output.toString());
    }

    @Test
    public void checkSuccessfulCheckoutMessage() {
        Librarian librarian = new Librarian();
        OutputStream output = new ByteArrayOutputStream();
        PrintStream printStream = new PrintStream(output);
        System.setOut(printStream);

        librarian.findCheckoutBook("Hamlet", books);

        assertEquals("Thank you! Enjoy the book\n", output.toString());
    }

    @Test
    public void checkUnsuccessfulCheckoutMessage() {
        Librarian librarian = new Librarian();
        OutputStream output = new ByteArrayOutputStream();
        PrintStream printStream = new PrintStream(output);
        System.setOut(printStream);

        librarian.findCheckoutBook("Haomlet", books);

        assertEquals("Sorry, that book is not available\n", output.toString());
    }

    @Test
    public void checkThatACheckedOutBookCannotBeCheckedOutAgain() {
        Librarian librarian = new Librarian();
        librarian.findCheckoutBook("Hamlet", books);
        OutputStream output = new ByteArrayOutputStream();
        PrintStream printStream = new PrintStream(output);
        System.setOut(printStream);

        librarian.findCheckoutBook("Hamlet", books);

        assertEquals("Sorry, that book is not available\n", output.toString());
    }

    @Test
    public void checkReturnOfBook() {
        Librarian librarian = new Librarian();
        InputOutput inputOutput = new InputOutput();
        librarian.findCheckoutBook("Hamlet", books);
        OutputStream output = new ByteArrayOutputStream();
        PrintStream printStream = new PrintStream(output);
        System.setOut(printStream);

        librarian.findReturnBook("Hamlet", books);
        inputOutput.displayBookList(books);

        assertEquals("Thank you for returning the book\nHamlet | William Shakespeare | 1603\nRomeo & Juliet | William " +
                "Shakespeare | 1597\nMerchant of " +
                "Venice | William Shakespeare | 1600\n", output.toString());
    }

    @Test
    public void checkSuccessfulReturnMessage() {
        Librarian librarian = new Librarian();
        librarian.findCheckoutBook("Hamlet", books);
        OutputStream output = new ByteArrayOutputStream();
        PrintStream printStream = new PrintStream(output);
        System.setOut(printStream);

        librarian.findReturnBook("Hamlet", books);

        assertEquals("Thank you for returning the book\n", output.toString());
    }

    @Test
    public void checkUnsuccessfulReturnMessage() {
        Librarian librarian = new Librarian();
        OutputStream output = new ByteArrayOutputStream();
        PrintStream printStream = new PrintStream(output);
        System.setOut(printStream);

        librarian.findReturnBook("Merchant of Cyprus", books);

        assertEquals("That is not a valid book to return.\n", output.toString());
    }

    @Test
    public void checkThatAReturnedBookCannotBeReturnedAgain() {
        Librarian librarian = new Librarian();
        librarian.findCheckoutBook("Romeo & Juliet", books);
        librarian.findReturnBook("Romeo & Juliet", books);
        OutputStream output = new ByteArrayOutputStream();
        PrintStream printStream = new PrintStream(output);
        System.setOut(printStream);

        librarian.findReturnBook("Romeo & Juliet", books);

        assertEquals("That is not a valid book to return.\n", output.toString());
    }
}
