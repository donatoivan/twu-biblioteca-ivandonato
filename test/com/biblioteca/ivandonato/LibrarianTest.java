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
    }

    @Test
    public void checkSuccessfulCheckoutMessage() {

        boolean result = librarian.findCheckoutBook("Hamlet", books);

        assertTrue(result);
    }

    @Test
    public void checkUnsuccessfulCheckoutMessage() {

        boolean result = librarian.findCheckoutBook("Haomlet", books);

        assertFalse(result);
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
