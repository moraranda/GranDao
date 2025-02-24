package org.example.grandao.Repositorio;

import org.example.grandao.Entidades.Libro;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface RepositorioLibroMDB extends MongoRepository<Libro, String> { }