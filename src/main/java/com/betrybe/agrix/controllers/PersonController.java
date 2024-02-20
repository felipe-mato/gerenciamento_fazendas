package com.betrybe.agrix.controllers;

import com.betrybe.agrix.controllers.dto.PersonDto;
import com.betrybe.agrix.controllers.dto.PersonWithoutPasswordDto;
import com.betrybe.agrix.entities.Person;
import com.betrybe.agrix.services.PersonService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Classe.
 */
@RestController
@RequestMapping("/person")
public class PersonController {

  private final PersonService personService;

  @Autowired
  public PersonController(PersonService personService) {
    this.personService = personService;
  }

  /**
   * Cria uma person.
   */
  @PostMapping
  public ResponseEntity<PersonDto> create(@RequestBody PersonDto personDto) {
    Person newPerson = personService.create(personDto.toEntity());
    return ResponseEntity.status(HttpStatus.CREATED)
        .body(PersonDto.fromEntity(newPerson));
  }

  /**
   * Retorna um Frtilizer or 'id'.
   */
  @GetMapping
  @Secured("ADMIN")
  public List<PersonDto> findAll() {
    List<Person> personList = personService.findAll();

    return personList.stream()
        .map(PersonDto::fromEntity)
        .toList();
  }
}
