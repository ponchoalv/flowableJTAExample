package com.poncho.flowabledemo.secondDatabaseExample.repositories;

import com.poncho.flowabledemo.secondDatabaseExample.model.AccessControl;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SecondAccessControlRepository extends JpaRepository<AccessControl, Long> {
}