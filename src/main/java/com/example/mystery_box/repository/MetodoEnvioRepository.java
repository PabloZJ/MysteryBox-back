package com.example.mystery_box.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.example.mystery_box.model.MetodoEnvio;

@Repository
public interface MetodoEnvioRepository extends JpaRepository<MetodoEnvio, Long> {
    MetodoEnvio findByNombre(String nombre);
}