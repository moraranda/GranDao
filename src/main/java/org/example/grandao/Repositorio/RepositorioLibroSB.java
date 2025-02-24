package org.example.grandao.Repositorio;

import org.example.grandao.Entidades.LibroJPA;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RepositorioLibroSB extends JpaRepository<LibroJPA, String> { }