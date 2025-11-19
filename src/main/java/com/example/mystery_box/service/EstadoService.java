package com.example.mystery_box.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.mystery_box.model.Estado;
import com.example.mystery_box.model.Venta;
import com.example.mystery_box.repository.EstadoRepository;
import com.example.mystery_box.repository.VentaRepository;
import jakarta.transaction.Transactional;

@Service
@Transactional
public class EstadoService {

    @Autowired
    private EstadoRepository estadoRepository;

    @Autowired
    private VentaRepository ventaRepository;
    
    // Obtener todos los estados
    public List<Estado> obtenerEstados() {
        return estadoRepository.findAll();
    }
    // Obtener estado por ID
    public Estado obtenerEstadoPorId(Long id) {
        return estadoRepository.findById(id).orElse(null);
    }
    // Guardar estado
    public Estado guardarEstado(Estado estado) {
        return estadoRepository.save(estado);
    }
    // Actualizar (PUT)
    public Estado actualizarEstado(Long id, Estado estado) {
        Estado estadoExistente = estadoRepository.findById(id).orElse(null);
        if (estadoExistente != null) {
            estadoExistente.setNombre(estado.getNombre());
            return estadoRepository.save(estadoExistente);
        }
        return null;
    }
    // Actualizar (PATCH)
    public Estado actualizarEstadoParcial(Long id, Estado estado) {
        Estado estadoExistente = estadoRepository.findById(id).orElse(null);
        if (estadoExistente != null) {
            if (estado.getNombre() != null) {
                estadoExistente.setNombre(estado.getNombre());
            }
            return estadoRepository.save(estadoExistente);
        }
        return null;
    }
    // Eliminar estado
    public void eliminarEstado(Long id) {
        Estado estado = estadoRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Estado no encontrado"));
        List<Venta> ventasAsociadas = ventaRepository.findByEstado(estado);
        if (!ventasAsociadas.isEmpty()) {
            throw new RuntimeException("No se puede eliminar este estado, está siendo usado en ventas.");
        }
        estadoRepository.delete(estado);
    }
    // Buscar estado por nombre
    public Estado obtenerEstadoPorNombre(String nombre) {
        return estadoRepository.findByNombre(nombre);
    }
}
