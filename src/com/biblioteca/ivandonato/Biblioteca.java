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
        Biblioteca.run(books);
    }

    public static void run(ArrayList books) {
        Biblioteca biblioteca = new ApplicationBuilder().build(books);

        biblioteca.librarian.inputOutput.welcomeMessage();

        while (true) {
            biblioteca.librarian.inputOutput.displayMenu();
            String choice = biblioteca.librarian.inputOutput.getInputFromUser();
            biblioteca.librarian.menuController(choice, biblioteca.bookList);
        }
    }

    public static ArrayList<Book> buildLibrary() {
        ArrayList<Book> books = new ArrayList<>();
        books.add(new Book("Hamlet", "William Shakespeare", "1603"));
        books.add(new Book("Romeo & Juliet", "William Shakespeare", "1597"));
        books.add(new Book("Merchant of Venice", "William Shakespeare", "1600"));

        return books;
    }
}
