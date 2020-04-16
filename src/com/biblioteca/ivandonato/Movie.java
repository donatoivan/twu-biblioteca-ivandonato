package com.biblioteca.ivandonato;

public class Movie {
    private String title;
    private String year;
    private String director;

    public Movie(String title, String year, String director) {
        this.title = title;
        this.year = year;
        this.director = director;
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
}
