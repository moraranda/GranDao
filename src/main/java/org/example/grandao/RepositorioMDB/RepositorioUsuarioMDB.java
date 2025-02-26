package org.example.grandao.RepositorioMDB;

import org.example.grandao.Entidades.UsuarioMDB;
import org.springframework.data.mongodb.repository.MongoRepository;
//Repositorio para Usuario en MONGO
public interface RepositorioUsuarioMDB extends MongoRepository<UsuarioMDB, String> {

}
