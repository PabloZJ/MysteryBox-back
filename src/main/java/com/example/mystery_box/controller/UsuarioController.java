package com.example.mystery_box.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.mystery_box.model.Usuario;
import com.example.mystery_box.service.UsuarioService;

@RestController
@RequestMapping("/api/usuarios")
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;

    @GetMapping
    public ResponseEntity<List<Usuario>> obtenerTodosLosUsuarios() {
        List<Usuario> usuarios = usuarioService.obtenerUsuarios();
        if (usuarios.isEmpty()) return ResponseEntity.noContent().build();
        return ResponseEntity.ok(usuarios);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Usuario> obtenerUsuarioPorId(@PathVariable Long id) {
        Usuario usuario = usuarioService.obtenerUsuarioPorId(id);
        if (usuario == null) return ResponseEntity.notFound().build();
        return ResponseEntity.ok(usuario);
    }

    @PostMapping
    public ResponseEntity<Usuario> crearUsuario(@RequestBody Usuario usuario) {
        Usuario nuevoUsuario = usuarioService.guardarUsuario(usuario);
        nuevoUsuario.setContrasena(null); // nunca devolver contraseña
        return ResponseEntity.status(HttpStatus.CREATED).body(nuevoUsuario);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Usuario> actualizarUsuario(@PathVariable Long id, @RequestBody Usuario usuario) {
        Usuario actualizado = usuarioService.actualizarUsuario(id, usuario);
        if (actualizado == null) return ResponseEntity.notFound().build();
        actualizado.setContrasena(null);
        return ResponseEntity.ok(actualizado);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Usuario> actualizarParcialUsuario(@PathVariable Long id, @RequestBody Usuario usuario) {
        Usuario actualizado = usuarioService.actualizarUsuarioParcial(id, usuario);
        if (actualizado == null) return ResponseEntity.notFound().build();
        actualizado.setContrasena(null);
        return ResponseEntity.ok(actualizado);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarUsuario(@PathVariable Long id) {
        usuarioService.eliminarUsuario(id);
        return ResponseEntity.noContent().build();
    }
    
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody Usuario usuario) {
        try {
            if (usuario == null || usuario.getCorreo() == null || usuario.getContrasena() == null) {
                return ResponseEntity.badRequest().body("Correo y contraseña son requeridos");
            }
            Usuario login = usuarioService.login(usuario.getCorreo(), usuario.getContrasena());
            if (login != null) return ResponseEntity.ok(login);
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Credenciales inválidas");
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error interno del servidor");
        }
    }
}
