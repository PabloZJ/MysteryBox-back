package com.example.mystery_box.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.example.mystery_box.model.Comuna;
import java.util.List;

@Repository
public interface ComunaRepository extends JpaRepository<Comuna, Long> {

    List<Comuna> findByRegionId(Long regionId);
    Comuna findByNombre(String nombre);
}