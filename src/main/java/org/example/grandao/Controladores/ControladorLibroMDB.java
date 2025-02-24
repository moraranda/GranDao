package org.example.grandao.Controladores;

import org.example.grandao.Entidades.Libro;
import org.example.grandao.Repositorio.RepositorioLibroMDB;
import org.example.grandao.Repositorio.RepositorioUsuarioMDB;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/libroMDB")
public class ControladorLibroMDB {

    @Autowired
    private RepositorioLibroMDB bookRepository;

    // Obtenemos todos los libros
    @GetMapping
    public List<Libro> findAll() {
        return bookRepository.findAll();
    }

    // Obtenemos un libro según su ISBN
    @GetMapping("/{isbn}")
    public ResponseEntity<Libro> findById(@PathVariable String isbn) {
            Optional<Libro> book = bookRepository.findById(isbn);
        return book.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    // Añadimos un libro
    @PostMapping
    public Libro save(@RequestBody Libro book) {
        return bookRepository.save(book);
    }

    // Actualizamos un libro según su ISBN
    @PutMapping("/{isbn}")
    public ResponseEntity<Libro> update(@PathVariable String isbn, @RequestBody Libro bookDetails) {
        Optional<Libro> bookOptional = bookRepository.findById(isbn);

        if (bookOptional.isPresent()) {
            Libro book = bookOptional.get();

            book.setTitulo(bookDetails.getTitulo());
            book.setAutor(bookDetails.getAutor());

            return ResponseEntity.ok(bookRepository.save(book));

        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // Eliminar libro según su ISBN
    @DeleteMapping("/{isbn}")
    public ResponseEntity<Void> deleteById(@PathVariable String isbn) {
        if (bookRepository.existsById(isbn)) {
            bookRepository.deleteById(isbn);
            return ResponseEntity.noContent().build();

        } else {
            return ResponseEntity.notFound().build();
        }
    }
}