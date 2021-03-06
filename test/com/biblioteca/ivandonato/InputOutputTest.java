package com.biblioteca.ivandonato;

import org.junit.Before;
import org.junit.Test;

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

import static org.junit.Assert.assertEquals;

public class InputOutputTest {
    public ArrayList<Book> books;
    public ArrayList<Movie> movies;
    private ByteArrayOutputStream mockOutput;
    public User user;

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

        user = new User("222-2222", "password","Harry Styles", "one@direction.com", "0404111222");

        mockOutput = new ByteArrayOutputStream();

    }

    @Test
    public void shouldDisplayWelcomeMessageTest() {
        ByteArrayInputStream mockInput = new ByteArrayInputStream("".getBytes());
        InputOutput inputOutput = new InputOutput(new PrintStream(mockOutput), new Scanner(mockInput));

        inputOutput.welcomeMessage();

        assertEquals("Welcome to Biblioteca. Your one-stop-shop for great book titles in Bangalore!\n\n",
                mockOutput.toString());
    }

    @Test
    public void shouldDisplayMenuOptionsNoUserLoggedIn() {
        ByteArrayInputStream mockInput = new ByteArrayInputStream("".getBytes());
        InputOutput inputOutput = new InputOutput(new PrintStream(mockOutput), new Scanner(mockInput));

        inputOutput.displayMenuLoggedOut();

        assertEquals("[Menu Options] (Please type a number)\n[0] Quit Application\n[1] List of books" +
                        "\n[2] List of movies\n[3] Login\n",
                mockOutput.toString());
    }

    @Test
    public void shouldDisplayMenuOptionUserLoggedIn() {
        ByteArrayInputStream mockInput = new ByteArrayInputStream("".getBytes());
        InputOutput inputOutput = new InputOutput(new PrintStream(mockOutput), new Scanner(mockInput));

        inputOutput.displayMenuLoggedIn();

        assertEquals("[Menu Options] (Please type a number)\n[0] Quit Application\n[1] List of books\n[2] Checkout " +
                        "book\n[3] Return book\n[4] List of movies\n[5] Checkout movie\n[6] Logout\n[7] View My " +
                        "Books\n[8] View My Details\n",
                mockOutput.toString());
    }

    @Test
    public void shouldDisplayBookList() {
        ByteArrayInputStream mockInput = new ByteArrayInputStream("1".getBytes());
        InputOutput inputOutput = new InputOutput(new PrintStream(mockOutput), new Scanner(mockInput));

        inputOutput.displayBookList(books);

        assertEquals("Hamlet | William Shakespeare | 1603\nRomeo & Juliet | William Shakespeare | 1597\nMerchant of " +
                "Venice | William Shakespeare | 1600\n",
                mockOutput.toString());
    }

    @Test
    public void shouldNotDisplayCheckOutBook() {
        ByteArrayInputStream mockInput = new ByteArrayInputStream("1".getBytes());
        InputOutput inputOutput = new InputOutput(new PrintStream(mockOutput), new Scanner(mockInput));
        books.stream()
                .filter(b -> b.getTitle().contains("Hamlet"))
                .forEach(Book::checkoutBook);

        inputOutput.displayBookList(books);

        assertEquals("Romeo & Juliet | William Shakespeare | 1597\nMerchant of " +
                "Venice | William Shakespeare | 1600\n", mockOutput.toString());
    }

    @Test
    public void shouldDisplayMovieList() {
        ByteArrayInputStream mockInput = new ByteArrayInputStream("1".getBytes());
        InputOutput inputOutput = new InputOutput(new PrintStream(mockOutput), new Scanner(mockInput));

        inputOutput.displayMovieList(movies);

        assertEquals("Mad Max | 1985 | George Miller | 10\nJumanji | 1995 | Joe Johnston | 8\nMortal Kombat | 1999 | " +
                "Paul Anderson | Unrated\n", mockOutput.toString());
    }

    @Test
    public void shouldNotDisplayCheckOutMovie() {
        ByteArrayInputStream mockInput = new ByteArrayInputStream("4".getBytes());
        InputOutput inputOutput = new InputOutput(new PrintStream(mockOutput), new Scanner(mockInput));
        movies.stream()
                .filter(b -> b.getTitle().contains("Mad Max"))
                .forEach(Movie::checkoutMovie);

        inputOutput.displayMovieList(movies);

        assertEquals("Jumanji | 1995 | Joe Johnston | 8\nMortal Kombat | 1999 | " +
                "Paul Anderson | Unrated\n", mockOutput.toString());
    }

    @Test
    public void viewUserBooks() {
        ByteArrayInputStream mockInput = new ByteArrayInputStream("".getBytes());
        InputOutput inputOutput = new InputOutput(new PrintStream(mockOutput), new Scanner(mockInput));
        user.addBookToUser(new Book("Hamlet", "William Shakespeare", "1603"));
        user.addBookToUser(new Book("Romeo & Juliet", "William Shakespeare", "1597"));

        inputOutput.viewUserBooks(user);

        assertEquals("My Books:\nHamlet | William Shakespeare | 1603\nRomeo & Juliet | William Shakespeare | 1597\n",
                mockOutput.toString());
    }

    @Test
    public void viewUserDetails() {
        ByteArrayInputStream mockInput = new ByteArrayInputStream("".getBytes());
        InputOutput inputOutput = new InputOutput(new PrintStream(mockOutput), new Scanner(mockInput));

        inputOutput.viewUserDetails(user);

        assertEquals("My Details:\nName: Harry Styles\nEmail: one@direction.com\nPhone: 0404111222\nUserId: 222-2222\n",
                mockOutput.toString());
    }
}
