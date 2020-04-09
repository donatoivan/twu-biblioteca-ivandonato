package com.biblioteca.ivandonato;

import java.util.ArrayList;

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
        InputOutput inputOutput = new InputOutput();
        Librarian librarian = new Librarian();

        inputOutput.welcomeMessage();

        while (true) {
            inputOutput.displayMenu();
            String choice = inputOutput.getInputFromUser();
            librarian.menuController(choice, biblioteca.booklist);
        }

    }
}
