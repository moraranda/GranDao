package org.example.grandao.Controladores;

import org.example.grandao.Entidades.Libro;
import org.example.grandao.Repositorio.RepositorioLibroSB;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/libroSB")
public class ControladorLibroSB {

    RepositorioLibroSB repository;

    @Autowired
    public ControladorLibroSB(RepositorioLibroSB repository) {
        this.repository = repository;
    }

    // GET -> SELECT
    @GetMapping
    public ResponseEntity<List<Libro>> getBooks() {
        List<Libro> books = this.repository.findAll();
        System.out.println(books);

        return ResponseEntity.ok(books);
    }

    // GET BY ID -> SELECT BY ID
    @GetMapping("/{id}")
    @Cacheable
    public ResponseEntity<Libro> getBookById(@PathVariable String isbn) {
        Libro book = this.repository.findById(isbn).get();
        return ResponseEntity.ok(book);
    }

    // POST -> INSERT
    @PostMapping("/libro")
    public ResponseEntity<Libro> addBook(@RequestBody Libro book) {
        Libro savedBooks = this.repository.save(book);
        return ResponseEntity.ok().body(savedBooks);
    }

    // PUT -> UPDATE
    @PutMapping("/{id}")
    public ResponseEntity<Libro> updateBook(@RequestBody Libro book, @PathVariable String isbn) {
        Libro savedBook = repository.save(book);
        return ResponseEntity.ok().body(savedBook);
    }

    // DELETE
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteBook(@PathVariable String isbn) {
        repository.deleteById(isbn);
        String message = "Libro con ISBN " + isbn + " eliminado correctamente.";
        return ResponseEntity.ok().body(message);
    }
}