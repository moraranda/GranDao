package org.example.grandao.RepositorioMDB;

import org.example.grandao.Entidades.UsuarioMDB;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface RepositorioUsuarioMDB extends MongoRepository<UsuarioMDB, String> {

}
