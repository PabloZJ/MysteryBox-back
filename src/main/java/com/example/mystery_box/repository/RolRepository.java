package com.example.mystery_box.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.example.mystery_box.model.Rol;

@Repository
public interface RolRepository extends JpaRepository<Rol, Long> {

    Rol findByNombre(String nombre);
}