package com.betrybe.agrix.controllers.dto;

import com.betrybe.agrix.entities.Crop;
import com.fasterxml.jackson.annotation.JsonFormat;
import java.time.LocalDate;

/**
 * Classe.
 */
public record CropCreationDto(
    String name,
    Double plantedArea,
    @JsonFormat(pattern = "yyyy-MM-dd")
    LocalDate harvestDate,
    Long id,
    Long farmId,
    @JsonFormat(pattern = "yyyy-MM-dd")
    LocalDate plantedDate
) {

  /**
   * Dto to Entity.
   */
  public Crop toEntity() {
    Crop crop = new Crop();
    crop.setName(name);
    crop.setPlantedArea(plantedArea);
    crop.setHarvestDate(harvestDate);
    crop.setPlantedDate(plantedDate);
    return crop;
  }

}