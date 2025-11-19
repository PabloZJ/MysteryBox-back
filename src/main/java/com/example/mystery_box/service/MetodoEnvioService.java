package com.example.mystery_box.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.mystery_box.model.MetodoEnvio;
import com.example.mystery_box.model.Venta;
import com.example.mystery_box.repository.MetodoEnvioRepository;
import com.example.mystery_box.repository.VentaRepository;
import jakarta.transaction.Transactional;

@Service
@Transactional
public class MetodoEnvioService {

    @Autowired
    private MetodoEnvioRepository metodoEnvioRepository;

    @Autowired
    private VentaRepository ventaRepository;

    // Obtener todos los métodos de envío
    public List<MetodoEnvio> obtenerMetodosEnvio() {
        return metodoEnvioRepository.findAll();
    }
    // Obtener método de envío por ID
    public MetodoEnvio obtenerMetodoEnvioPorId(Long id) {
        return metodoEnvioRepository.findById(id).orElse(null);
    }
    // Guardar método de envío
    public MetodoEnvio guardarMetodoEnvio(MetodoEnvio metodoEnvio) {
        return metodoEnvioRepository.save(metodoEnvio);
    }
    // Actualizar (PUT)
    public MetodoEnvio actualizarMetodoEnvio(Long id, MetodoEnvio metodoEnvio) {
        MetodoEnvio metodoEnvioExistente = metodoEnvioRepository.findById(id).orElse(null);
        if (metodoEnvioExistente != null) {
            metodoEnvioExistente.setNombre(metodoEnvio.getNombre());
            return metodoEnvioRepository.save(metodoEnvioExistente);
        }
        return null;
    }
    // Actualizar (PATCH)
    public MetodoEnvio actualizarMetodoEnvioParcial(Long id, MetodoEnvio metodoEnvio) {
        MetodoEnvio metodoEnvioExistente = metodoEnvioRepository.findById(id).orElse(null);
        if (metodoEnvioExistente != null) {
            if (metodoEnvio.getNombre() != null) {
                metodoEnvioExistente.setNombre(metodoEnvio.getNombre());
            }
            return metodoEnvioRepository.save(metodoEnvioExistente);
        }
        return null;
    }
    // Eliminar método de envío
    public void eliminarMetodoEnvio(Long id) {
        MetodoEnvio metodoEnvio = metodoEnvioRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Método de envío no encontrado"));
        List<Venta> ventas = ventaRepository.findByMetodoEnvioId(id);
        for (Venta venta : ventas) {
            ventaRepository.delete(venta);
        }
        metodoEnvioRepository.delete(metodoEnvio);
    }
}

