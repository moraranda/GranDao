package org.example.grandao.Entidades;



import lombok.Data;
import org.springframework.data.annotation.Id;

import org.springframework.data.mongodb.core.mapping.Document;

//Clase de LIbro hecha para MongoDB

@Data
@Document(collection = "libro") // Anotación de MongoDB
public class LibroMDB {

    @Id
    private String isbn;

    private String titulo;

    private String autor;
}