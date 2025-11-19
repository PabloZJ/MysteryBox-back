package com.example.mystery_box.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.example.mystery_box.model.Direccion;
import java.util.List;

@Repository
public interface DireccionRepository extends JpaRepository<Direccion, Long> {

    List<Direccion> findByUsuarioId(Long usuarioId);
    List<Direccion> findByComunaId(Long comunaId);
}