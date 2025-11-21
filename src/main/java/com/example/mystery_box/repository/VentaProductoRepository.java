package com.example.mystery_box.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.mystery_box.model.VentaProducto;

@Repository
public interface VentaProductoRepository extends JpaRepository<VentaProducto, Long> {

    List<VentaProducto> findByVentaId(Long ventaId);
    List<VentaProducto> findByProductoId(Long productoId);
}