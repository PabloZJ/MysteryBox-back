package com.example.mystery_box.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.example.mystery_box.model.VentaProducto;
import com.example.mystery_box.service.VentaProductoService;

@RestController
@RequestMapping("/api/ventas-productos")
public class VentaProductoController {

    @Autowired
    private VentaProductoService ventaProductoService;

    @GetMapping
    public ResponseEntity<List<VentaProducto>> obtenerTodos() {
        List<VentaProducto> lista = ventaProductoService.obtenerVentaProductos();
        if (lista.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(lista);
    }
    @GetMapping("/{id}")
    public ResponseEntity<VentaProducto> obtenerPorId(@PathVariable Long id) {
        VentaProducto vp = ventaProductoService.obtenerVentaProductoPorId(id);
        if (vp == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(vp);
    }
    @PostMapping
    public ResponseEntity<VentaProducto> crear(@RequestBody VentaProducto ventaProducto) {
        VentaProducto creado = ventaProductoService.guardarVentaProducto(ventaProducto);
        return ResponseEntity.status(201).body(creado);
    }
    @PutMapping("/{id}")
    public ResponseEntity<VentaProducto> actualizar(@PathVariable Long id, @RequestBody VentaProducto ventaProducto) {
        VentaProducto actualizado = ventaProductoService.actualizarVentaProducto(id, ventaProducto);
        if (actualizado == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(actualizado);
    }
    @PatchMapping("/{id}")
    public ResponseEntity<VentaProducto> actualizarParcial(@PathVariable Long id, @RequestBody VentaProducto ventaProducto) {
        VentaProducto actualizado = ventaProductoService.actualizarVentaProductoParcial(id, ventaProducto);
        if (actualizado == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(actualizado);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        ventaProductoService.eliminarVentaProducto(id);
        return ResponseEntity.noContent().build();
    }
    @GetMapping("/venta/{ventaId}")
    public ResponseEntity<List<VentaProducto>> obtenerPorVenta(@PathVariable Long ventaId) {
        List<VentaProducto> lista = ventaProductoService.obtenerPorVentaId(ventaId);
        if (lista.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(lista);
    }
    @GetMapping("/producto/{productoId}")
    public ResponseEntity<List<VentaProducto>> obtenerPorProducto(@PathVariable Long productoId) {
        List<VentaProducto> lista = ventaProductoService.obtenerPorProductoId(productoId);
        if (lista.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(lista);
    }
}
