package org.example.grandao.Entidades;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document(collection = "libro") // Anotación de MongoDB
public class LibroMDB {
    @Id // Anotación de MongoDB
    @Size(max = 20)
    private String isbn;

    @Size(max = 200)
    @NotNull
    private String titulo;

    @Size(max = 100)
    @NotNull
    private String autor;
}