package com.betrybe.agrix.controllers.dto;

import com.betrybe.agrix.entities.Person;
import com.betrybe.agrix.security.Role;

/**
 * Classe.
 */
public record PersonDto(Long id, String username, String password, String role) {

  /**
   * fromEntity.
   */
  public static PersonDto fromEntity(Person person) {
    return new PersonDto(
        person.getId(),
        person.getUsername(),
        person.getPassword(),
        person.getRoles()
    );
  }

  /**
   * toEntity.
   */
  public Person toEntity() {
    Person person = new Person();
    person.setUsername(username);
    person.setPassword(password);
    person.setRoles(role);
    return person;
  }
}