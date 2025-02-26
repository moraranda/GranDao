package org.example.grandao.Entidades;


import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;

//Clase de Usuario hecha para MongoDB

@Data
@Document(collection = "usuario")
public class UsuarioMDB {

    private String id;

    private String dni;

    private String nombre;

    private String password;

}
