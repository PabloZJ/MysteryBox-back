package com.example.mystery_box.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.mystery_box.model.Imagen;
import com.example.mystery_box.repository.ImagenRepository;
import jakarta.transaction.Transactional;

@Service
@Transactional
public class ImagenService {

    @Autowired
    private ImagenRepository imagenRepository;

    // Obtener todas las imágenes
    public List<Imagen> obtenerImagenes() {
        return imagenRepository.findAll();
    }
    // Obtener imagen por ID
    public Imagen obtenerImagenPorId(Long id) {
        return imagenRepository.findById(id).orElse(null);
    }
    // Guardar imagen
    public Imagen guardarImagen(Imagen imagen) {
        return imagenRepository.save(imagen);
    }
    // Actualizar (PUT)
    public Imagen actualizarImagen(Long id, Imagen imagen) {
        Imagen imagenExistente = imagenRepository.findById(id).orElse(null);
        if (imagenExistente != null) {
            imagenExistente.setUrl(imagen.getUrl());
            imagenExistente.setProducto(imagen.getProducto());
            return imagenRepository.save(imagenExistente);
        }
        return null;
    }
    // Actualizar (PATCH)
    public Imagen actualizarImagenParcial(Long id, Imagen imagen) {
        Imagen imagenExistente = imagenRepository.findById(id).orElse(null);
        if (imagenExistente != null) {
            if (imagen.getUrl() != null) {
                imagenExistente.setUrl(imagen.getUrl());
            }
            if (imagen.getProducto() != null) {
                imagenExistente.setProducto(imagen.getProducto());
            }
            return imagenRepository.save(imagenExistente);
        }
        return null;
    }
    // Eliminar imagen
    public void eliminarImagen(Long id) {
        Imagen imagen = imagenRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Imagen no encontrada"));
        imagenRepository.delete(imagen);
    }
    // Obtener imágenes por producto
    public List<Imagen> obtenerImagenesPorProductoId(Long productoId) {
        return imagenRepository.findByProductoId(productoId);
    }
}
