package com.example.mystery_box.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.mystery_box.model.Categoria;
import com.example.mystery_box.model.Producto;
import com.example.mystery_box.repository.CategoriaRepository;
import com.example.mystery_box.repository.ProductoRepository;
import jakarta.transaction.Transactional;

@Service
@Transactional
public class CategoriaService {

    @Autowired
    private CategoriaRepository categoriaRepository;

    @Autowired
    private ProductoRepository productoRepository;

    // Obtener todas las categorías
    public List<Categoria> obtenerCategorias() {
        return categoriaRepository.findAll();
    }
    // Obtener una categoría por ID
    public Categoria obtenerCategoriaPorId(Long id) {
        return categoriaRepository.findById(id).orElse(null);
    }
    // Guardar categoría
    public Categoria guardarCategoria(Categoria categoria) {
        return categoriaRepository.save(categoria);
    }
    // Actualizar (PUT)
    public Categoria actualizarCategoria(Long id, Categoria categoria) {
        Categoria categoriaExistente = categoriaRepository.findById(id).orElse(null);
        if (categoriaExistente != null) {
            categoriaExistente.setNombre(categoria.getNombre());
            return categoriaRepository.save(categoriaExistente);
        }
        return null;
    }
    // Actualizar (PATCH)
    public Categoria actualizarCategoriaParcial(Long id, Categoria categoria) {
        Categoria categoriaExistente = categoriaRepository.findById(id).orElse(null);
        if (categoriaExistente != null) {
            if (categoria.getNombre() != null) {
                categoriaExistente.setNombre(categoria.getNombre());
            }
            return categoriaRepository.save(categoriaExistente);
        }
        return null;
    }
    // Eliminar categoría
    public void eliminarCategoria(Long id) {
        Categoria categoria = categoriaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Categoría no encontrada"));
        List<Producto> productos = productoRepository.findByCategoria(categoria);
        for (Producto producto : productos) {
            productoRepository.delete(producto);
        }
        categoriaRepository.delete(categoria);
    }
}
