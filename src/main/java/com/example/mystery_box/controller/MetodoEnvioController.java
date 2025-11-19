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
import com.example.mystery_box.model.MetodoEnvio;
import com.example.mystery_box.service.MetodoEnvioService;

@RestController
@RequestMapping("/api/metodos-envio")
public class MetodoEnvioController {

    @Autowired
    private MetodoEnvioService metodoEnvioService;

    @GetMapping
    public ResponseEntity<List<MetodoEnvio>> obtenerTodosLosMetodosEnvio() {
        List<MetodoEnvio> metodos = metodoEnvioService.obtenerMetodosEnvio();
        if (metodos.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(metodos);
    }
    @GetMapping("/{id}")
    public ResponseEntity<MetodoEnvio> obtenerMetodoEnvioPorId(@PathVariable Long id) {
        MetodoEnvio metodo = metodoEnvioService.obtenerMetodoEnvioPorId(id);
        if (metodo == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(metodo);
    }
    @PostMapping
    public ResponseEntity<MetodoEnvio> crearMetodoEnvio(@RequestBody MetodoEnvio metodoEnvio) {
        MetodoEnvio nuevoMetodo = metodoEnvioService.guardarMetodoEnvio(metodoEnvio);
        return ResponseEntity.status(201).body(nuevoMetodo);
    }
    @PutMapping("/{id}")
    public ResponseEntity<MetodoEnvio> actualizarMetodoEnvio(@PathVariable Long id, @RequestBody MetodoEnvio metodoEnvio) {
        MetodoEnvio actualizado = metodoEnvioService.actualizarMetodoEnvio(id, metodoEnvio);
        if (actualizado == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(actualizado);
    }
    @PatchMapping("/{id}")
    public ResponseEntity<MetodoEnvio> actualizarParcialMetodoEnvio(@PathVariable Long id, @RequestBody MetodoEnvio metodoEnvio) {
        MetodoEnvio actualizado = metodoEnvioService.actualizarMetodoEnvioParcial(id, metodoEnvio);
        if (actualizado == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(actualizado);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarMetodoEnvio(@PathVariable Long id) {
        metodoEnvioService.eliminarMetodoEnvio(id);
        return ResponseEntity.noContent().build();
    }
}
