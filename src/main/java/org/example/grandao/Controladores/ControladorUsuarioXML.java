package org.example.grandao.Controladores;

import org.example.grandao.DAO.UsuarioXMLDAO;
import org.example.grandao.Entidades.UsuarioJPA;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.xml.bind.JAXBException;
import java.io.File;
import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/usuariosXML")
public class ControladorUsuarioXML {

    private final String filePath = "src/main/resources/usuarios.xml"; // Ruta predeterminada del archivo XML
    private final UsuarioXMLDAO usuarioXMLDAO = new UsuarioXMLDAO();

    // Obtener todos los usuarios desde el archivo XML
    @GetMapping
    public ResponseEntity<List<UsuarioJPA>> getAllUsers() {
        try {
            File file = new File(filePath);
            if (!file.exists()) {
                file.createNewFile(); // Si el archivo no existe, se crea
            }
            List<UsuarioJPA> usuarios = usuarioXMLDAO.leerUsuariosDesdeXML(filePath);
            return ResponseEntity.ok(usuarios);
        } catch (JAXBException | IOException e) {
            e.printStackTrace();
            return ResponseEntity.status(500).body(null);
        }
    }

    // Insertar un nuevo usuario en el archivo XML
    @PostMapping
    public ResponseEntity<String> addUser(@RequestBody UsuarioJPA usuario) {
        try {
            File file = new File(filePath);
            if (!file.exists()) {
                file.createNewFile(); // Si el archivo no existe, se crea
            }
            usuarioXMLDAO.insertarUsuarioEnXML(usuario, filePath);
            return ResponseEntity.status(201).body("Usuario agregado exitosamente.");
        } catch (JAXBException | IOException e) {
            e.printStackTrace();
            return ResponseEntity.status(500).body("Error al agregar el usuario.");
        }
    }
}