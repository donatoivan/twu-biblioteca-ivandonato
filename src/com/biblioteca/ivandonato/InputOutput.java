package com.biblioteca.ivandonato;

import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Scanner;

public class InputOutput {
    PrintStream stream;
    Scanner scanner;

    public InputOutput(PrintStream stream, Scanner scanner) {
        this.stream = stream;
        this.scanner = scanner;
    }

    public void welcomeMessage() {
        stream.print("Welcome to Biblioteca. Your one-stop-shop for great book titles in Bangalore!\n\n");
    }

    public void displayMenu() {
        stream.println("[Menu Options] (Please type a number)");
        stream.println("[0] Quit Application");
        stream.println("[1] List of books");
        stream.println("[2] Checkout book");
        stream.println("[3] Return book");
        stream.println("[4] List of movies");
        stream.println("[5] Checkout movie");
    }

    public String getInputFromUser() {
        return scanner.nextLine();
    }

    public void askForCheckoutTitle() {
        stream.println("Please type the title of the book you wish to checkout: ");
    }

    public void askForCheckoutTitleMovie() {
        stream.println("Please type the title of the movie you wish to checkout: ");
    }

    public void askForReturnTitle() {
        stream.println("Please type the title of the book you wish to checkout: ");
    }

    public void inValidOption() {
        stream.println("Please select a valid option!");
    }

    public void displayBookList(ArrayList booklist) {
        for (int i = 0; i < booklist.size(); i++) {
            Book book = (Book) booklist.get(i);
            if (book.getCheckout().equals(false)) {
                stream.println(String.format("%s | %s | %s", book.getTitle(), book.getAuthor(), book.getYear()));
            }
        }
    }

    public void displayMovieList(ArrayList movielist) {
        for (int i = 0; i < movielist.size(); i++) {
            Movie movie = (Movie) movielist.get(i);
            if (movie.getCheckoutMovie().equals(false)) {
                stream.println(String.format("%s | %s | %s | %s", movie.getTitle(), movie.getYear(), movie.getDirector(),
                        movie.getRating()));
            }
        }
    }

    public void successfulCheckoutMessage() {
        stream.println("Thank you! Enjoy the book");
    }

    public void successfulCheckoutMessageMovie() {
        stream.println("Thank you! Enjoy the movie");
    }

    public void unsuccessfulCheckoutMessage() {
        stream.println("Sorry, that book is not available");
    }

    public void unsuccessfulCheckoutMessageMovie() {
        stream.println("Sorry, that movie is not available");
    }

    public void successfulReturnMessage() {
        stream.println("Thank you for returning the book");
    }

    public void unsuccessfulReturnMessage() {
        stream.println("That is not a valid book to return.");
    }

}
