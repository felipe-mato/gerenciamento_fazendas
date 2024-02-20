package com.betrybe.agrix.controllers.dto;

import com.betrybe.agrix.entities.Crop;
import com.fasterxml.jackson.annotation.JsonFormat;
import java.time.LocalDate;

/**
 * Classe.
 */
public record CropDto(
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
   * Entity to Dto.
   */
  public static CropDto fromEntity(Crop crop) {
    return new CropDto(
        crop.getName(),
        crop.getPlantedArea(),
        crop.getHarvestDate(),
        crop.getId(),
        crop.getFarm().getId(),
        crop.getPlantedDate()
    );
  }

  /**
   * Dto to Entity.
   */
  public Crop toEntity() {
    Crop crop = new Crop();
    crop.setName(name);
    crop.setPlantedArea(plantedArea);
    crop.setPlantedDate(plantedDate);
    crop.setHarvestDate(harvestDate);

    return crop;
  }
}