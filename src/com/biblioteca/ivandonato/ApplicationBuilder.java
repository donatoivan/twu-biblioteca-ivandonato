package com.biblioteca.ivandonato;

import java.util.ArrayList;

public class ApplicationBuilder {
    Biblioteca build(ArrayList books, ArrayList movies) {
        return new Biblioteca(books, movies, new Librarian(new InputOutput(System.out)));
    }
}
