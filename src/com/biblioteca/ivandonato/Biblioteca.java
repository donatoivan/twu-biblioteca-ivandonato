package com.biblioteca.ivandonato;

import java.util.ArrayList;

public class Biblioteca {

    public static void main(String[] args) {
        Biblioteca biblioteca = new Biblioteca();
        ArrayList<Book> books = new ArrayList<>();
        BookList booklist = new BookList();

        books.add(new Book("Hamlet", "William Shakespeare", "1603"));
        books.add(new Book("Romeo & Juliet", "William Shakespeare", "1597"));
        books.add(new Book("Merchant of Venice", "William Shakespeare", "1600"));

        booklist.setBooklist(books);

        biblioteca.welcomeMessage();
        biblioteca.displayBookList(booklist.booklist);
    }

    public void welcomeMessage() {
        System.out.print("Welcome to Biblioteca. Your one-stop-shop for great book titles in Bangalore!\n\n");
    }

    public void displayBookList(ArrayList bookList) {
        for (int i = 0; i < bookList.size(); i++) {
            Book book = (Book) bookList.get(i);
            System.out.println(String.format("%s | %s | %s", book.getTitle(), book.getAuthor(), book.getYear()));
        }
    }
}
