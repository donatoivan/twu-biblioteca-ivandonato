package com.biblioteca.ivandonato;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class MovieTest {

    @Test
    public void shouldGetMovieTitle() {
        Movie movie = new Movie("Mad Max", "1985", "George Miller", "10");
        assertEquals("Mad Max", movie.getTitle());
    }

    @Test
    public void shouldGetMovieYear() {
        Movie movie = new Movie("Mad Max", "1985", "George Miller", "10");
        assertEquals("1985", movie.getYear());
    }

    @Test
    public void shouldGetDirector() {
        Movie movie = new Movie("Mad Max", "1985", "George Miller", "10");
        assertEquals("George Miller", movie.getDirector());
    }

    @Test
    public void shouldGetMovieRating() {
        Movie movie = new Movie("Mad Max", "1985", "George Miller", "10");
        assertEquals("10", movie.getRating());
    }

}
