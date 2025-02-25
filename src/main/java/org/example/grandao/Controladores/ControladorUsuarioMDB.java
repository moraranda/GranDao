package org.example.grandao.Controladores;

import org.example.grandao.Entidades.UsuarioMDB;
import org.example.grandao.RepositorioMDB.RepositorioUsuarioMDB;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("usuarioMDB")
public class ControladorUsuarioMDB {
    @Autowired
    private RepositorioUsuarioMDB usuarioRepository;

    // Obtener todos los usuarios
    @GetMapping
    public List<UsuarioMDB> findAll() {
        return usuarioRepository.findAll();
    }

    // Obtener un usuario por ID
    @GetMapping("/{id}")
    public ResponseEntity<UsuarioMDB> findById(@PathVariable String id) {
        Optional<UsuarioMDB> usuario = usuarioRepository.findById(id);
        return usuario.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    // Crear un usuario
    @PostMapping("/usuario")
    public UsuarioMDB save(@RequestBody UsuarioMDB usuario) {
        return usuarioRepository.save(usuario);
    }

    // Actualizar un usuario por ID
    @PutMapping("/{id}")
    public ResponseEntity<UsuarioMDB> update(@PathVariable String id, @RequestBody UsuarioMDB usuarioDetails) {
        Optional<UsuarioMDB> usuarioOptional = usuarioRepository.findById(id);
        if (usuarioOptional.isPresent()) {
            UsuarioMDB usuario = usuarioOptional.get();
            usuario.setDni(usuarioDetails.getDni());
            usuario.setNombre(usuarioDetails.getNombre());
            usuario.setPassword(usuarioDetails.getPassword());
            return ResponseEntity.ok(usuarioRepository.save(usuario));
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // Eliminar un usuario por ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable String id) {
        if (usuarioRepository.existsById(id)) {
            usuarioRepository.deleteById(id);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
