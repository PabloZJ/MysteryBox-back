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
import com.example.mystery_box.model.Direccion;
import com.example.mystery_box.service.DireccionService;

@RestController
@RequestMapping("/api/direcciones")
public class DireccionController {

    @Autowired
    private DireccionService direccionService;

    @GetMapping
    public ResponseEntity<List<Direccion>> obtenerTodasLasDirecciones() {
        List<Direccion> direcciones = direccionService.obtenerDirecciones();
        if (direcciones.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(direcciones);
    }
    @GetMapping("/{id}")
    public ResponseEntity<Direccion> obtenerDireccionPorId(@PathVariable Long id) {
        Direccion direccion = direccionService.obtenerDireccionPorId(id);
        if (direccion == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(direccion);
    }
    @PostMapping
    public ResponseEntity<Direccion> crearDireccion(@RequestBody Direccion direccion) {
        Direccion direccionCreada = direccionService.guardarDireccion(direccion);
        return ResponseEntity.status(201).body(direccionCreada);
    }
    @PutMapping("/{id}")
    public ResponseEntity<Direccion> actualizarDireccion(@PathVariable Long id, @RequestBody Direccion direccion) {
        Direccion direccionActualizada = direccionService.actualizarDireccion(id, direccion);
        if (direccionActualizada == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(direccionActualizada);
    }
    @PatchMapping("/{id}")
    public ResponseEntity<Direccion> actualizarDireccionParcial(@PathVariable Long id, @RequestBody Direccion direccion) {
        Direccion direccionActualizada = direccionService.actualizarDireccionParcial(id, direccion);
        if (direccionActualizada == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(direccionActualizada);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarDireccion(@PathVariable Long id) {
        direccionService.eliminarDireccion(id);
        return ResponseEntity.noContent().build();
    }
    @GetMapping("/usuario/{usuarioId}")
    public ResponseEntity<List<Direccion>> obtenerDireccionesPorUsuario(@PathVariable Long usuarioId) {
        List<Direccion> direcciones = direccionService.obtenerDireccionesPorUsuarioId(usuarioId);
        if (direcciones.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(direcciones);
    }
    @GetMapping("/comuna/{comunaId}")
    public ResponseEntity<List<Direccion>> obtenerDireccionesPorComuna(@PathVariable Long comunaId) {
        List<Direccion> direcciones = direccionService.obtenerDireccionesPorComunaId(comunaId);
        if (direcciones.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(direcciones);
    }
}
