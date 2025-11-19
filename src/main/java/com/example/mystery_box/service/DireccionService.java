package com.example.mystery_box.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.mystery_box.model.Direccion;
import com.example.mystery_box.repository.DireccionRepository;
import jakarta.transaction.Transactional;

@Service
@Transactional
public class DireccionService {

    @Autowired
    private DireccionRepository direccionRepository;

    // Obtener todas las direcciones
    public List<Direccion> obtenerDirecciones() {
        return direccionRepository.findAll();
    }
    // Obtener dirección por ID
    public Direccion obtenerDireccionPorId(Long id) {
        return direccionRepository.findById(id).orElse(null);
    }
    // Guardar dirección
    public Direccion guardarDireccion(Direccion direccion) {
        return direccionRepository.save(direccion);
    }
    // Actualizar completa (PUT) - reemplaza todo
    public Direccion actualizarDireccion(Long id, Direccion direccion) {
        Direccion direccionExistente = direccionRepository.findById(id).orElse(null);
        if (direccionExistente != null) {
            direccionExistente.setCalle(direccion.getCalle());
            direccionExistente.setNumero(direccion.getNumero());
            direccionExistente.setUsuario(direccion.getUsuario());
            direccionExistente.setComuna(direccion.getComuna());
            return direccionRepository.save(direccionExistente);
        }
        return null;
    }
    // Actualizar parcial (PATCH) - solo campos que llegan
    public Direccion actualizarDireccionParcial(Long id, Direccion direccion) {
        Direccion direccionExistente = direccionRepository.findById(id).orElse(null);
        if (direccionExistente != null) {
            if (direccion.getCalle() != null) {
                direccionExistente.setCalle(direccion.getCalle());
            }
            if (direccion.getNumero() != null) {
                direccionExistente.setNumero(direccion.getNumero());
            }
            if (direccion.getUsuario() != null) {
                direccionExistente.setUsuario(direccion.getUsuario());
            }
            if (direccion.getComuna() != null) {
                direccionExistente.setComuna(direccion.getComuna());
            }
            return direccionRepository.save(direccionExistente);
        }
        return null;
    }
    // Eliminar dirección
    public void eliminarDireccion(Long id) {
        Direccion direccion = direccionRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Dirección no encontrada"));
        direccionRepository.delete(direccion);
    }
    // Obtener direcciones por usuario
    public List<Direccion> obtenerDireccionesPorUsuarioId(Long usuarioId) {
        return direccionRepository.findByUsuarioId(usuarioId);
    }
    // Obtener direcciones por comuna
    public List<Direccion> obtenerDireccionesPorComunaId(Long comunaId) {
        return direccionRepository.findByComunaId(comunaId);
    }
}
