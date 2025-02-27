package org.example.grandao.Controladores;

import org.example.grandao.DAO.UsuarioFicheroDAO;
import org.example.grandao.Entidades.UsuarioFichero;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/usuarioFichero")
public class ControladorUsuarioFichero {
    private final String rutaArchivo = "usuario.txt"; // Ruta del archivo
    private final UsuarioFicheroDAO usuarioFicheroDAO = new UsuarioFicheroDAO(rutaArchivo);

    // Leer todos los usuarios desde el archivo
    @GetMapping
    public List<UsuarioFichero> getAllUsers() {
        try {
            return usuarioFicheroDAO.leerUsuarios();
        } catch (IOException e) {
            e.printStackTrace();
            return List.of();  // Retorna una lista vacía en caso de error
        }
    }

    // Agregar un usuario al archivo
    @PostMapping
    public ResponseEntity<String> addUser(@RequestBody UsuarioFichero usuario) {
        try {
            usuarioFicheroDAO.insertarUsuario(usuario);
            return ResponseEntity.status(201).body("Usuario agregado exitosamente.");
        } catch (IOException e) {
            e.printStackTrace();
            return ResponseEntity.status(500).body("Error al agregar el usuario.");
        }
    }

}

