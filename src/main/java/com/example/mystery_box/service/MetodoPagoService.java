package com.example.mystery_box.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.mystery_box.model.MetodoPago;
import com.example.mystery_box.model.Venta;
import com.example.mystery_box.repository.MetodoPagoRepository;
import com.example.mystery_box.repository.VentaRepository;
import jakarta.transaction.Transactional;

@Service
@Transactional
public class MetodoPagoService {

    @Autowired
    private MetodoPagoRepository metodoPagoRepository;

    @Autowired
    private VentaRepository ventaRepository;

    // Obtener todos los métodos de pago
    public List<MetodoPago> obtenerMetodosPago() {
        return metodoPagoRepository.findAll();
    }
    // Obtener método de pago por ID
    public MetodoPago obtenerMetodoPagoPorId(Long id) {
        return metodoPagoRepository.findById(id).orElse(null);
    }
    // Guardar método de pago
    public MetodoPago guardarMetodoPago(MetodoPago metodoPago) {
        return metodoPagoRepository.save(metodoPago);
    }
    // Actualizar (PUT)
    public MetodoPago actualizarMetodoPago(Long id, MetodoPago metodoPago) {
        MetodoPago metodoPagoExistente = metodoPagoRepository.findById(id).orElse(null);
        if (metodoPagoExistente != null) {
            metodoPagoExistente.setNombre(metodoPago.getNombre());
            return metodoPagoRepository.save(metodoPagoExistente);
        }
        return null;
    }
    // Actualizar (PATCH)
    public MetodoPago actualizarMetodoPagoParcial(Long id, MetodoPago metodoPago) {
        MetodoPago metodoPagoExistente = metodoPagoRepository.findById(id).orElse(null);
        if (metodoPagoExistente != null) {
            if (metodoPago.getNombre() != null) {
                metodoPagoExistente.setNombre(metodoPago.getNombre());
            }
            return metodoPagoRepository.save(metodoPagoExistente);
        }
        return null;
    }
    // Eliminar método de pago
    public void eliminarMetodoPago(Long id) {
        MetodoPago metodoPago = metodoPagoRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Método de pago no encontrado"));
        List<Venta> ventas = ventaRepository.findByMetodoPagoId(id);
        for (Venta venta : ventas) {
            ventaRepository.delete(venta);
        }
        metodoPagoRepository.delete(metodoPago);
    }
}
