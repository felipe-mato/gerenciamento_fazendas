package com.betrybe.agrix.controllers.dto;

import com.betrybe.agrix.entities.Person;
import com.betrybe.agrix.security.Role;

/**
 * Classe.
 */
public record PersonWithoutPasswordDto(Long id, String username, String role) {

  /**
   * fromEntity.
   */
  public static PersonWithoutPasswordDto fromEntity(Person person) {
    return new PersonWithoutPasswordDto(
        person.getId(),
        person.getUsername(),
        person.getRoles()
    );
  }

}