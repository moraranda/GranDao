package org.example.grandao.Controladores;

import org.example.grandao.Entidades.LibroMDB;
import org.example.grandao.RepositorioMDB.RepositorioLibroMDB;
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
    public List<LibroMDB> findAll() {
        return bookRepository.findAll();
    }

    // Obtenemos un libro según su ISBN
    @GetMapping("/{isbn}")
    public ResponseEntity<LibroMDB> findById(@PathVariable String isbn) {
            Optional<LibroMDB> book = bookRepository.findById(isbn);
        return book.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping("/libro")
    public LibroMDB save(@RequestBody LibroMDB book) {
        return bookRepository.save(book);
    }

    @DeleteMapping("/{isbn}")
    public ResponseEntity<Void> deleteById(@PathVariable String isbn) {
        if (bookRepository.existsById(isbn)) {
            bookRepository.deleteById(isbn);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // Actualizamos un libro según su ISBN
    @PutMapping("/{isbn}")
    public ResponseEntity<LibroMDB> update(@PathVariable String isbn, @RequestBody LibroMDB bookDetails) {
        Optional<LibroMDB> bookOptional = bookRepository.findById(isbn);

        if (bookOptional.isPresent()) {
            LibroMDB book = bookOptional.get();

            book.setTitulo(bookDetails.getTitulo());
            book.setAutor(bookDetails.getAutor());

            return ResponseEntity.ok(bookRepository.save(book));

        } else {
            return ResponseEntity.notFound().build();
        }
    }

}