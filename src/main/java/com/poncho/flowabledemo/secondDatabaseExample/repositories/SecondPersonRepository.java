package com.poncho.flowabledemo.secondDatabaseExample.repositories;

import com.poncho.flowabledemo.secondDatabaseExample.model.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SecondPersonRepository extends JpaRepository<Person, Long> {

    Person findByUsername(String username);

}