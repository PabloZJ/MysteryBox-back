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
import com.example.mystery_box.model.MetodoPago;
import com.example.mystery_box.service.MetodoPagoService;

@RestController
@RequestMapping("/api/metodos-pago")
public class MetodoPagoController {

    @Autowired
    private MetodoPagoService metodoPagoService;

    @GetMapping
    public ResponseEntity<List<MetodoPago>> obtenerTodosLosMetodosPago() {
        List<MetodoPago> metodos = metodoPagoService.obtenerMetodosPago();
        if (metodos.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(metodos);
    }
    @GetMapping("/{id}")
    public ResponseEntity<MetodoPago> obtenerMetodoPagoPorId(@PathVariable Long id) {
        MetodoPago metodo = metodoPagoService.obtenerMetodoPagoPorId(id);
        if (metodo == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(metodo);
    }
    @PostMapping
    public ResponseEntity<MetodoPago> crearMetodoPago(@RequestBody MetodoPago metodoPago) {
        MetodoPago nuevoMetodo = metodoPagoService.guardarMetodoPago(metodoPago);
        return ResponseEntity.status(201).body(nuevoMetodo);
    }
    @PutMapping("/{id}")
    public ResponseEntity<MetodoPago> actualizarMetodoPago(@PathVariable Long id, @RequestBody MetodoPago metodoPago) {
        MetodoPago actualizado = metodoPagoService.actualizarMetodoPago(id, metodoPago);
        if (actualizado == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(actualizado);
    }
    @PatchMapping("/{id}")
    public ResponseEntity<MetodoPago> actualizarParcialMetodoPago(@PathVariable Long id, @RequestBody MetodoPago metodoPago) {
        MetodoPago actualizado = metodoPagoService.actualizarMetodoPagoParcial(id, metodoPago);
        if (actualizado == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(actualizado);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarMetodoPago(@PathVariable Long id) {
        metodoPagoService.eliminarMetodoPago(id);
        return ResponseEntity.noContent().build();
    }
}
