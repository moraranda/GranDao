package org.example.grandao.Repositorio;

import org.example.grandao.Entidades.Usuario;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface RepositorioUsuarioMDB extends MongoRepository<Usuario, String> {

}
