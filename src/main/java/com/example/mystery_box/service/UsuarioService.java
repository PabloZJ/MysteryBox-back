package com.example.mystery_box.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.mystery_box.model.Direccion;
import com.example.mystery_box.model.Usuario;
import com.example.mystery_box.model.Venta;
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
        Usuario usuario = usuarioRepository.findById(id).orElse(null);
        if (usuario != null) usuario.setContrasena(null);
        return usuario;
    }

    public Usuario guardarUsuario(Usuario usuario) {
        if (usuario.getContrasena() != null) {
            usuario.setContrasena(passwordEncoder.encode(usuario.getContrasena()));
        }
        return usuarioRepository.save(usuario);
    }

    public Usuario actualizarUsuario(Long id, Usuario usuario) {
        Usuario existente = usuarioRepository.findById(id).orElse(null);
        if (existente != null) {
            existente.setCorreo(usuario.getCorreo());
            if (usuario.getContrasena() != null) {
                existente.setContrasena(passwordEncoder.encode(usuario.getContrasena()));
            }
            existente.setRol(usuario.getRol());
            return usuarioRepository.save(existente);
        }
        return null;
    }

    public Usuario actualizarUsuarioParcial(Long id, Usuario usuario) {
        Usuario existente = usuarioRepository.findById(id).orElse(null);
        if (existente != null) {
            if (usuario.getCorreo() != null) existente.setCorreo(usuario.getCorreo());
            if (usuario.getContrasena() != null) existente.setContrasena(passwordEncoder.encode(usuario.getContrasena()));
            if (usuario.getRol() != null) existente.setRol(usuario.getRol());
            return usuarioRepository.save(existente);
        }
        return null;
    }

    public void eliminarUsuario(Long id) {
        Usuario usuario = usuarioRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));
        List<Direccion> direcciones = direccionRepository.findByUsuarioId(id);
        for (Direccion direccion : direcciones) direccionRepository.delete(direccion);
        List<Venta> ventas = ventaRepository.findByUsuarioId(id);
        for (Venta venta : ventas) ventaRepository.delete(venta);
        usuarioRepository.delete(usuario);
    }

    public Usuario obtenerUsuarioPorCorreo(String correo) {
        Usuario usuario = usuarioRepository.findByCorreo(correo);
        if (usuario != null) usuario.setContrasena(null);
        return usuario;
    }

    public List<Usuario> obtenerUsuariosPorRolId(Long rolId) {
        List<Usuario> usuarios = usuarioRepository.findByRolId(rolId);
        usuarios.forEach(u -> u.setContrasena(null));
        return usuarios;
    }
    public Usuario login(String correo, String contrasena) {
    if (correo == null || contrasena == null) return null;
    Usuario usuario = usuarioRepository.findByCorreo(correo);
    if (usuario != null && usuario.getContrasena() != null &&
        passwordEncoder.matches(contrasena, usuario.getContrasena())) {
        if (usuario.getRol() != null) usuario.getRol().getNombre();
        usuario.setContrasena(null);
        return usuario;
    }
    return null;
}
}
