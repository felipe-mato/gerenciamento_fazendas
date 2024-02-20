package com.betrybe.agrix.controllers.dto;

import com.betrybe.agrix.entities.Farm;

/**
 * Classe.
 */
public record FarmDto(Long id, String name, Double size) {

  /**
   * Métodos. Entity to Dto.
   */
  public static FarmDto fromEntity(Farm farm) {
    return new FarmDto(
        farm.getId(),
        farm.getName(),
        farm.getSize()
    );
  }

  /**
   * Dto to Entity.
   */

  public Farm toEntity() {
    Farm farm = new Farm(id, name, size);
    farm.setId(id);
    farm.setName(name);
    farm.setSize(size);
    return farm;
  }
}