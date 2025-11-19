package com.example.mystery_box.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.mystery_box.model.Region;
import com.example.mystery_box.model.Comuna;
import com.example.mystery_box.model.Direccion;
import com.example.mystery_box.repository.RegionRepository;
import com.example.mystery_box.repository.ComunaRepository;
import com.example.mystery_box.repository.DireccionRepository;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class RegionService {

    @Autowired
    private RegionRepository regionRepository;

    @Autowired
    private ComunaRepository comunaRepository;

    @Autowired
    private DireccionRepository direccionRepository;

    // Obtener todas las regiones
    public List<Region> obtenerRegiones() {
        return regionRepository.findAll();
    }
    // Obtener región por ID
    public Region obtenerRegionPorId(Long id) {
        return regionRepository.findById(id).orElse(null);
    }
    // Guardar región
    public Region guardarRegion(Region region) {
        return regionRepository.save(region);
    }
    // Actualizar (PUT)
    public Region actualizarRegion(Long id, Region region) {
        Region regionExistente = regionRepository.findById(id).orElse(null);
        if (regionExistente != null) {
            regionExistente.setNombre(region.getNombre());
            return regionRepository.save(regionExistente);
        }
        return null;
    }
    // Actualizar (PATCH)
    public Region actualizarRegionParcial(Long id, Region region) {
        Region regionExistente = regionRepository.findById(id).orElse(null);
        if (regionExistente != null) {
            if (region.getNombre() != null) {
                regionExistente.setNombre(region.getNombre());
            }
            return regionRepository.save(regionExistente);
        }
        return null;
    }
    // Eliminar región
    public void eliminarRegion(Long id) {
        Region region = regionRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Región no encontrada"));
        List<Comuna> comunas = comunaRepository.findByRegionId(id);
        for (Comuna comuna : comunas) {
            List<Direccion> direcciones = direccionRepository.findByComunaId(comuna.getId());
            for (Direccion direccion : direcciones) {
                direccionRepository.delete(direccion);
            }
            comunaRepository.delete(comuna);
        }
        regionRepository.delete(region);
    }
}
