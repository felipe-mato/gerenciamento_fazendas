package com.betrybe.agrix.entities;


import com.betrybe.agrix.security.Role;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import java.util.List;
import java.util.Objects;

/**
 * Classe.
 */
@Entity
@Table(name = "farms")
public class Farm {

  /**
   * Atributos.
   */
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  private String name;
  private Double size;
  @OneToMany(mappedBy = "farm")
  private List<Crop> crops;

  /**
   * Construtor vazio.
   */
  public Farm() {

  }

  /**
   * Construtor completo.
   */

  public Farm(Long id, String name, Double size) {
    this.id = id;
    this.name = name;
    this.size = size;
  }

  /**
   * Metodos.
   */

  public void setId(Long id) {
    this.id = id;
  }

  public Long getId() {
    return this.id;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getName() {
    return this.name;
  }

  public void setSize(Double size) {
    this.size = size;
  }

  public Double getSize() {
    return this.size;
  }

  public List<Crop> getCrops() {
    return crops;
  }

  public void setCrops(List<Crop> crops) {
    this.crops = crops;
  }
}
