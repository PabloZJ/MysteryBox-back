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
import com.example.mystery_box.model.Comuna;
import com.example.mystery_box.service.ComunaService;

@RestController
@RequestMapping("/api/comunas")
public class ComunaController {

    @Autowired
    private ComunaService comunaService;

    @GetMapping
    public ResponseEntity<List<Comuna>> obtenerTodasLasComunas() {
        List<Comuna> comunas = comunaService.obtenerComunas();
        if (comunas.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(comunas);
    }
    @GetMapping("/{id}")
    public ResponseEntity<Comuna> obtenerComunaPorId(@PathVariable Long id) {
        Comuna comuna = comunaService.obtenerComunaPorId(id);
        if (comuna == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(comuna);
    }
    @PostMapping
    public ResponseEntity<Comuna> crearComuna(@RequestBody Comuna comuna) {
        Comuna comunaCreada = comunaService.guardarComuna(comuna);
        return ResponseEntity.status(201).body(comunaCreada);
    }
    @PutMapping("/{id}")
    public ResponseEntity<Comuna> actualizarComuna(@PathVariable Long id, @RequestBody Comuna comuna) {
        Comuna comunaActualizada = comunaService.actualizarComuna(id, comuna);
        if (comunaActualizada == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(comunaActualizada);
    }
    @PatchMapping("/{id}")
    public ResponseEntity<Comuna> actualizarComunaParcial(@PathVariable Long id, @RequestBody Comuna comuna) {
        Comuna comunaActualizada = comunaService.actualizarComunaParcial(id, comuna);
        if (comunaActualizada == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(comunaActualizada);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarComuna(@PathVariable Long id) {
        comunaService.eliminarComuna(id);
        return ResponseEntity.noContent().build();
    }
    @GetMapping("/region/{regionId}")
    public ResponseEntity<List<Comuna>> obtenerComunasPorRegion(@PathVariable Long regionId) {
        List<Comuna> comunas = comunaService.obtenerComunasPorRegionId(regionId);
        if (comunas.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(comunas);
    }
    @GetMapping("/nombre/{nombre}")
    public ResponseEntity<Comuna> obtenerComunaPorNombre(@PathVariable String nombre) {
        Comuna comuna = comunaService.obtenerComunaPorNombre(nombre);
        if (comuna == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(comuna);
    }
}
