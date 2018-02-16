package com.poncho.flowabledemo.engineJpaDatabase.repositories;

import com.poncho.flowabledemo.engineJpaDatabase.model.Material;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EngineMaterialRepository extends JpaRepository<Material, Long> {
}