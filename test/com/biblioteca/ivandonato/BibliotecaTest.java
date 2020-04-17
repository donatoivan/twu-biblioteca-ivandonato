package com.biblioteca.ivandonato;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.contrib.java.lang.system.ExpectedSystemExit;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.junit.Assert.assertTrue;

public class BibliotecaTest {
    private ArrayList<Book> books;
    @Rule
    public final ExpectedSystemExit exit = ExpectedSystemExit.none();
    private Biblioteca biblioteca;
    private ByteArrayOutputStream mockOutput;
    private InputOutput io;

    @Before
    public void initilaiseBooklist() {
        books = new ArrayList<>();
        books.add(new Book("Hamlet", "William Shakespeare", "1603"));
        books.add(new Book("Romeo & Juliet", "William Shakespeare", "1597"));
        books.add(new Book("Merchant of Venice", "William Shakespeare", "1600"));

        mockOutput = new ByteArrayOutputStream();
        Librarian librarian = new Librarian();
        io = new InputOutput(new PrintStream(mockOutput), Collections.emptyIterator());
        biblioteca = new Biblioteca(books, librarian, io);
    }


    @Test
    public void shouldDisplayBookListWhenUserInputsOne() {
        input(io, "1");

        biblioteca.step();

        assertTrue(mockOutput.toString().contains("Hamlet | William Shakespeare | 1603\nRomeo & Juliet | William Shakespeare | 1597\nMerchant of Venice | William Shakespeare | 1600"));
    }

    @Test
    public void shouldDisplayInvalidOptionMessage() {
        input(io, "invalid option");

        biblioteca.step();

        assertTrue(mockOutput.toString().contains("Please select a valid option!"));
    }

    //    @Test
//    public void checkReturnOfBook() {
//        ByteArrayOutputStream mockOutput = new ByteArrayOutputStream();
//        Librarian librarian = new Librarian(new InputOutput(new PrintStream(mockOutput)));
//        checkout(this.books, "Hamlet");
//
//        boolean returnResult = librarian.findReturnBook("Hamlet", this.books);
//
//        assertTrue(returnResult);
//
//        librarian.inputOutput.displayBookList(this.books);
//        assertEquals("Thank you! Enjoy the book\nThank you for returning the book\nHamlet | William Shakespeare | " +
//                "1603\nRomeo & Juliet | William " +
//                "Shakespeare | 1597\nMerchant of " +
//                "Venice | William Shakespeare | 1600\n", mockOutput.toString());
//    }
//@Test
    @Test
    public void checkSuccessfulCheckoutMessage() {
        input(io, "2", "Hamlet");

        biblioteca.step();

        assertTrue(mockOutput.toString().contains("Thank you! Enjoy the book"));
    }

    @Test
    public void checkUnSuccessfulCheckoutMessageWhenBookNotExists() {
        input(io, "2", "not exists");

        biblioteca.step();

        assertTrue(mockOutput.toString().contains("Sorry, that book is not available"));
    }

    private void input(InputOutput io, String... strs) {
        io.setIn(List.of(strs).iterator());
    }
//    @Test
//    public void checkUnsuccessfulReturnMessage() {
//        ByteArrayOutputStream mockOutput = new ByteArrayOutputStream();
//        Librarian librarian = new Librarian(new InputOutput(new PrintStream(mockOutput)));
//
//        librarian.findReturnBook("Merchant of Cyprus", books);
//
//        assertEquals("That is not a valid book to return.\n", mockOutput.toString());
//    }
//
//    @Test
//    public void checkThatAReturnedBookCannotBeReturnedAgain() {
//        ByteArrayOutputStream mockOutput = new ByteArrayOutputStream();
//        Librarian librarian = new Librarian(new InputOutput(new PrintStream(mockOutput)));
//
//        librarian.findCheckoutBook("Romeo & Juliet", books);
//        librarian.findReturnBook("Romeo & Juliet", books);
//        librarian.findReturnBook("Romeo & Juliet", books);
//
//        assertEquals("Thank you! Enjoy the book\nThank you for returning the book\nThat is not a valid book to return" +
//                ".\n", mockOutput.toString());
//    }
//
//    private void checkout(ArrayList<Book> books, String title) {
//        books.stream()
//                .filter(b -> b.getTitle().contains(title))
//                .forEach(Book::checkoutBook);
//    }

    @Test
    public void appShouldExit() {
        exit.expectSystemExit();

        biblioteca.actionOn("0");
    }

}