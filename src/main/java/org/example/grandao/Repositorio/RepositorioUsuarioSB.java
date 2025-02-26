package org.example.grandao.Repositorio;

import org.example.grandao.Entidades.UsuarioJPA;
import org.springframework.data.jpa.repository.JpaRepository;
//Repositorio para Usuario en JPA
public interface RepositorioUsuarioSB extends JpaRepository<UsuarioJPA, Integer> {
}
