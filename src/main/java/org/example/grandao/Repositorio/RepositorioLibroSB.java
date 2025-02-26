package org.example.grandao.Repositorio;

import org.example.grandao.Entidades.LibroJPA;
import org.springframework.data.jpa.repository.JpaRepository;
//Repositorio para LIbro en JPA
public interface RepositorioLibroSB extends JpaRepository<LibroJPA, String> { }