package com.biblioteca.ivandonato;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class LibrarianTest {
    public ArrayList<Book> books;
    private Librarian librarian;

    @Before
    public void initialiseBooklist() {
        librarian = new Librarian();

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
    public void checkSuccessfulCheckoutMessage() {

        boolean result = librarian.findCheckoutBook("Hamlet", books);

        assertTrue(result);
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

        boolean result = librarian.findCheckoutBook("Haomlet", books);

        assertFalse(result);
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
        checkout(books, "Hamlet");

        boolean result = librarian.findCheckoutBook("Hamlet", books);

        assertFalse(result);
    }

    private void checkout(ArrayList<Book> books, String title) {
        books.stream()
                .filter(b -> b.getTitle().contains(title))
                .forEach(Book::checkoutBook);
    }
}
