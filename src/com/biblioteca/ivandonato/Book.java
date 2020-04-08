package com.biblioteca.ivandonato;

public class Book {
    private String title;
    private String author;
    private String year;
    private Boolean checkout;

    public Book(String title, String author, String year) {
        this.title = title;
        this.author = author;
        this.year = year;
        this.checkout = false;
    }

    public String getTitle() {
        return title;
    }

    public String getAuthor() { return author; }

    public String getYear() { return year; }

    public Boolean getCheckout() { return  checkout; }

    public void checkoutBook() {
        if (this.checkout.equals(false)) {
            this.checkout = true;
        }
    }

    public void returnBook() {
        if (this.checkout.equals(true)) {
            this.checkout = false;
        }
    }
}
