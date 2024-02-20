package com.betrybe.agrix.repositorys;

import com.betrybe.agrix.entities.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Classe.
 */
@Repository
public interface PersonRepository extends JpaRepository<Person, Long> {

  Person findByUsername(String username);

}