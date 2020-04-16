package com.biblioteca.ivandonato;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.contrib.java.lang.system.ExpectedSystemExit;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;

import static org.junit.Assert.assertEquals;

public class LibrarianTest {
    public ArrayList<Book> books;
    public ArrayList<Movie> movies;

    @Rule
    public final ExpectedSystemExit exit = ExpectedSystemExit.none();

    @Before
    public void initialiseLibrary() {
        books = new ArrayList<>();
        books.add(new Book("Hamlet", "William Shakespeare", "1603"));
        books.add(new Book("Romeo & Juliet", "William Shakespeare", "1597"));
        books.add(new Book("Merchant of Venice", "William Shakespeare", "1600"));

        movies = new ArrayList<>();
        movies.add(new Movie("Mad Max", "1985", "George Miller", "10"));
        movies.add(new Movie("Jumanji", "1995", "Joe Johnston", "8"));
        movies.add(new Movie("Mortal Kombat", "1999", "Paul Anderson", "Unrated"));
    }

    @Test
    public void shouldDisplayBookListWhenUserInputsOne() {
        ByteArrayOutputStream mockOutput = new ByteArrayOutputStream();
        Librarian librarian = new Librarian(new InputOutput(new PrintStream(mockOutput)));

        librarian.menuController("1", books, movies);

        assertEquals("Hamlet | William Shakespeare | 1603\nRomeo & Juliet | William Shakespeare | 1597\nMerchant of " +
                "Venice | William Shakespeare | 1600\n", mockOutput.toString());
    }

    @Test
    public void shouldDisplayInvalidOptionMessage() {
        ByteArrayOutputStream mockOutput = new ByteArrayOutputStream();
        Librarian librarian = new Librarian(new InputOutput(new PrintStream(mockOutput)));

        librarian.menuController("asdfasdfasdf", books, movies);

        assertEquals("Please select a valid option!\n", mockOutput.toString());
    }

    @Test
    public void appShouldExit() {
        ByteArrayOutputStream mockOutput = new ByteArrayOutputStream();
        Librarian librarian = new Librarian(new InputOutput(new PrintStream(mockOutput)));
        exit.expectSystemExit();

        librarian.menuController("0", books, movies);
    }

    @Test
    public void shouldNotDisplayCheckOutBook() {
        ByteArrayOutputStream mockOutput = new ByteArrayOutputStream();
        Librarian librarian = new Librarian(new InputOutput(new PrintStream(mockOutput)));

        librarian.findCheckoutBook("Hamlet", books);
        librarian.inputOutput.displayBookList(books);

        assertEquals("Thank you! Enjoy the book\nRomeo & Juliet | William Shakespeare | 1597\nMerchant of " +
                "Venice | William Shakespeare | 1600\n", mockOutput.toString());
    }

    @Test
    public void shouldNotDisplayCheckOutMovie() {
        ByteArrayOutputStream mockOutput = new ByteArrayOutputStream();
        Librarian librarian = new Librarian(new InputOutput(new PrintStream(mockOutput)));

        librarian.findCheckoutMovie("Mad Max", movies);
        librarian.inputOutput.displayMovieList(movies);

        assertEquals("Thank you! Enjoy the movie\nJumanji | 1995 | Joe Johnston | 8\nMortal Kombat | 1999 | Paul " +
                "Anderson | Unrated\n", mockOutput.toString());
    }

    @Test
    public void checkSuccessfulCheckoutMessageBook() {
        ByteArrayOutputStream mockOutput = new ByteArrayOutputStream();
        Librarian librarian = new Librarian(new InputOutput(new PrintStream(mockOutput)));

        librarian.findCheckoutBook("Hamlet", books);

        assertEquals("Thank you! Enjoy the book\n", mockOutput.toString());
    }

    @Test
    public void checkSuccessfulCheckoutMessageMovie() {
        ByteArrayOutputStream mockOutput = new ByteArrayOutputStream();
        Librarian librarian = new Librarian(new InputOutput(new PrintStream(mockOutput)));

        librarian.findCheckoutMovie("Mad Max", movies);

        assertEquals("Thank you! Enjoy the movie\n", mockOutput.toString());
    }

    @Test
    public void checkUnsuccessfulCheckoutMessage() {
        ByteArrayOutputStream mockOutput = new ByteArrayOutputStream();
        Librarian librarian = new Librarian(new InputOutput(new PrintStream(mockOutput)));

        librarian.findCheckoutBook("Haomlet", books);

        assertEquals("Sorry, that book is not available\n", mockOutput.toString());
    }

    @Test
    public void checkUnsuccessfulCheckoutMessageMovie() {
        ByteArrayOutputStream mockOutput = new ByteArrayOutputStream();
        Librarian librarian = new Librarian(new InputOutput(new PrintStream(mockOutput)));

        librarian.findCheckoutMovie("Mad Martin", movies);

        assertEquals("Sorry, that movie is not available\n", mockOutput.toString());
    }

    @Test
    public void checkThatACheckedOutBookCannotBeCheckedOutAgain() {
        ByteArrayOutputStream mockOutput = new ByteArrayOutputStream();
        Librarian librarian = new Librarian(new InputOutput(new PrintStream(mockOutput)));

        librarian.findCheckoutBook("Hamlet", books);
        librarian.findCheckoutBook("Hamlet", books);

        assertEquals("Thank you! Enjoy the book\nSorry, that book is not available\n", mockOutput.toString());
    }

    @Test
    public void checkReturnOfBook() {
        ByteArrayOutputStream mockOutput = new ByteArrayOutputStream();
        Librarian librarian = new Librarian(new InputOutput(new PrintStream(mockOutput)));

        librarian.findCheckoutBook("Hamlet", books);
        librarian.findReturnBook("Hamlet", books);
        librarian.inputOutput.displayBookList(books);

        assertEquals("Thank you! Enjoy the book\nThank you for returning the book\nHamlet | William Shakespeare | " +
                "1603\nRomeo & Juliet | William " +
                "Shakespeare | 1597\nMerchant of " +
                "Venice | William Shakespeare | 1600\n", mockOutput.toString());
    }

    @Test
    public void checkSuccessfulReturnMessage() {
        ByteArrayOutputStream mockOutput = new ByteArrayOutputStream();
        Librarian librarian = new Librarian(new InputOutput(new PrintStream(mockOutput)));

        librarian.findCheckoutBook("Hamlet", books);
        librarian.findReturnBook("Hamlet", books);

        assertEquals("Thank you! Enjoy the book\nThank you for returning the book\n", mockOutput.toString());
    }

    @Test
    public void checkUnsuccessfulReturnMessage() {
        ByteArrayOutputStream mockOutput = new ByteArrayOutputStream();
        Librarian librarian = new Librarian(new InputOutput(new PrintStream(mockOutput)));

        librarian.findReturnBook("Merchant of Cyprus", books);

        assertEquals("That is not a valid book to return.\n", mockOutput.toString());
    }

    @Test
    public void checkThatAReturnedBookCannotBeReturnedAgain() {
        ByteArrayOutputStream mockOutput = new ByteArrayOutputStream();
        Librarian librarian = new Librarian(new InputOutput(new PrintStream(mockOutput)));

        librarian.findCheckoutBook("Romeo & Juliet", books);
        librarian.findReturnBook("Romeo & Juliet", books);
        librarian.findReturnBook("Romeo & Juliet", books);

        assertEquals("Thank you! Enjoy the book\nThank you for returning the book\nThat is not a valid book to return" +
                ".\n", mockOutput.toString());
    }
}
