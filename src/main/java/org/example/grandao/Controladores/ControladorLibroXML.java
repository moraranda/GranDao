package org.example.grandao.Controladores;

import org.example.grandao.DAO.LibroXMLDAO;
import org.example.grandao.Entidades.LibroJPA;
import org.example.grandao.Entidades.LibroXML;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.xml.bind.JAXBException;
import java.io.File;
import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/libroXML")
public class ControladorLibroXML {

    private final String filePath = "libros.xml"; // Archivo XML dentro de resources
    private final LibroXMLDAO libroXMLDAO = new LibroXMLDAO();

    // Leer todos los libros del archivo XML
    @GetMapping
    public List<LibroXML> getAllBooks() {
        try {
            File file = new File(filePath);
            if (!file.exists()) {
                file.createNewFile(); // Crea el archivo si no existe
            }
            return libroXMLDAO.readBooksFromXML(filePath);
        } catch (JAXBException | IOException e) {
            e.printStackTrace();
            return List.of();  // Retorna una lista vacía en caso de error
        }
    }

    // Agregar un libro al archivo XML
    @PostMapping
    public ResponseEntity<String> addBook(@RequestBody LibroXML libro) {
        try {
            File file = new File(filePath);
            if (!file.exists()) {
                file.createNewFile(); // Crea el archivo si no existe
            }
            libroXMLDAO.insertBookInXML(libro, filePath);
            return ResponseEntity.status(201).body("Libro agregado exitosamente.");
        } catch (JAXBException | IOException e) {
            e.printStackTrace();
            return ResponseEntity.status(500).body("Error al agregar el libro.");
        }
    }
}