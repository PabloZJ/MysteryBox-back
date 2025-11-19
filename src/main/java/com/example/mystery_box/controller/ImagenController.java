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
import com.example.mystery_box.model.Imagen;
import com.example.mystery_box.service.ImagenService;

@RestController
@RequestMapping("/api/imagenes")
public class ImagenController {

    @Autowired
    private ImagenService imagenService;

    @GetMapping
    public ResponseEntity<List<Imagen>> obtenerTodasLasImagenes() {
        List<Imagen> imagenes = imagenService.obtenerImagenes();
        if (imagenes.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(imagenes);
    }
    @GetMapping("/{id}")
    public ResponseEntity<Imagen> obtenerImagenPorId(@PathVariable Long id) {
        Imagen imagen = imagenService.obtenerImagenPorId(id);
        if (imagen == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(imagen);
    }
    @PostMapping
    public ResponseEntity<Imagen> crearImagen(@RequestBody Imagen imagen) {
        Imagen nuevaImagen = imagenService.guardarImagen(imagen);
        return ResponseEntity.status(201).body(nuevaImagen);
    }
    @PutMapping("/{id}")
    public ResponseEntity<Imagen> actualizarImagen(@PathVariable Long id, @RequestBody Imagen imagen) {
        Imagen imagenActualizada = imagenService.actualizarImagen(id, imagen);
        if (imagenActualizada == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(imagenActualizada);
    }
    @PatchMapping("/{id}")
    public ResponseEntity<Imagen> actualizarParcialImagen(@PathVariable Long id, @RequestBody Imagen imagen) {
        Imagen imagenActualizada = imagenService.actualizarImagenParcial(id, imagen);
        if (imagenActualizada == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(imagenActualizada);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarImagen(@PathVariable Long id) {
        imagenService.eliminarImagen(id);
        return ResponseEntity.noContent().build();
    }
    @GetMapping("/producto/{productoId}")
    public ResponseEntity<List<Imagen>> obtenerImagenesPorProducto(@PathVariable Long productoId) {
        List<Imagen> imagenes = imagenService.obtenerImagenesPorProductoId(productoId);
        if (imagenes.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(imagenes);
    }
}
