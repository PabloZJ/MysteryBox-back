package com.example.mystery_box.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.mystery_box.model.Venta;
import com.example.mystery_box.model.Estado;
import com.example.mystery_box.repository.VentaRepository;
import jakarta.transaction.Transactional;

@Service
@Transactional
public class VentaService {

    @Autowired
    private VentaRepository ventaRepository;

    // Obtener todas las ventas
    public List<Venta> obtenerVentas() {
        return ventaRepository.findAll();
    }
    // Obtener venta por ID
    public Venta obtenerVentaPorId(Long id) {
        return ventaRepository.findById(id).orElse(null);
    }
    // Guardar venta
    public Venta guardarVenta(Venta venta) {
        return ventaRepository.save(venta);
    }
    // Actualizar (PUT)
    public Venta actualizarVenta(Long id, Venta venta) {
        Venta ventaExistente = ventaRepository.findById(id).orElse(null);
        if (ventaExistente != null) {
            ventaExistente.setUsuario(venta.getUsuario());
            ventaExistente.setEstado(venta.getEstado());
            ventaExistente.setMetodoPago(venta.getMetodoPago());
            ventaExistente.setMetodoEnvio(venta.getMetodoEnvio());
            ventaExistente.setFecha(venta.getFecha());
            ventaExistente.setTotal(venta.getTotal());
            ventaExistente.setProductos(venta.getProductos());
            return ventaRepository.save(ventaExistente);
        }
        return null;
    }
    // Actualizar parcial (PATCH)
    public Venta actualizarVentaParcial(Long id, Venta venta) {
        Venta ventaExistente = ventaRepository.findById(id).orElse(null);
        if (ventaExistente != null) {
            if (venta.getUsuario() != null){ 
                ventaExistente.setUsuario(venta.getUsuario());
            }
            if (venta.getEstado() != null){ 
                ventaExistente.setEstado(venta.getEstado());
            }
            if (venta.getMetodoPago() != null){
                ventaExistente.setMetodoPago(venta.getMetodoPago());
            }
            if (venta.getMetodoEnvio() != null){
                ventaExistente.setMetodoEnvio(venta.getMetodoEnvio());
            }
            if (venta.getFecha() != null){
                ventaExistente.setFecha(venta.getFecha());
            }
            if (venta.getTotal() != null){
                ventaExistente.setTotal(venta.getTotal());
            }
            if (venta.getProductos() != null){
                ventaExistente.setProductos(venta.getProductos());
            }
            return ventaRepository.save(ventaExistente);
        }
        return null;
    }
    // Eliminar venta
    public void eliminarVenta(Long id) {
        Venta venta = ventaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Venta no encontrada"));
        ventaRepository.delete(venta);
    }
    public List<Venta> obtenerVentasPorUsuarioId(Long usuarioId) {
        return ventaRepository.findByUsuarioId(usuarioId);
    }
    public List<Venta> obtenerVentasPorEstadoId(Long estadoId) {
        return ventaRepository.findByEstadoId(estadoId);
    }
    public List<Venta> obtenerVentasPorEstado(Estado estado) {
        return ventaRepository.findByEstado(estado);
    }
    public List<Venta> obtenerVentasPorMetodoPagoId(Long metodoPagoId) {
        return ventaRepository.findByMetodoPagoId(metodoPagoId);
    }
    public List<Venta> obtenerVentasPorMetodoEnvioId(Long metodoEnvioId) {
        return ventaRepository.findByMetodoEnvioId(metodoEnvioId);
    }
}
