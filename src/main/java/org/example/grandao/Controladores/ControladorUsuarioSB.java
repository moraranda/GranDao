package org.example.grandao.Controladores;

import jakarta.validation.Valid;
import org.example.grandao.Entidades.Usuario;
import org.example.grandao.Repositorio.RepositorioUsuarioSB;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/usuarioSB")
public class ControladorUsuarioSB {
    RepositorioUsuarioSB usuarioRepositorio;

    @Autowired
    public ControladorUsuarioSB(RepositorioUsuarioSB usuarioRepositorio) {
        this.usuarioRepositorio = usuarioRepositorio;
    }

    //GET --> SELECT *
    @GetMapping
    public ResponseEntity<List<Usuario>> getUsuarios() {
        List<Usuario> lista= this.usuarioRepositorio.findAll();
        System.out.println(lista);
        return ResponseEntity.ok(lista);
    }

    //GET BY ID --> SELECT BY ID
    @GetMapping("/{id}")
    @Cacheable
    public ResponseEntity<Usuario> getUsuarioById(@PathVariable Integer id) {
        Usuario u = this.usuarioRepositorio.findById(id).get();
        return ResponseEntity.ok(u);
    }

    //POST --> INSERT
    @PostMapping("/usuario")
    public ResponseEntity<Usuario> addUsuario(@RequestBody Usuario usuario) {
        Usuario usuarioPersistido = this.usuarioRepositorio.save(usuario);
        return ResponseEntity.ok().body(usuarioPersistido);
    }

    //PUT --> UPDATE
    @PutMapping("/{id}")
    public ResponseEntity<Usuario> updateUsuario(@RequestBody Usuario usuario, @PathVariable Integer id) {
        Usuario usuarioPersistido = usuarioRepositorio.save(usuario);
        return ResponseEntity.ok().body(usuarioPersistido);
    }

    //DELETE
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteUsuario(@PathVariable Integer id) {
        usuarioRepositorio.deleteById(id);
        String mensaje = "Usuario borrado";
        return ResponseEntity.ok().body(mensaje);
    }
}
