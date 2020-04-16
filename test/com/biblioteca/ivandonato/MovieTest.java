package com.biblioteca.ivandonato;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class MovieTest {

    @Test
    public void shouldGetMovieTitle() {
        Movie movie = new Movie("Mad Max");
        assertEquals("Mad Max", movie.getTitle());
    }

}
