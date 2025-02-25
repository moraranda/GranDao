package org.example.grandao.Controladores;

import org.example.grandao.DAO.UsuarioXMLDAO;
import org.example.grandao.Entidades.UsuarioXML;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.xml.bind.JAXBException;
import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/usuariosXML")
public class ControladorUsuarioXML {

    private final String filePath = "src/main/resources/archivos/usuarios.xml";
    private final UsuarioXMLDAO usuarioXMLDAO = new UsuarioXMLDAO();

    // Obtener todos los usuarios desde el archivo XML
    @GetMapping
    public ResponseEntity<List<UsuarioXML>> getAllUsers() {
        try {
            List<UsuarioXML> usuarios = usuarioXMLDAO.leerUsuariosDesdeXML(filePath);
            return ResponseEntity.ok(usuarios);
        } catch (JAXBException | IOException e) {
            e.printStackTrace();
            return ResponseEntity.status(500).body(null);
        }
    }

    // Insertar un nuevo usuario en el archivo XML
    @PostMapping
    public ResponseEntity<String> addUser(@RequestBody UsuarioXML usuario) {
        try {
            usuarioXMLDAO.insertarUsuarioEnXML(usuario, filePath);
            return ResponseEntity.status(201).body("Usuario agregado exitosamente.");
        } catch (JAXBException | IOException e) {
            e.printStackTrace();
            return ResponseEntity.status(500).body("Error al agregar el usuario.");
        }
    }
}
