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
import com.example.mystery_box.model.Region;
import com.example.mystery_box.service.RegionService;

@RestController
@RequestMapping("/api/regiones")
public class RegionController {

    @Autowired
    private RegionService regionService;

    @GetMapping
    public ResponseEntity<List<Region>> obtenerTodasLasRegiones() {
        List<Region> regiones = regionService.obtenerRegiones();
        if (regiones.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(regiones);
    }
    @GetMapping("/{id}")
    public ResponseEntity<Region> obtenerRegionPorId(@PathVariable Long id) {
        Region region = regionService.obtenerRegionPorId(id);
        if (region == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(region);
    }
    @PostMapping
    public ResponseEntity<Region> crearRegion(@RequestBody Region region) {
        Region nuevaRegion = regionService.guardarRegion(region);
        return ResponseEntity.status(201).body(nuevaRegion);
    }
    @PutMapping("/{id}")
    public ResponseEntity<Region> actualizarRegion(@PathVariable Long id, @RequestBody Region region) {
        Region actualizada = regionService.actualizarRegion(id, region);
        if (actualizada == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(actualizada);
    }
    @PatchMapping("/{id}")
    public ResponseEntity<Region> actualizarParcialRegion(@PathVariable Long id, @RequestBody Region region) {
        Region actualizada = regionService.actualizarRegionParcial(id, region);
        if (actualizada == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(actualizada);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarRegion(@PathVariable Long id) {
        regionService.eliminarRegion(id);
        return ResponseEntity.noContent().build();
    }
}
