package com.biblioteca.ivandonato;

import java.util.ArrayList;

public class Biblioteca {
    public ArrayList bookList;
    public ArrayList movielist;
    private final Librarian librarian;

    public Biblioteca(ArrayList bookList, ArrayList movielist, Librarian librarian) {
        this.bookList = bookList;
        this.librarian = librarian;
        this.movielist = movielist;
    }

    public static void main(String[] args) {
        ArrayList books = Biblioteca.buildBooksLibrary();
        ArrayList movies = Biblioteca.buildMoviesLibrary();
        Biblioteca.run(books, movies);
    }

    public static void run(ArrayList books, ArrayList movies) {
        Biblioteca biblioteca = new ApplicationBuilder().build(books, movies);

        biblioteca.librarian.inputOutput.welcomeMessage();

        while (true) {
            biblioteca.librarian.inputOutput.displayMenu();
            String choice = biblioteca.librarian.inputOutput.getInputFromUser();
            biblioteca.librarian.menuController(choice, biblioteca.bookList, biblioteca.movielist);
        }
    }

    public static ArrayList<Book> buildBooksLibrary() {
        ArrayList<Book> books = new ArrayList<>();
        books.add(new Book("Hamlet", "William Shakespeare", "1603"));
        books.add(new Book("Romeo & Juliet", "William Shakespeare", "1597"));
        books.add(new Book("Merchant of Venice", "William Shakespeare", "1600"));

        return books;
    }

    public static ArrayList<Movie> buildMoviesLibrary() {
        ArrayList<Movie> movies = new ArrayList<>();
        movies.add(new Movie("Mad Max", "1985", "George Miller", "10"));
        movies.add(new Movie("Jumanji", "1995", "Joe Johnston", "8"));
        movies.add(new Movie("Mortal Kombat", "1999", "Paul Anderson", "Unrated"));

        return movies;
    }
}
