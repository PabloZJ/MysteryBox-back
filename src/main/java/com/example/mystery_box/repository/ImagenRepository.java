package com.example.mystery_box.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.example.mystery_box.model.Imagen;
import java.util.List;

@Repository
public interface ImagenRepository extends JpaRepository<Imagen, Long> {

    List<Imagen> findByProductoId(Long productoId);
}