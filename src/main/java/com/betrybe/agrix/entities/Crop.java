package com.betrybe.agrix.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import java.time.LocalDate;
import java.util.List;

/**
 * Classe.
 */
@Entity
@Table(name = "crops")
public class Crop {

  /**
   * Atributos.
   */
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  @ManyToOne
  @JoinColumn(name = "farm_id")
  private Farm farm;
  private String name;
  @Column(name = "planted_area")
  private Double plantedArea;
  @Column(name = "harvest_date")
  private LocalDate harvestDate;
  @Column(name = "planted_date")
  private LocalDate plantedDate;
  @ManyToMany
  @JoinTable(
      name = "crop_fertilizer",
      joinColumns = @JoinColumn(name = "fertilizer_id"),
      inverseJoinColumns = @JoinColumn(name = "crop_id"),
      uniqueConstraints = @UniqueConstraint(columnNames = {"crop_id", "fertilizer_id"})
  )
  List<Fertilizer> fertilizers;

  /**
   * Construtor vazio.
   */
  public Crop() {

  }

  /**
   * Construtor completo.
   */

  public Crop(
      Long id,
      Farm farm,
      String name,
      Double plantedArea,
      LocalDate harvestDate,
      LocalDate plantedDate
  ) {
    this.id = id;
    this.farm = farm;
    this.name = name;
    this.plantedArea = plantedArea;
    this.harvestDate = harvestDate;
    this.plantedDate = plantedDate;
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

  public void setFarm(Farm farm) {
    this.farm = farm;
  }

  public Farm getFarm() {
    return this.farm;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getName() {
    return this.name;
  }

  public void setPlantedArea(Double plantedArea) {
    this.plantedArea = plantedArea;
  }

  public Double getPlantedArea() {
    return this.plantedArea;
  }

  public LocalDate getPlantedDate() {
    return plantedDate;
  }

  public void setPlantedDate(LocalDate plantedDate) {
    this.plantedDate = plantedDate;
  }

  public LocalDate getHarvestDate() {
    return harvestDate;
  }

  public void setHarvestDate(LocalDate harvestDate) {
    this.harvestDate = harvestDate;
  }

  public List<Fertilizer> getFertilizers() {
    return fertilizers;
  }

  public void setFertilizers(List<Fertilizer> fertilizers) {
    this.fertilizers = fertilizers;
  }

}
