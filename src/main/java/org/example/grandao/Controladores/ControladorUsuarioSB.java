package org.example.grandao.Controladores;

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
    RepositorioUsuarioSB repositorioUsuarioSB;

    @Autowired
    public ControladorUsuarioSB(RepositorioUsuarioSB  repositorioUsuarioSB) {
        this.repositorioUsuarioSB = repositorioUsuarioSB;
    }

    //GET --> SELECT *
    @GetMapping
    public ResponseEntity<List<Usuario>> getUsuarios() {
        List<Usuario> lista= this.repositorioUsuarioSB.findAll();
        System.out.println(lista);
        return ResponseEntity.ok(lista);
    }

    //GET BY ID --> SELECT BY ID
    @GetMapping("/{id}")
    @Cacheable
    public ResponseEntity<Usuario> getUsuarioById(@PathVariable Integer id) {
        Usuario u = this.repositorioUsuarioSB.findById(id).get();
        return ResponseEntity.ok(u);
    }

    //POST --> INSERT
    @PostMapping("/usuario")
    public ResponseEntity<Usuario> addUsuario(@RequestBody Usuario usuario) {
        Usuario usuarioPersistido = this.repositorioUsuarioSB.save(usuario);
        return ResponseEntity.ok().body(usuarioPersistido);
    }

    //PUT --> UPDATE
    @PutMapping("/{id}")
    public ResponseEntity<Usuario> updateUsuario(@RequestBody Usuario usuario, @PathVariable Integer id) {
        Usuario usuarioPersistido = repositorioUsuarioSB.save(usuario);
        return ResponseEntity.ok().body(usuarioPersistido);
    }

    //DELETE
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteUsuario(@PathVariable Integer id) {
        repositorioUsuarioSB.deleteById(id);
        String mensaje = "Usuario borrado";
        return ResponseEntity.ok().body(mensaje);
    }
}
