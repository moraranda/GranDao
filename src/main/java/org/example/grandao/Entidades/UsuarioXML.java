package org.example.grandao.Entidades;

import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;

@XmlRootElement(name = "usuario")
public class UsuarioXML implements Serializable {

    private int id;
    private String nombre;
    private String dni;
    private String password;

    public UsuarioXML() {}

    public UsuarioXML(int id, String nombre, String dni, String password) {
        this.id = id;
        this.nombre = nombre;
        this.dni = dni;
        this.password = password;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
