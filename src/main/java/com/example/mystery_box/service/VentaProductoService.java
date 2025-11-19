package com.example.mystery_box.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.mystery_box.model.VentaProducto;
import com.example.mystery_box.repository.VentaProductoRepository;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class VentaProductoService {

    @Autowired
    private VentaProductoRepository ventaProductoRepository;

    // Obtener todos los VentaProducto
    public List<VentaProducto> obtenerVentaProductos() {
        return ventaProductoRepository.findAll();
    }
    // Obtener VentaProducto por ID
    public VentaProducto obtenerVentaProductoPorId(Long id) {
        return ventaProductoRepository.findById(id).orElse(null);
    }
    // Guardar VentaProducto
    public VentaProducto guardarVentaProducto(VentaProducto ventaProducto) {
        return ventaProductoRepository.save(ventaProducto);
    }
    // Actualizar (PUT)
    public VentaProducto actualizarVentaProducto(Long id, VentaProducto ventaProducto) {
        VentaProducto ventaProductoExistente = ventaProductoRepository.findById(id).orElse(null);
        if (ventaProductoExistente != null) {
            ventaProductoExistente.setVenta(ventaProducto.getVenta());
            ventaProductoExistente.setProducto(ventaProducto.getProducto());
            ventaProductoExistente.setCantidad(ventaProducto.getCantidad());
            return ventaProductoRepository.save(ventaProductoExistente);
        }
        return null;
    }
    // Actualizar (PATCH)
    public VentaProducto actualizarVentaProductoParcial(Long id, VentaProducto ventaProducto) {
        VentaProducto ventaProductoExistente = ventaProductoRepository.findById(id).orElse(null);
        if (ventaProductoExistente != null) {
            if (ventaProducto.getVenta() != null) {
                ventaProductoExistente.setVenta(ventaProducto.getVenta());
            }
            if (ventaProducto.getProducto() != null) {
                ventaProductoExistente.setProducto(ventaProducto.getProducto());
            }
            if (ventaProducto.getCantidad() != null) {
                ventaProductoExistente.setCantidad(ventaProducto.getCantidad());
            }
            return ventaProductoRepository.save(ventaProductoExistente);
        }
        return null;
    }
    // Eliminar VentaProducto
    public void eliminarVentaProducto(Long id) {
        VentaProducto ventaProducto = ventaProductoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("VentaProducto no encontrado"));
        ventaProductoRepository.delete(ventaProducto);
    }
    // Obtener por venta
    public List<VentaProducto> obtenerPorVentaId(Long ventaId) {
        return ventaProductoRepository.findByVentaId(ventaId);
    }
    // Obtener por producto
    public List<VentaProducto> obtenerPorProductoId(Long productoId) {
        return ventaProductoRepository.findByProductoId(productoId);
    }
}
