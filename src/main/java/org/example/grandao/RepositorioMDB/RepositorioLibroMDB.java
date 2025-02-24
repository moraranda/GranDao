package org.example.grandao.RepositorioMDB;


import org.example.grandao.Entidades.LibroMDB;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface RepositorioLibroMDB extends MongoRepository<LibroMDB, String> { }