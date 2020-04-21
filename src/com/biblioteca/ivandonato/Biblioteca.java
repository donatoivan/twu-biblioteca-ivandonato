package com.biblioteca.ivandonato;

import java.util.ArrayList;

public class Biblioteca {
    private ArrayList bookList;
    private ArrayList movieList;
    private ArrayList users;
    private final Librarian librarian;
    private InputOutput inputOutput;
    private Boolean loggedOut;
    private User loggedInUser;

    public Biblioteca(ArrayList bookList, Librarian librarian, InputOutput inputOutput, ArrayList movieList,
                      ArrayList users) {
        this.bookList = bookList;
        this.librarian = librarian;
        this.inputOutput = inputOutput;
        this.movieList = movieList;
        this.users = users;
        this.loggedOut = true;
    }

    public static void main(String[] args) {
        ArrayList books = Biblioteca.buildLibrary();
        ArrayList movies = Biblioteca.buildMoviesLibrary();
        ArrayList users = Biblioteca.buildUsers();
        Biblioteca biblioteca = new ApplicationBuilder().build(books, movies, users);

        biblioteca.run();
    }

    private void run() {
        welcome();
        while (true) {
            step();
        }
    }

    void step() {
        if (loggedOut) {
            showMenuLoggedOut();
            String choice = readChoice();
            actionOnLoggedOut(choice);
        } else {
            showMenuLoggedIn();
            String choice = readChoice();
            actionOnLoggedIn(choice);
        }
    }

    void actionOnLoggedIn(String choice) {
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
        } else if (choice.equals("6")) {
            logoutUser();
        } else if (choice.equals("7")) {
            inputOutput.viewUserBooks(loggedInUser);
        } else if (choice.equals("8")) {
            inputOutput.viewUserDetails(loggedInUser);
        } else {
            inputOutput.inValidOption();
        }
    }

    void actionOnLoggedOut(String choice) {
        if (choice.equals("1")) {
            inputOutput.displayBookList(bookList);
        } else if (choice.equals("0")) {
            System.exit(1);
        } else if (choice.equals("2")) {
            inputOutput.displayMovieList(movieList);
        } else if (choice.equals("3")) {
            loginUser();
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
        if(librarian.findCheckoutBook(title, bookList, loggedInUser)){
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

    private void loginUser() {
        inputOutput.loginMessage();
        String userId = inputOutput.getInputFromUser();
        inputOutput.passwordMessage();
        String password = inputOutput.getInputFromUser();

        if (validateUser(userId, password)) {
            inputOutput.successfulLoginMessage();
        } else {
            inputOutput.unsuccessfulLoginMessage();
        }
    }

    public Boolean validateUser(String userId, String password) {
        for (int i = 0; i < users.size(); i++) {
            User user = (User) users.get(i);
            if (user.getUserId().equals(userId) && user.getPassword().equals(password)) {
                loggedOut = false;
                loggedInUser = user;
                return true;
            }
        }
        return false;
    }

    private void logoutUser() {
        inputOutput.logoutMessage();

        loggedOut = true;
        loggedInUser = null;
    }

    private String readChoice() {
        return inputOutput.getInputFromUser();
    }

    private void showMenuLoggedOut() {
        inputOutput.displayMenuLoggedOut();
    }

    private void showMenuLoggedIn() {
        inputOutput.displayMenuLoggedIn();
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

    public static ArrayList<User> buildUsers() {
        ArrayList<User> users = new ArrayList<>();
        users.add(new User("123-4567", "password123", "Adam", "t@t.com", "123456"));
        users.add(new User("987-6543", "helloworld","Steve", "a@a.com", "11111156"));
        users.add(new User("111-1111", "whoami","Stacey", "s@s.com", "99999999"));

        return users;
    }
}
