package com.poncho.flowabledemo.secondDatabaseExample.repositories;

import com.poncho.flowabledemo.secondDatabaseExample.model.Material;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SecondMaterialRepository extends JpaRepository<Material, Long> {
}