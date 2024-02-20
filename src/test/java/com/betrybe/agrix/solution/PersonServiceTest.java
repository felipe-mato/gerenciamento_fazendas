package com.betrybe.agrix.solution;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.betrybe.agrix.entities.Farm.Person;
import com.betrybe.agrix.ebytr.staff.exception.PersonNotFoundException;
import com.betrybe.agrix.ebytr.staff.repository.PersonRepository;
import com.betrybe.agrix.ebytr.staff.security.Role;
import com.betrybe.agrix.ebytr.staff.service.PersonService;
import java.util.Optional;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;


/**
 * Classe.
 */
@SpringBootTest
@ActiveProfiles("test")
public class PersonServiceTest {

  /**
   * Auto estancía.
   */
  @Autowired
  PersonService personService;

  /**
   * Necessário para o Mock.
   */
  @MockBean
  PersonRepository personRepository;

  /**
   * Faz o Mock.
   */
  public static Person mockPersonUser(Long userId, String userName, String userPassword) {
    Person mockedUser = new Person();
    mockedUser.setId(userId);
    mockedUser.setUsername(userName);
    mockedUser.setPassword(userPassword);
    mockedUser.setRole(Role.USER);

    return mockedUser;
  }

  /**
   * Testa metodo getPersonById.
   */
  @Test
  public void getPersonByIdTest() {
    // Arrange - Cria uma pessoa-mock
    Person mockedUser = mockPersonUser(1L, "Qui-Gon Jinn", "123456"); // Cria uma pessoa-mock
    // Indica como agir em determinada situação
    when(
        personRepository.findById(eq(1L))
    ).thenReturn(Optional.of(mockedUser));

    //Act - Chama a função que será testada
    Person foundPerson = personService.getPersonById(1L);
    verify(personRepository).findById(eq(1L));

    // Assert - Compara a pessoa-mock com os resultados da função testada
    assertEquals(foundPerson.getId(), mockedUser.getId());
    assertEquals(foundPerson.getUsername(), mockedUser.getUsername());
    assertEquals(foundPerson.getPassword(), mockedUser.getPassword());
  }


  /**
   * Testa getPersonById Exception.
   */
  @Test
  public void getPersonByIdExceptionTest() {
    // Arrange - Indica como agir em determinada situação
    when(
        personRepository.findById(eq(665L))
    ).thenReturn(Optional.empty());

    // Act & Assert
    assertThrows(PersonNotFoundException.class, () -> {
      personService.getPersonById(665L);
    });

    // Verifica se o método findById foi chamado com o "id" correto
    verify(personRepository).findById(eq(665L));
  }

  /**
   * Testa getPersonByUsername.
   */
  @Test
  public void getPersonByUsernameTest() {

    //Arrange
    Person mockedUser = mockPersonUser(1L, "Qui-Gon Jinn", "123456");
    when(
        personRepository.findByUsername(eq("Qui-Gon Jinn"))
    ).thenReturn(Optional.of(mockedUser));

    //Act
    Person foundPerson = personService.getPersonByUsername("Qui-Gon Jinn");
    verify(personRepository).findByUsername(eq("Qui-Gon Jinn"));

    // Assert
    assertEquals(foundPerson.getId(), mockedUser.getId());
    assertEquals(foundPerson.getUsername(), mockedUser.getUsername());
    assertEquals(foundPerson.getPassword(), mockedUser.getPassword());
  }

  /**
   * Testa getPersonByUsername Exception.
   */
  @Test
  public void getPersonByUsernameExceptionTest() {
    // Arrange
    when(
        personRepository.findByUsername(eq("Jaro Tapal"))
    ).thenReturn(Optional.empty());

    // Act & Assert
    assertThrows(PersonNotFoundException.class, () -> {
      personService.getPersonByUsername("Jaro Tapal");
    });

    // Verifica se o método findById foi chamado com o "ID" correto
    verify(personRepository).findByUsername(eq("Jaro Tapal"));
  }

  /**
   * Testa createPerson.
   */
  @Test
  public void createPersonTest() {
    // Arrange
    Person mockedUser = mockPersonUser(2L, "Jaro Tapal", "654321");
    when(personRepository.save(ArgumentMatchers.any(Person.class)))
        .thenReturn(mockedUser);

    // Act
    Person createdPerson = personService.create(mockedUser);

    // Assert
    assertEquals(mockedUser.getId(), createdPerson.getId());
    assertEquals(mockedUser.getUsername(), createdPerson.getUsername());
    assertEquals(mockedUser.getPassword(), createdPerson.getPassword());

    // Verifica se o método save foi chamado com o objeto correto
    verify(personRepository).save(ArgumentMatchers.any(Person.class));
  }
}

