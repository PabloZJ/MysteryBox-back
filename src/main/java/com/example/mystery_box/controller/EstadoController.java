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
import com.example.mystery_box.model.Estado;
import com.example.mystery_box.service.EstadoService;

@RestController
@RequestMapping("/api/estados")
public class EstadoController {

    @Autowired
    private EstadoService estadoService;

    @GetMapping
    public ResponseEntity<List<Estado>> obtenerTodosLosEstados() {
        List<Estado> estados = estadoService.obtenerEstados();
        if (estados.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(estados);
    }
    @GetMapping("/{id}")
    public ResponseEntity<Estado> obtenerEstadoPorId(@PathVariable Long id) {
        Estado estado = estadoService.obtenerEstadoPorId(id);
        if (estado == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(estado);
    }
    @PostMapping
    public ResponseEntity<Estado> crearEstado(@RequestBody Estado estado) {
        Estado nuevoEstado = estadoService.guardarEstado(estado);
        return ResponseEntity.status(201).body(nuevoEstado);
    }
    @PutMapping("/{id}")
    public ResponseEntity<Estado> actualizarEstado(@PathVariable Long id, @RequestBody Estado estado) {
        Estado estadoActualizado = estadoService.actualizarEstado(id, estado);
        if (estadoActualizado == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(estadoActualizado);
    }
    @PatchMapping("/{id}")
    public ResponseEntity<Estado> actualizarParcialEstado(@PathVariable Long id, @RequestBody Estado estado) {
        Estado estadoActualizado = estadoService.actualizarEstadoParcial(id, estado);
        if (estadoActualizado == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(estadoActualizado);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarEstado(@PathVariable Long id) {
        try {
            estadoService.eliminarEstado(id);
            return ResponseEntity.noContent().build();
        } catch (RuntimeException e) {
            return ResponseEntity.status(400).build();
        }
    }
    @GetMapping("/nombre/{nombre}")
    public ResponseEntity<Estado> obtenerEstadoPorNombre(@PathVariable String nombre) {
        Estado estado = estadoService.obtenerEstadoPorNombre(nombre);
        if (estado == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(estado);
    }
}