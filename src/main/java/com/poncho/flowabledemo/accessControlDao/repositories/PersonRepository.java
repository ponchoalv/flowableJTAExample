package com.poncho.flowabledemo.accessControlDao.repositories;

import com.poncho.flowabledemo.accessControlDao.model.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PersonRepository extends JpaRepository<Person, Long> {

    Person findByUsername(String username);

}