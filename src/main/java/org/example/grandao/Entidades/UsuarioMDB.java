package org.example.grandao.Entidades;

import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "usuario")
public class UsuarioMDB {
    private Integer id;

    private String dni;

    private String nombre;

    private String password;

    public UsuarioMDB(Integer id, String dni, String nombre, String password) {
        this.id = id;
        this.dni = dni;
        this.nombre = nombre;
        this.password = password;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "UsuarioMDB{" +
                "id=" + id +
                ", dni='" + dni + '\'' +
                ", nombre='" + nombre + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
