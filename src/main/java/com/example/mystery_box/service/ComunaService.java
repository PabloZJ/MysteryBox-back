package com.example.mystery_box.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.mystery_box.model.Comuna;
import com.example.mystery_box.model.Direccion;
import com.example.mystery_box.repository.ComunaRepository;
import com.example.mystery_box.repository.DireccionRepository;
import jakarta.transaction.Transactional;

@Service
@Transactional
public class ComunaService {

    @Autowired
    private ComunaRepository comunaRepository;

    @Autowired
    private DireccionRepository direccionRepository;

    // Obtener todas las comunas
    public List<Comuna> obtenerComunas() {
        return comunaRepository.findAll();
    }
    // Obtener una comuna por ID
    public Comuna obtenerComunaPorId(Long id) {
        return comunaRepository.findById(id).orElse(null);
    }
    // Guardar comuna
    public Comuna guardarComuna(Comuna comuna) {
        return comunaRepository.save(comuna);
    }
    // Actualizar completa (PUT)
    public Comuna actualizarComuna(Long id, Comuna comuna) {
        Comuna comunaExistente = comunaRepository.findById(id).orElse(null);
        if (comunaExistente != null) {
            comunaExistente.setNombre(comuna.getNombre());
            comunaExistente.setRegion(comuna.getRegion());
            return comunaRepository.save(comunaExistente);
        }
        return null;
    }
    // Actualizar (PATCH)
    public Comuna actualizarComunaParcial(Long id, Comuna comuna) {
        Comuna comunaExistente = comunaRepository.findById(id).orElse(null);
        if (comunaExistente != null) {
            if (comuna.getNombre() != null) {
                comunaExistente.setNombre(comuna.getNombre());
            }
            if (comuna.getRegion() != null) {
                comunaExistente.setRegion(comuna.getRegion());
            }
            return comunaRepository.save(comunaExistente);
        }
        return null;
    }
    // Eliminar comuna
    public void eliminarComuna(Long id) {
        Comuna comuna = comunaRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Comuna no encontrada"));
        List<Direccion> direcciones = direccionRepository.findByComunaId(id);
        for (Direccion direccion : direcciones) {
            direccionRepository.delete(direccion);
        }
        comunaRepository.delete(comuna);
    }
    // Obtener comunas por región
    public List<Comuna> obtenerComunasPorRegionId(Long regionId) {
        return comunaRepository.findByRegionId(regionId);
    }
    // Buscar comuna por nombre
    public Comuna obtenerComunaPorNombre(String nombre) {
        return comunaRepository.findByNombre(nombre);
    }
}
