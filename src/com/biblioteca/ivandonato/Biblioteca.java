package com.biblioteca.ivandonato;

import java.util.ArrayList;
import java.util.Scanner;

public class Biblioteca {
    public ArrayList booklist;

    public Biblioteca(ArrayList bookList) {
        this.booklist = bookList;
    }

    public static void main(String[] args) {
        ArrayList<Book> books = new ArrayList<>();

        books.add(new Book("Hamlet", "William Shakespeare", "1603"));
        books.add(new Book("Romeo & Juliet", "William Shakespeare", "1597"));
        books.add(new Book("Merchant of Venice", "William Shakespeare", "1600"));

        Biblioteca biblioteca = new Biblioteca(books);

        biblioteca.welcomeMessage();
        while (true) {
            biblioteca.displayMenu();
            String choice = biblioteca.getInputFromUser();
            biblioteca.menuController(choice);
        }

    }

    public void welcomeMessage() {
        System.out.print("Welcome to Biblioteca. Your one-stop-shop for great book titles in Bangalore!\n\n");
    }

    public void displayBookList() {
        for (int i = 0; i < this.booklist.size(); i++) {
            Book book = (Book) booklist.get(i);
            if (book.getCheckout().equals(false)) {
                System.out.println(String.format("%s | %s | %s", book.getTitle(), book.getAuthor(), book.getYear()));
            }
        }
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

    public void menuController(String choice) {
        if (choice.equals("1")) {
            displayBookList();
        } else if (choice.equals("0")) {
            System.exit(1);
        } else if (choice.equals("2")){
            System.out.println("Please type the title of the book you wish to checkout: ");
            String title = getInputFromUser();
            findCheckoutBook(title);
        } else if (choice.equals("3")) {
            System.out.println("Please type the title of the book you wish to checkout: ");
            String title = getInputFromUser();
            findReturnBook(title);
        } else {
            System.out.println("Please select a valid option!");
        }
    }

    public void findCheckoutBook(String title) {
        for (int i = 0; i < this.booklist.size(); i++) {
            Book book = (Book) booklist.get(i);
            if (book.getTitle().equals(title)) {
                book.checkoutBook();
                System.out.println("Thank you! Enjoy the book");
                return;
            }
        }
        System.out.println("Sorry, that book is not available");
    }

    public void findReturnBook(String title) {
        for (int i = 0; i < this.booklist.size(); i++) {
            Book book = (Book) booklist.get(i);
            if (book.getTitle().equals(title)) {
                book.returnBook();
                return;
            }
        }
    }
}
