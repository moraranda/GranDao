package org.example.grandao.Controladores;

import org.example.grandao.DAO.LibroXMLDAO;
import org.example.grandao.Entidades.LibroJPA;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.xml.bind.JAXBException;
import java.util.List;

@RestController
@RequestMapping("/librosXML")
public class ControladorLibroXML {
    private final String filePath = "libros.xml"; // Ruta del archivo XML
    private final LibroXMLDAO libroXMLDAO = new LibroXMLDAO();

    // Obtener todos los libros desde el archivo XML
    @GetMapping
    public ResponseEntity<List<LibroJPA>> getAllBooks() {
        try {
            List<LibroJPA> libros = libroXMLDAO.readBooksFromXML(filePath);
            return ResponseEntity.ok(libros);
        } catch (JAXBException e) {
            e.printStackTrace();
            return ResponseEntity.status(500).body(null);
        }
    }

    // Insertar un nuevo libro en el archivo XML
    @PostMapping
    public ResponseEntity<String> addBook(@RequestBody LibroJPA libro) {
        try {
            libroXMLDAO.insertBookInXML(libro, filePath);
            return ResponseEntity.status(201).body("Libro agregado exitosamente.");
        } catch (JAXBException e) {
            e.printStackTrace();
            return ResponseEntity.status(500).body("Error al agregar el libro.");
        }
    }
}