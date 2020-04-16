package com.biblioteca.ivandonato;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class MovieTest {

    @Test
    public void shouldGetMovieTitle() {
        Movie movie = new Movie("Mad Max", "1985");
        assertEquals("Mad Max", movie.getTitle());
    }

    @Test
    public void shouldGetMovieYear() {
        Movie movie = new Movie("Mad Max", "1985");
        assertEquals("1985", movie.getYear());
    }

}
