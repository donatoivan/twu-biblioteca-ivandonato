package com.biblioteca.ivandonato;

public class Movie {
    private String title;
    private String year;
    private String director;
    private String rating;
    private Boolean checkout;

    public Movie(String title, String year, String director, String rating) {
        this.title = title;
        this.year = year;
        this.director = director;
        this.rating = rating;
        this.checkout = false;
    }

    public String getTitle() {
        return title;
    }

    public String getYear() {
        return year;
    }

    public String getDirector() {
        return director;
    }

    public String getRating() {
        return rating;
    }

    public Boolean getCheckoutMovie() { return  checkout; }

    public void checkoutMovie() {
        if (this.checkout.equals(false)) {
            this.checkout = true;
        }
    }
}
