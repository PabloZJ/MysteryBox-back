package com.example.mystery_box.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.example.mystery_box.model.Producto;
import java.util.List;
import com.example.mystery_box.model.Categoria;

@Repository
public interface ProductoRepository extends JpaRepository<Producto, Long> {

    List<Producto> findByCategoriaId(Long categoriaId);
    List<Producto> findByCategoria(Categoria categoria);
    Producto findByNombre(String nombre);
}