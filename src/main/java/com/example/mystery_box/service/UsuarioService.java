package com.example.mystery_box.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.mystery_box.model.Usuario;
import com.example.mystery_box.repository.DireccionRepository;
import com.example.mystery_box.repository.UsuarioRepository;
import com.example.mystery_box.repository.VentaRepository;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private VentaRepository ventaRepository;

    @Autowired
    private DireccionRepository direccionRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public List<Usuario> obtenerUsuarios() {
        return usuarioRepository.findAll();
    }

    public Usuario obtenerUsuarioPorId(Long id) {
        return usuarioRepository.findById(id).orElse(null);
    }

    public Usuario guardarUsuario(Usuario usuario) {
        if (usuario.getContrasena() != null) {
            usuario.setContrasena(passwordEncoder.encode(usuario.getContrasena()));
        }
        return usuarioRepository.save(usuario);
    }

    public Usuario actualizarUsuario(Long id, Usuario usuario) {
        return usuarioRepository.findById(id).map(existente -> {
            existente.setCorreo(usuario.getCorreo());
            if (usuario.getContrasena() != null && !usuario.getContrasena().isBlank()) {
                existente.setContrasena(passwordEncoder.encode(usuario.getContrasena()));
            }
            existente.setRol(usuario.getRol());
            return usuarioRepository.save(existente);
        }).orElse(null);
    }

    public Usuario actualizarUsuarioParcial(Long id, Usuario usuario) {
        return usuarioRepository.findById(id).map(existente -> {
            if (usuario.getCorreo() != null) existente.setCorreo(usuario.getCorreo());
            if (usuario.getContrasena() != null && !usuario.getContrasena().isBlank()) {
                existente.setContrasena(passwordEncoder.encode(usuario.getContrasena()));
            }
            if (usuario.getRol() != null) existente.setRol(usuario.getRol());
            return usuarioRepository.save(existente);
        }).orElse(null);
    }

    public void eliminarUsuario(Long id) {
        Usuario usuario = usuarioRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));
        direccionRepository.findByUsuarioId(id).forEach(direccionRepository::delete);
        ventaRepository.findByUsuarioId(id).forEach(ventaRepository::delete);
        usuarioRepository.delete(usuario);
    }

    public Usuario obtenerUsuarioPorCorreo(String correo) {
        return usuarioRepository.findByCorreo(correo);
    }

    public boolean validarLogin(String correo, String contrasena) {
        Usuario usuario = usuarioRepository.findByCorreo(correo);
        return usuario != null && passwordEncoder.matches(contrasena, usuario.getContrasena());
    }

    public Usuario usuarioParaFrontend(Usuario usuario) {
        if (usuario == null) return null;
        Usuario copia = new Usuario();
        copia.setUsername(usuario.getUsername());
        copia.setId(usuario.getId());
        copia.setCorreo(usuario.getCorreo());
        copia.setRol(usuario.getRol());
        return copia; // sin contraseña
    }
}
