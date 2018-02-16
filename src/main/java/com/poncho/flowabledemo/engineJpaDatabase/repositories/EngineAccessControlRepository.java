package com.poncho.flowabledemo.engineJpaDatabase.repositories;

import com.poncho.flowabledemo.engineJpaDatabase.model.AccessControl;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EngineAccessControlRepository extends JpaRepository<AccessControl, Long> {
}