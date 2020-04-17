package com.biblioteca.ivandonato;

import java.util.ArrayList;

public class Librarian {
    InputOutput inputOutput;

    public Librarian(InputOutput inputOutput) {
        this.inputOutput = inputOutput;
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

    public void findReturnBook(String title, ArrayList bookList) {
        for (int i = 0; i < bookList.size(); i++) {
            Book book = (Book) bookList.get(i);
            if (book.getTitle().equals(title) && book.getCheckout().equals(true)) {
                book.returnBook();
                inputOutput.successfulReturnMessage();
                return;
            }
        }
        inputOutput.unsuccessfulReturnMessage();
    }
}
