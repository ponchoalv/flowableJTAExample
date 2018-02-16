package com.poncho.flowabledemo.accessControlDao.repositories;

import com.poncho.flowabledemo.accessControlDao.model.AccessControl;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AccessControlRepository extends JpaRepository<AccessControl, Long> {
}