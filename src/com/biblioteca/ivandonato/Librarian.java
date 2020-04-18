package com.biblioteca.ivandonato;

import java.util.ArrayList;

public class Librarian {

    public Librarian() {
    }

    public boolean findCheckoutBook(String title, ArrayList bookList) {
        for (int i = 0; i < bookList.size(); i++) {
            Book book = (Book) bookList.get(i);
            if (book.getTitle().equals(title) && book.getCheckout().equals(false)) {
                book.checkoutBook();
                return true;
            }
        }
        return false;
    }

    public boolean findReturnBook(String title, ArrayList bookList) {
        for (int i = 0; i < bookList.size(); i++) {
            Book book = (Book) bookList.get(i);
            if (book.getTitle().equals(title) && book.getCheckout().equals(true)) {
                book.returnBook();
                return true;
            }
        }
        return false;
    }

//    public void findCheckoutMovie(String title, ArrayList<Movie> movieList) {
//        for (int i = 0; i < movieList.size(); i++) {
//            Movie movie = movieList.get(i);
//            if (movie.getTitle().equals(title) && movie.getCheckoutMovie().equals(false)) {
//                movie.checkoutMovie();
//                inputOutput.successfulCheckoutMessageMovie();
//                return;
//            }
//        }
//        inputOutput.unsuccessfulCheckoutMessageMovie();
//    }

}
