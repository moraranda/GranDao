package org.example.grandao.Repositorio;

import jakarta.validation.constraints.Size;
import org.example.grandao.Entidades.Libro;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RepositorioLibroSB extends JpaRepository<Libro, String> { }