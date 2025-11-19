package com.example.mystery_box.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.mystery_box.model.Rol;
import com.example.mystery_box.model.Usuario;
import com.example.mystery_box.repository.RolRepository;
import com.example.mystery_box.repository.UsuarioRepository;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class RolService {

    @Autowired
    private RolRepository rolRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    // Obtener todos los roles
    public List<Rol> obtenerRoles() {
        return rolRepository.findAll();
    }
    // Obtener rol por ID
    public Rol obtenerRolPorId(Long id) {
        return rolRepository.findById(id).orElse(null);
    }
    // Guardar rol
    public Rol guardarRol(Rol rol) {
        return rolRepository.save(rol);
    }
    // Actualizar (PUT)
    public Rol actualizarRol(Long id, Rol rol) {
        Rol rolExistente = rolRepository.findById(id).orElse(null);
        if (rolExistente != null) {
            rolExistente.setNombre(rol.getNombre());
            return rolRepository.save(rolExistente);
        }
        return null;
    }
    // Actualizar (PATCH)
    public Rol actualizarRolParcial(Long id, Rol rol) {
        Rol rolExistente = rolRepository.findById(id).orElse(null);
        if (rolExistente != null) {
            if (rol.getNombre() != null) {
                rolExistente.setNombre(rol.getNombre());
            }
            return rolRepository.save(rolExistente);
        }
        return null;
    }
    // Eliminar rol
    public void eliminarRol(Long id) {
        Rol rol = rolRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Rol no encontrado"));
        List<Usuario> usuarios = usuarioRepository.findByRolId(id);
        for (Usuario usuario : usuarios) {
            usuarioRepository.delete(usuario);
        }
        rolRepository.delete(rol);
    }
    // Buscar rol por nombre
    public Rol obtenerRolPorNombre(String nombre) {
        return rolRepository.findByNombre(nombre);
    }
}
