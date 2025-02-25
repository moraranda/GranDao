package org.example.grandao.Controladores;

import org.example.grandao.DAO.LibroFicheroDAO;
import org.example.grandao.Entidades.LibroFichero;
import org.example.grandao.Entidades.LibroJPA;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/librosFichero")
public class ControladorLibroFichero {
    private final String filePath = "src/resources/archivos/libros.txt"; // Ruta del archivo
    private final LibroFicheroDAO libroFicheroDAO = new LibroFicheroDAO(filePath);

    // Leer todos los libros del archivo
    @GetMapping
    public List<LibroFichero> getAllBooks() {
        try {
            return libroFicheroDAO.readBooks();
        } catch (IOException e) {
            e.printStackTrace();
            return List.of();  // Retorna una lista vacía en caso de error
        }
    }

    // Agregar un libro al archivo
    @PostMapping
    public ResponseEntity<String> addBook(@RequestBody LibroFichero libro) {
        try {
            libroFicheroDAO.addBook(libro);
            return ResponseEntity.status(201).body("Libro agregado exitosamente.");
        } catch (IOException e) {
            e.printStackTrace();
            return ResponseEntity.status(500).body("Error al agregar el libro.");
        }
    }
}