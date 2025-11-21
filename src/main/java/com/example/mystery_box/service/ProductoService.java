package com.example.mystery_box.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.mystery_box.model.Categoria;
import com.example.mystery_box.model.Imagen;
import com.example.mystery_box.model.Producto;
import com.example.mystery_box.model.VentaProducto;
import com.example.mystery_box.repository.ImagenRepository;
import com.example.mystery_box.repository.ProductoRepository;
import com.example.mystery_box.repository.VentaProductoRepository;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class ProductoService {

    @Autowired
    private ProductoRepository productoRepository;

    @Autowired
    private ImagenRepository imagenRepository;

    @Autowired
    private VentaProductoRepository ventaProductoRepository;

    // Obtener todos los productos
    public List<Producto> obtenerProductos() {
        return productoRepository.findAll();
    }
    // Obtener producto por ID
    public Producto obtenerProductoPorId(Long id) {
        return productoRepository.findById(id).orElse(null);
    }
    // Guardar producto
    public Producto guardarProducto(Producto producto) {
        return productoRepository.save(producto);
    }
    // Actualizar (PUT)
    public Producto actualizarProducto(Long id, Producto producto) {
        Producto productoExistente = productoRepository.findById(id).orElse(null);
        if (productoExistente != null) {
            productoExistente.setNombre(producto.getNombre());
            productoExistente.setDescripcion(producto.getDescripcion());
            productoExistente.setPrecio(producto.getPrecio());
            productoExistente.setCategoria(producto.getCategoria());
            return productoRepository.save(productoExistente);
        }
        return null;
    }
    // Actualizar (PATCH)
    public Producto actualizarProductoParcial(Long id, Producto producto) {
        Producto productoExistente = productoRepository.findById(id).orElse(null);
        if (productoExistente != null) {
            if (producto.getNombre() != null) {
                productoExistente.setNombre(producto.getNombre());
            }
            if (producto.getDescripcion() != null) {
                productoExistente.setDescripcion(producto.getDescripcion());
            }
            if (producto.getPrecio() != null) {
                productoExistente.setPrecio(producto.getPrecio());
            }
            if (producto.getCategoria() != null) {
                productoExistente.setCategoria(producto.getCategoria());
            }
            return productoRepository.save(productoExistente);
        }
        return null;
    }
    // Eliminar producto
    public void eliminarProducto(Long id) {
        Producto producto = productoRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Producto no encontrado"));
        List<Imagen> imagenes = imagenRepository.findByProductoId(id);
        for (Imagen img : imagenes) {
            imagenRepository.delete(img);
        }
        List<VentaProducto> ventasProd = ventaProductoRepository.findByProductoId(id);
        for (VentaProducto vp : ventasProd) {
            ventaProductoRepository.delete(vp);
        }
        productoRepository.delete(producto);
    }
    // Obtener productos por categoría
    public List<Producto> obtenerProductosPorCategoriaId(Long categoriaId) {
        return productoRepository.findByCategoriaId(categoriaId);
    }
    public List<Producto> obtenerProductosPorCategoria(Categoria categoria) {
        return productoRepository.findByCategoria(categoria);
    }
}