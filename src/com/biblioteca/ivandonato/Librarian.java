package com.biblioteca.ivandonato;

import java.util.ArrayList;

public class Librarian {
    public void menuController(String choice, ArrayList booklist) {
        InputOutput inputOutput = new InputOutput();
        if (choice.equals("1")) {
            inputOutput.displayBookList(booklist);
        } else if (choice.equals("0")) {
            System.exit(1);
        } else if (choice.equals("2")){
            inputOutput.askForTitle();
            String title = inputOutput.getInputFromUser();
            findCheckoutBook(title, booklist);
        } else if (choice.equals("3")) {
            inputOutput.askForTitle();
            String title = inputOutput.getInputFromUser();
            findReturnBook(title, booklist);
        } else {
            inputOutput.inValidOption();
        }
    }

    public void findCheckoutBook(String title, ArrayList booklist) {
        InputOutput inputOutput = new InputOutput();
        for (int i = 0; i < booklist.size(); i++) {
            Book book = (Book) booklist.get(i);
            if (book.getTitle().equals(title) && book.getCheckout().equals(false)) {
                book.checkoutBook();
                inputOutput.successfulCheckoutMessage();
                return;
            }
        }
        inputOutput.unsuccessfulCheckoutMessage();
    }

    public void findReturnBook(String title, ArrayList booklist) {
        InputOutput inputOutput = new InputOutput();
        for (int i = 0; i < booklist.size(); i++) {
            Book book = (Book) booklist.get(i);
            if (book.getTitle().equals(title) && book.getCheckout().equals(true)) {
                book.returnBook();
                inputOutput.successfulReturnMessage();
                return;
            }
        }
        inputOutput.unsuccessfulReturnMessage();
    }
}
