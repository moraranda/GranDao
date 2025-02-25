package org.example.grandao.Entidades;

import lombok.Data;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document(collection = "usuario")
public class UsuarioMDB {

    private String id;

    private String dni;

    private String nombre;

    private String password;

}
