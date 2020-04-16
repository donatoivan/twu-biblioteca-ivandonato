package com.biblioteca.ivandonato;

import java.util.ArrayList;

public class Librarian {
    InputOutput inputOutput;

    public Librarian(InputOutput inputOutput) {
        this.inputOutput = inputOutput;
    }

    public void menuController(String choice, ArrayList bookList, ArrayList movieList) {
        if (choice.equals("1")) {
            inputOutput.displayBookList(bookList);
        } else if (choice.equals("0")) {
            System.exit(1);
        } else if (choice.equals("2")){
            inputOutput.askForCheckoutTitle();
            String title = inputOutput.getInputFromUser();
            findCheckoutBook(title, bookList);
        } else if (choice.equals("3")) {
            inputOutput.askForReturnTitle();
            String title = inputOutput.getInputFromUser();
            findReturnBook(title, bookList);
        } else if (choice.equals("4")) {
            inputOutput.displayMovieList(movieList);
        } else if (choice.equals("5")) {
            inputOutput.askForCheckoutTitleMovie();
            String title = inputOutput.getInputFromUser();
            findCheckoutMovie(title, movieList);
        } else {
            inputOutput.inValidOption();
        }
    }

    public void findCheckoutBook(String title, ArrayList bookList) {
        for (int i = 0; i < bookList.size(); i++) {
            Book book = (Book) bookList.get(i);
            if (book.getTitle().equals(title) && book.getCheckout().equals(false)) {
                book.checkoutBook();
                inputOutput.successfulCheckoutMessage();
                return;
            }
        }
        inputOutput.unsuccessfulCheckoutMessage();
    }

    public void findReturnBook(String title, ArrayList<Book> bookList) {
        for (int i = 0; i < bookList.size(); i++) {
            Book book = bookList.get(i);
            if (book.getTitle().equals(title) && book.getCheckout().equals(true)) {
                book.returnBook();
                inputOutput.successfulReturnMessage();
                return;
            }
        }
        inputOutput.unsuccessfulReturnMessage();
    }

    public void findCheckoutMovie(String title, ArrayList<Movie> movieList) {
        for (int i = 0; i < movieList.size(); i++) {
            Movie movie = movieList.get(i);
            if (movie.getTitle().equals(title) && movie.getCheckoutMovie().equals(false)) {
                movie.checkoutMovie();
                inputOutput.successfulCheckoutMessageMovie();
                return;
            }
        }
        inputOutput.unsuccessfulCheckoutMessageMovie();
    }

}
