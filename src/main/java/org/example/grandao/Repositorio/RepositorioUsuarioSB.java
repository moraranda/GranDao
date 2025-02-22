package org.example.grandao.Repositorio;

import org.example.grandao.Entidades.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RepositorioUsuarioSB extends JpaRepository<Usuario, Integer> {
}
