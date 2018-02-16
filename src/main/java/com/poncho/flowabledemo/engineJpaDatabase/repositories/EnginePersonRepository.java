package com.poncho.flowabledemo.engineJpaDatabase.repositories;

import com.poncho.flowabledemo.engineJpaDatabase.model.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EnginePersonRepository extends JpaRepository<Person, Long> {

    Person findByUsername(String username);

}