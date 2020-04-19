package com.biblioteca.ivandonato;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.contrib.java.lang.system.ExpectedSystemExit;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Scanner;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class BibliotecaTest {
    private ArrayList<Book> books;
    private ArrayList<Movie> movies;
    private ArrayList<User> users;
    @Rule
    public final ExpectedSystemExit exit = ExpectedSystemExit.none();
    private Biblioteca biblioteca;
    private ByteArrayOutputStream mockOutput;

    @Before
    public void initialiseBookList() {
        books = new ArrayList<>();
        books.add(new Book("Hamlet", "William Shakespeare", "1603"));
        books.add(new Book("Romeo & Juliet", "William Shakespeare", "1597"));
        books.add(new Book("Merchant of Venice", "William Shakespeare", "1600"));

        movies = new ArrayList<>();
        movies.add(new Movie("Mad Max", "1985", "George Miller", "10"));
        movies.add(new Movie("Jumanji", "1995", "Joe Johnston", "8"));
        movies.add(new Movie("Mortal Kombat", "1999", "Paul Anderson", "Unrated"));

        users = new ArrayList<>();
        users.add(new User("123-4567", "password123"));
        users.add(new User("987-6543", "whoami"));
        users.add(new User("246-8101", "drowssap"));

        mockOutput = new ByteArrayOutputStream();
    }


    @Test
    public void shouldDisplayBookListWhenUserInputsOne() {
        ByteArrayInputStream mockInput = new ByteArrayInputStream("1".getBytes());
        biblioteca = new Biblioteca(books, new Librarian(), new InputOutput(new PrintStream(mockOutput),
                new Scanner(mockInput)), movies, users);

        biblioteca.step();

        assertTrue(mockOutput.toString().contains("Hamlet | William Shakespeare | 1603\nRomeo & Juliet | William Shakespeare | 1597\nMerchant of Venice | William Shakespeare | 1600"));
    }

    @Test
    public void shouldDisplayMovieListWhenUserInputsFour() {
        ByteArrayInputStream mockInput = new ByteArrayInputStream("2".getBytes());
        biblioteca = new Biblioteca(books, new Librarian(), new InputOutput(new PrintStream(mockOutput),
                new Scanner(mockInput)), movies, users);

        biblioteca.step();

        assertTrue(mockOutput.toString().contains("Mad Max | 1985 | George Miller | 10\nJumanji | 1995 | Joe " +
                "Johnston | 8\nMortal Kombat | 1999 | Paul Anderson | Unrated"));
    }

    @Test
    public void shouldDisplayInvalidOptionMessage() {
        ByteArrayInputStream mockInput = new ByteArrayInputStream("invalid option".getBytes());
        biblioteca = new Biblioteca(books, new Librarian(), new InputOutput(new PrintStream(mockOutput),
                new Scanner(mockInput)), movies, users);

        biblioteca.step();

        assertTrue(mockOutput.toString().contains("Please select a valid option!"));
    }

    @Test
    public void checkSuccessfulCheckoutBookMessage() {
        ByteArrayInputStream mockInput = new ByteArrayInputStream("2\nHamlet".getBytes());
        biblioteca = new Biblioteca(books, new Librarian(), new InputOutput(new PrintStream(mockOutput),
                new Scanner(mockInput)), movies, users);
        biblioteca.validateUser("123-4567", "password123");

        biblioteca.step();

        assertTrue(mockOutput.toString().contains("Thank you! Enjoy the book"));
    }

    @Test
    public void checkUnsuccessfulCheckoutBookMessage() {
        ByteArrayInputStream mockInput = new ByteArrayInputStream("2\nnot exists".getBytes());
        biblioteca = new Biblioteca(books, new Librarian(), new InputOutput(new PrintStream(mockOutput),
                new Scanner(mockInput)), movies, users);
        biblioteca.validateUser("123-4567", "password123");

        biblioteca.step();

        assertTrue(mockOutput.toString().contains("Sorry, that book is not available"));
    }

    @Test
    public void checkSuccessfulReturnedBookMessage() {
        ByteArrayInputStream mockInput = new ByteArrayInputStream("3\nRomeo & Juliet".getBytes());
        biblioteca = new Biblioteca(books, new Librarian(), new InputOutput(new PrintStream(mockOutput),
                new Scanner(mockInput)), movies, users);
        biblioteca.validateUser("123-4567", "password123");
        checkout(books, "Romeo & Juliet");

        biblioteca.step();

        assertTrue(mockOutput.toString().contains("Thank you for returning the book"));
    }

    @Test
    public void checkUnsuccessfulReturnBookMessage() {
        ByteArrayInputStream mockInput = new ByteArrayInputStream("3\nHamlet".getBytes());
        biblioteca = new Biblioteca(books, new Librarian(), new InputOutput(new PrintStream(mockOutput),
                new Scanner(mockInput)), movies, users);
        biblioteca.validateUser("123-4567", "password123");

        biblioteca.step();

        assertTrue(mockOutput.toString().contains("That is not a valid book to return."));
    }

    @Test
    public void checkThatAReturnedBookCannotBeReturnedAgain() {
        ByteArrayInputStream mockInput = new ByteArrayInputStream("3\nRomeo & Juliet\n3\nRomeo & Juliet".getBytes());
        biblioteca = new Biblioteca(books, new Librarian(), new InputOutput(new PrintStream(mockOutput),
                new Scanner(mockInput)), movies, users);
        biblioteca.validateUser("123-4567", "password123");
        checkout(books, "Romeo & Juliet");

        biblioteca.step();
        biblioteca.step();

        assertTrue(mockOutput.toString().contains("That is not a valid book to return."));
    }

    @Test
    public void checkSuccessfulCheckoutMovieMessage() {
        ByteArrayInputStream mockInput = new ByteArrayInputStream("5\nMad Max".getBytes());
        biblioteca = new Biblioteca(books, new Librarian(), new InputOutput(new PrintStream(mockOutput),
                new Scanner(mockInput)), movies, users);
        biblioteca.validateUser("123-4567", "password123");

        biblioteca.step();

        assertTrue(mockOutput.toString().contains("Thank you! Enjoy the movie"));
    }

    @Test
    public void checkUnsuccessfulCheckoutMovieMessage() {
        ByteArrayInputStream mockInput = new ByteArrayInputStream("5\nnot exists".getBytes());
        biblioteca = new Biblioteca(books, new Librarian(), new InputOutput(new PrintStream(mockOutput),
                new Scanner(mockInput)), movies, users);
        biblioteca.validateUser("123-4567", "password123");

        biblioteca.step();

        assertTrue(mockOutput.toString().contains("Sorry, that movie is not available"));
    }

    @Test
    public void SuccessfulLogin() {
        ByteArrayInputStream mockInput = new ByteArrayInputStream("".getBytes());
        biblioteca = new Biblioteca(books, new Librarian(), new InputOutput(new PrintStream(mockOutput),
                new Scanner(mockInput)), movies, users);

        boolean result = biblioteca.validateUser("123-4567", "password123");

        assertTrue(result);
    }


    @Test
    public void checkUnsuccessfulLogin() {
        ByteArrayInputStream mockInput = new ByteArrayInputStream("".getBytes());
        biblioteca = new Biblioteca(books, new Librarian(), new InputOutput(new PrintStream(mockOutput),
                new Scanner(mockInput)), movies, users);

        boolean result = biblioteca.validateUser("123-4567", "passwo23");

        assertFalse(result);
    }

    @Test
    public void appShouldExit() {
        exit.expectSystemExit();
        ByteArrayInputStream mockInput = new ByteArrayInputStream("0".getBytes());
        biblioteca = new Biblioteca(books, new Librarian(), new InputOutput(new PrintStream(mockOutput),
                new Scanner(mockInput)), movies, users);

        biblioteca.step();
    }

    private void checkout(ArrayList<Book> books, String title) {
        books.stream()
                .filter(b -> b.getTitle().contains(title))
                .forEach(Book::checkoutBook);
    }
}
