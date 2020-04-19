package com.biblioteca.ivandonato;

import java.util.ArrayList;

public class Biblioteca {
    private ArrayList bookList;
    private ArrayList movieList;
    private final Librarian librarian;
    private InputOutput inputOutput;

    public Biblioteca(ArrayList bookList, Librarian librarian, InputOutput inputOutput, ArrayList movieList) {
        this.bookList = bookList;
        this.librarian = librarian;
        this.inputOutput = inputOutput;
        this.movieList = movieList;
    }

    public static void main(String[] args) {
        ArrayList books = Biblioteca.buildLibrary();
        ArrayList movies = Biblioteca.buildMoviesLibrary();
        Biblioteca biblioteca = new ApplicationBuilder().build(books, movies);

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
        } else if (choice.equals("4")) {
            inputOutput.displayMovieList(movieList);
        } else if (choice.equals("5")) {
            checkoutMovie();
        } else {
            inputOutput.inValidOption();
        }
    }

    private void returnBook() {
        inputOutput.askForReturnTitle();
        String title = inputOutput.getInputFromUser();
        if (librarian.findReturnBook(title, bookList)) {
            inputOutput.successfulReturnMessage();
        } else {
            inputOutput.unsuccessfulReturnMessage();
        }
    }

    private void checkoutBook() {
        inputOutput.askForCheckoutTitleBook();
        String title = inputOutput.getInputFromUser();
        if(librarian.findCheckoutBook(title, bookList)){
            inputOutput.successfulCheckoutMessageBook();
        } else {
            inputOutput.unsuccessfulCheckoutMessageBook();
        }
    }

    private void checkoutMovie() {
        inputOutput.askForCheckoutTitleMovie();
        String title = inputOutput.getInputFromUser();
        if (librarian.findCheckoutMovie(title, movieList)) {
            inputOutput.successfulCheckoutMessageMovie();
        } else {
            inputOutput.unsuccessfulCheckoutMessageMovie();
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

    public static ArrayList<Movie> buildMoviesLibrary() {
        ArrayList<Movie> movies = new ArrayList<>();
        movies.add(new Movie("Mad Max", "1985", "George Miller", "10"));
        movies.add(new Movie("Jumanji", "1995", "Joe Johnston", "8"));
        movies.add(new Movie("Mortal Kombat", "1999", "Paul Anderson", "Unrated"));

        return movies;
    }
}
