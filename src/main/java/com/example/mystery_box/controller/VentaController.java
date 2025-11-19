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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.example.mystery_box.model.Estado;
import com.example.mystery_box.model.Venta;
import com.example.mystery_box.service.VentaService;

@RestController
@RequestMapping("/api/ventas")
public class VentaController {

    @Autowired
    private VentaService ventaService;

    @GetMapping
    public ResponseEntity<List<Venta>> obtenerVentas() {
        List<Venta> ventas = ventaService.obtenerVentas();
        if (ventas.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(ventas);
    }
    @GetMapping("/{id}")
    public ResponseEntity<Venta> obtenerVentaPorId(@PathVariable Long id) {
        Venta venta = ventaService.obtenerVentaPorId(id);
        if (venta == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(venta);
    }
    @PostMapping
    public ResponseEntity<Venta> crearVenta(@RequestBody Venta venta) {
        Venta nuevaVenta = ventaService.guardarVenta(venta);
        return ResponseEntity.status(201).body(nuevaVenta);
    }
    @PutMapping("/{id}")
    public ResponseEntity<Venta> actualizarVenta(@PathVariable Long id, @RequestBody Venta venta) {
        Venta ventaActualizada = ventaService.actualizarVenta(id, venta);
        if (ventaActualizada == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(ventaActualizada);
    }
    @PatchMapping("/{id}")
    public ResponseEntity<Venta> actualizarVentaParcial(@PathVariable Long id, @RequestBody Venta venta) {
        Venta ventaActualizada = ventaService.actualizarVentaParcial(id, venta);
        if (ventaActualizada == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(ventaActualizada);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarVenta(@PathVariable Long id) {
        ventaService.eliminarVenta(id);
        return ResponseEntity.noContent().build();
    }
    @GetMapping("/usuario/{usuarioId}")
    public ResponseEntity<List<Venta>> obtenerVentasPorUsuario(@PathVariable Long usuarioId) {
        List<Venta> ventas = ventaService.obtenerVentasPorUsuarioId(usuarioId);
        if (ventas.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(ventas);
    }
    @GetMapping("/estado/id/{estadoId}")
    public ResponseEntity<List<Venta>> obtenerVentasPorEstadoId(@PathVariable Long estadoId) {
        List<Venta> ventas = ventaService.obtenerVentasPorEstadoId(estadoId);
        if (ventas.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(ventas);
    }
    @GetMapping("/estado")
    public ResponseEntity<List<Venta>> obtenerVentasPorEstado(@RequestParam Estado estado) {
        List<Venta> ventas = ventaService.obtenerVentasPorEstado(estado);
        if (ventas.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(ventas);
    }
    @GetMapping("/metodo-pago/{metodoPagoId}")
    public ResponseEntity<List<Venta>> obtenerVentasPorMetodoPago(@PathVariable Long metodoPagoId) {
        List<Venta> ventas = ventaService.obtenerVentasPorMetodoPagoId(metodoPagoId);
        if (ventas.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(ventas);
    }
    @GetMapping("/metodo-envio/{metodoEnvioId}")
    public ResponseEntity<List<Venta>> obtenerVentasPorMetodoEnvio(@PathVariable Long metodoEnvioId) {
        List<Venta> ventas = ventaService.obtenerVentasPorMetodoEnvioId(metodoEnvioId);
        if (ventas.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(ventas);
    }
}
