package org.example.grandao.DAO;

import org.example.grandao.Entidades.LibroFichero;
import org.example.grandao.Entidades.LibroJPA;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class LibroFicheroDAO {

    private String filePath;

    // Constructor que recibe la ruta del archivo
    public LibroFicheroDAO(String filePath) {
        this.filePath = filePath;
    }

    // Metodo para leer todos los libros desde el fichero
    public List<LibroFichero> readBooks() throws IOException {
        List<LibroFichero> books = new ArrayList<>();
        File file = new File(filePath);

        // Si el archivo no existe, retornar una lista vacía
        if (!file.exists()) {
            return books;
        }

        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String linea;
            while ((linea = reader.readLine()) != null) {

                LibroFichero libro = parseLineAsBook(linea);
                if (libro != null) {
                    books.add(libro);
                }
            }
        }
        return books;
    }

    // Metodo para insertar un libro en el fichero
    public void addBook(LibroFichero book) throws IOException {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath, true))) {
            // Formatear el libro como una línea de texto
            String line = formatBookAsLine(book);
            writer.write(line);
            writer.newLine();
        }
    }

    // Metodo para parsear una línea de texto a un objeto Libro
    private LibroFichero parseLineAsBook(String line) {
        String[] parts = line.split(", ");

        if (parts.length == 3) {
            LibroFichero book = new LibroFichero();

            book.setIsbn(parts[0]);
            book.setTitulo(parts[1]);
            book.setAutor(parts[2]);

            return book;
        }

        return null;
    }

    // Metodo para formatear un objeto Libro como una línea de texto
    private String formatBookAsLine(LibroFichero book) {
        return book.getIsbn() + ", " + book.getTitulo() + ", " + book.getAutor();
    }
}