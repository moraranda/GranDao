package org.example.grandao.Controladores;

import org.example.grandao.Entidades.LibroJPA;
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
    public ResponseEntity<List<LibroJPA>> getBooks() {
        List<LibroJPA> books = this.repository.findAll();
        System.out.println(books);

        return ResponseEntity.ok(books);
    }

    // GET BY ID -> SELECT BY ISBN
    @GetMapping("/{isbn}")
    @Cacheable
    public ResponseEntity<LibroJPA> getBookById(@PathVariable String isbn) {
        LibroJPA book = this.repository.findById(isbn).get();
        return ResponseEntity.ok(book);
    }

    // POST -> INSERT
    @PostMapping("/libro")
    public ResponseEntity<LibroJPA> addBook(@RequestBody LibroJPA book) {
        LibroJPA savedBooks = this.repository.save(book);
        return ResponseEntity.ok().body(savedBooks);
    }

    // PUT -> UPDATE
    @PutMapping("/{isbn}")
    public ResponseEntity<LibroJPA> updateBook(@RequestBody LibroJPA book, @PathVariable String isbn) {
        LibroJPA savedBook = repository.save(book);
        return ResponseEntity.ok().body(savedBook);
    }

    // DELETE
    @DeleteMapping("/{isbn}")
    public ResponseEntity<String> deleteBook(@PathVariable String isbn) {
        repository.deleteById(isbn);
        String message = "Libro con ISBN " + isbn + " eliminado correctamente.";
        return ResponseEntity.ok().body(message);
    }
}