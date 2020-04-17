package com.biblioteca.ivandonato;

import java.util.ArrayList;

public class Biblioteca {
    private ArrayList bookList;
    private final Librarian librarian;
    private InputOutput inputOutput;

    public Biblioteca(ArrayList bookList, Librarian librarian, InputOutput inputOutput) {
        this.bookList = bookList;
        this.librarian = librarian;
        this.inputOutput = inputOutput;
    }

    public static void main(String[] args) {
        ArrayList books = Biblioteca.buildLibrary();
        Biblioteca biblioteca = new ApplicationBuilder().build(books);

        biblioteca.run();
    }

    private void run() {
        welcome();
        while (true) {
            step();
        }
    }

    void step() {
        showMenu();
        String choice = readChoice();
        actionOn(choice);
    }

    void actionOn(String choice) {
        if (choice.equals("1")) {
            inputOutput.displayBookList(bookList);
        } else if (choice.equals("0")) {
            System.exit(1);
        } else if (choice.equals("2")) {
            checkoutBook();
        } else if (choice.equals("3")) {
            returnBook();
        } else {
            inputOutput.inValidOption();
        }
    }

    private void returnBook() {
        inputOutput.askForReturnTitle();
        String title = inputOutput.getInputFromUser();
        librarian.findReturnBook(title, bookList);
    }

    private void checkoutBook() {
        inputOutput.askForCheckoutTitle();
        String title = inputOutput.getInputFromUser();
        if(librarian.findCheckoutBook(title, bookList)){
            inputOutput.successfulCheckoutMessage();
        }else {
            inputOutput.unsuccessfulCheckoutMessage();
        }
    }

    private String readChoice() {
        return inputOutput.getInputFromUser();
    }

    private void showMenu() {
        inputOutput.displayMenu();
    }

    private void welcome() {
        inputOutput.welcomeMessage();
    }

    private static ArrayList<Book> buildLibrary() {
        ArrayList<Book> books = new ArrayList<>();
        books.add(new Book("Hamlet", "William Shakespeare", "1603"));
        books.add(new Book("Romeo & Juliet", "William Shakespeare", "1597"));
        books.add(new Book("Merchant of Venice", "William Shakespeare", "1600"));

        return books;
    }
}
