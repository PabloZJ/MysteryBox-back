package com.example.mystery_box.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.example.mystery_box.model.Region;

@Repository
public interface RegionRepository extends JpaRepository<Region, Long> {

}