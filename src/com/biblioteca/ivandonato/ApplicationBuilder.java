package com.biblioteca.ivandonato;

import java.util.ArrayList;
import java.util.Scanner;

public class ApplicationBuilder {
    Biblioteca build(ArrayList books, ArrayList movies, ArrayList users) {
        return new Biblioteca(books, new Librarian(), new InputOutput(System.out, new Scanner(System.in)), movies,
                users);
    }
}
