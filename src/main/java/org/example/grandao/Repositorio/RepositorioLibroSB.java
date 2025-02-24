package org.example.grandao.Repositorio;

import org.example.grandao.Entidades.Libro;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RepositorioLibroSB extends JpaRepository<Libro, String> { }