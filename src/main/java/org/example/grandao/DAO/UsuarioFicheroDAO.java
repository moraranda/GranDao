package org.example.grandao.DAO;

import org.example.grandao.Entidades.UsuarioJPA;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class UsuarioFicheroDAO {

    private String rutaArchivo;

    // Constructor que recibe la ruta del archivo
    public UsuarioFicheroDAO(String rutaArchivo) {
        this.rutaArchivo = rutaArchivo;
    }

    // Metodo para leer todos los usuarios desde el fichero
    public List<UsuarioJPA> leerUsuarios() throws IOException {
        List<UsuarioJPA> usuarios = new ArrayList<>();
        File file = new File(rutaArchivo);

        // Si el archivo no existe, retornar una lista vacía
        if (!file.exists()) {
            return usuarios;
        }

        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String linea;
            while ((linea = reader.readLine()) != null) {

                UsuarioJPA usuario = parsearLineaAUsuario(linea);
                if (usuario != null) {
                    usuarios.add(usuario);
                }
            }
        }
        return usuarios;
    }

    // Metodo para insertar un usuario en el fichero
    public void insertarUsuario(UsuarioJPA usuario) throws IOException {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(rutaArchivo, true))) {
            // Formatear el usuario como una línea de texto
            String linea = formatearUsuarioComoLinea(usuario);
            writer.write(linea);
            writer.newLine();
        }
    }

    // Metodo para parsear una línea de texto a un objeto Usuario
    private UsuarioJPA parsearLineaAUsuario(String linea) {
        String[] partes = linea.split(", ");
        if (partes.length == 4) {
            UsuarioJPA usuario = new UsuarioJPA();
            usuario.setId(Integer.parseInt(partes[0]));
            usuario.setDni(partes[1]);
            usuario.setNombre(partes[2]);
            usuario.setPassword(partes[3]);
            return usuario;
        }
        return null;
    }

    // Metodo para formatear un objeto Usuario como una línea de texto
    private String formatearUsuarioComoLinea(UsuarioJPA usuario) {
        return usuario.getId() + ", " + usuario.getDni() + ", " + usuario.getNombre() + ", " + usuario.getPassword();
    }
}
