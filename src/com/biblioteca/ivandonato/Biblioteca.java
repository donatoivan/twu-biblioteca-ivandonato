package com.biblioteca.ivandonato;

import java.util.ArrayList;

public class Biblioteca {
    public ArrayList bookList;
    private final Librarian librarian;

    public Biblioteca(ArrayList bookList, Librarian librarian) {
        this.bookList = bookList;
        this.librarian = librarian;
    }

    public static void main(String[] args) {
        ArrayList books = Biblioteca.buildLibrary();
        Biblioteca biblioteca = new ApplicationBuilder().build(books);

        biblioteca.welcome();
        while (true) {
            biblioteca.showMenu();
            String choice = biblioteca.readChoice();
            biblioteca.actionOn(choice);
        }
    }

    private void actionOn(String choice) {
        librarian.menuController(choice, bookList);
    }

    private String readChoice() {
        return librarian.inputOutput.getInputFromUser();
    }

    private void showMenu() {
        librarian.inputOutput.displayMenu();
    }

    private void welcome() {
        librarian.inputOutput.welcomeMessage();
    }

    public static ArrayList<Book> buildLibrary() {
        ArrayList<Book> books = new ArrayList<>();
        books.add(new Book("Hamlet", "William Shakespeare", "1603"));
        books.add(new Book("Romeo & Juliet", "William Shakespeare", "1597"));
        books.add(new Book("Merchant of Venice", "William Shakespeare", "1600"));

        return books;
    }
}
