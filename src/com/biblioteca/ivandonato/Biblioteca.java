package com.biblioteca.ivandonato;

import java.util.ArrayList;
import java.util.List;

public class Biblioteca {

    public static void main(String[] args) {
        Biblioteca biblioteca = new Biblioteca();
        ArrayList<Book> books = new ArrayList<>();
        BookList booklist = new BookList();

        books.add(new Book("Hamlet"));
        books.add(new Book("Romeo & Juliet"));
        books.add(new Book("Merchant of Venice"));

        booklist.setBooklist(books);

        biblioteca.welcomeMessage();
        biblioteca.displayBookList(booklist.booklist);
    }
    public void welcomeMessage() {
        System.out.print("Welcome to Biblioteca. Your one-stop-shop for great book titles in Bangalore!\n");
    }

    public void displayBookList(ArrayList bookList) {
        for (int i = 0; i < bookList.size(); i++) {
            Book book = (Book) bookList.get(i);
            System.out.println(book.getTitle());
        }
    }
}
