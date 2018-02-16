package com.poncho.flowabledemo.accessControlDao.repositories;

import com.poncho.flowabledemo.accessControlDao.model.Material;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MaterialRepository extends JpaRepository<Material, Long> {
}