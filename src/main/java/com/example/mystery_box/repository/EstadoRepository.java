package com.example.mystery_box.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.example.mystery_box.model.Estado;

@Repository
public interface EstadoRepository extends JpaRepository<Estado, Long> {

    Estado findByNombre(String nombre);
}