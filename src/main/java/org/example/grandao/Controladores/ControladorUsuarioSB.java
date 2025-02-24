package org.example.grandao.Controladores;

import org.example.grandao.Entidades.UsuarioJPA;
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
    public ResponseEntity<List<UsuarioJPA>> getUsuarios() {
        List<UsuarioJPA> lista= this.repositorioUsuarioSB.findAll();
        System.out.println(lista);
        return ResponseEntity.ok(lista);
    }

    //GET BY ID --> SELECT BY ID
    @GetMapping("/{id}")
    @Cacheable
    public ResponseEntity<UsuarioJPA> getUsuarioById(@PathVariable Integer id) {
        UsuarioJPA u = this.repositorioUsuarioSB.findById(id).get();
        return ResponseEntity.ok(u);
    }

    //POST --> INSERT
    @PostMapping("/usuario")
    public ResponseEntity<UsuarioJPA> addUsuario(@RequestBody UsuarioJPA usuario) {
        UsuarioJPA usuarioPersistido = this.repositorioUsuarioSB.save(usuario);
        return ResponseEntity.ok().body(usuarioPersistido);
    }

    //PUT --> UPDATE
    @PutMapping("/{id}")
    public ResponseEntity<UsuarioJPA> updateUsuario(@RequestBody UsuarioJPA usuario, @PathVariable Integer id) {
        UsuarioJPA usuarioPersistido = repositorioUsuarioSB.save(usuario);
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
