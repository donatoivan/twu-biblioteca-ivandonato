package com.biblioteca.ivandonato;

import java.util.ArrayList;
import java.util.Scanner;

public class InputOutput {

    public void welcomeMessage() {
        System.out.print("Welcome to Biblioteca. Your one-stop-shop for great book titles in Bangalore!\n\n");
    }

    public void displayMenu() {
        System.out.println("[Menu Options] (Please type a number)");
        System.out.println("[0] Quit Application");
        System.out.println("[1] List of books");
        System.out.println("[2] Checkout book");
        System.out.println("[3] Return book");
    }

    public String getInputFromUser() {
        Scanner scanner = new Scanner(System.in);
        return scanner.nextLine();
    }

    public void askForTitle() {
        System.out.println("Please type the title of the book you wish to checkout: ");
    }

    public void inValidOption() {
        System.out.println("Please select a valid option!");
    }

    public void displayBookList(ArrayList booklist) {
        for (int i = 0; i < booklist.size(); i++) {
            Book book = (Book) booklist.get(i);
            if (book.getCheckout().equals(false)) {
                System.out.println(String.format("%s | %s | %s", book.getTitle(), book.getAuthor(), book.getYear()));
            }
        }
    }

    public void successfulCheckoutMessage() {
        System.out.println("Thank you! Enjoy the book");
    }

    public void unsuccessfulCheckoutMessage() {
        System.out.println("Sorry, that book is not available");
    }

    public void successfulReturnMessage() {
        System.out.println("Thank you for returning the book");
    }

    public void unsuccessfulReturnMessage() {
        System.out.println("That is not a valid book to return.");
    }
}
