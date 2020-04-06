package com.biblioteca.ivandonato;

import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.OutputStream;
import java.io.PrintStream;

import static org.junit.Assert.assertEquals;

public class BibliotecaTest {

    @Test
    public void welcomeMessageTest() {
        Biblioteca biblioteca = new Biblioteca();

        OutputStream output = new ByteArrayOutputStream();
        PrintStream printStream = new PrintStream(output);
        System.setOut(printStream);

        biblioteca.welcomeMessage();

        assertEquals("Welcome to Biblioteca. Your one-stop-shop for great book titles in Bangalore!",
                output.toString());

    }

}
