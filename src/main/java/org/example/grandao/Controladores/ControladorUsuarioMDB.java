package org.example.grandao.Controladores;

import org.example.grandao.Entidades.Usuario;
import org.example.grandao.Repositorio.RepositorioUsuarioMDB;
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
    public List<Usuario> findAll() {
        return usuarioRepository.findAll();
    }

    // Obtener un usuario por ID
    @GetMapping("/{id}")
    public ResponseEntity<Usuario> findById(@PathVariable String id) {
        Optional<Usuario> usuario = usuarioRepository.findById(id);
        return usuario.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    // Crear un usuario
    @PostMapping
    public Usuario save(@RequestBody Usuario usuario) {
        return usuarioRepository.save(usuario);
    }

    // Actualizar un usuario por ID
    @PutMapping("/{id}")
    public ResponseEntity<Usuario> update(@PathVariable String id, @RequestBody Usuario usuarioDetails) {
        Optional<Usuario> usuarioOptional = usuarioRepository.findById(id);
        if (usuarioOptional.isPresent()) {
            Usuario usuario = usuarioOptional.get();
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
