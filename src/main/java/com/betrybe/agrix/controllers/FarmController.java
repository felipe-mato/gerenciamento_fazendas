package com.betrybe.agrix.controllers;

import com.betrybe.agrix.controllers.dto.CropCreationDto;
import com.betrybe.agrix.controllers.dto.CropDto;
import com.betrybe.agrix.controllers.dto.FarmDto;
import com.betrybe.agrix.entities.Crop;
import com.betrybe.agrix.entities.Farm;
import com.betrybe.agrix.services.FarmService;
import com.betrybe.agrix.services.exceptions.CropNotFoundException;
import com.betrybe.agrix.services.exceptions.FarmNotFoundException;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

/**
 * Classe.
 */
@RestController
@RequestMapping("/farms")
public class FarmController {

  private final FarmService farmService;

  @Autowired
  FarmController(FarmService farmService) {
    this.farmService = farmService;
  }

  /**
   * Traz todas as fazendas.
   */
  @GetMapping
  @Secured("ADMIN")
  public ResponseEntity<List<FarmDto>> findAll() {
    List<Farm> farms = farmService.findAll();
    List<FarmDto> farmDtos = farms.stream()
        .map(FarmDto::fromEntity)
        .toList();
    return ResponseEntity.ok(farmDtos);
  }

  /**
   * Traz uma fazenda por 'id'.
   */
  @GetMapping("/{id}")
  @Secured("ADMIN")
  public ResponseEntity<FarmDto> findById(@PathVariable("id") long id)
      throws FarmNotFoundException {
    Farm farm = farmService.findById(id);
    FarmDto farmDto = FarmDto.fromEntity(farm);
    return ResponseEntity.ok(farmDto);
  }

  /**
   * Cria uma fazenda.
   */
  @PostMapping
  @ResponseStatus(HttpStatus.CREATED)
  @Secured("ADMIN")
  public ResponseEntity<FarmDto> save(@RequestBody FarmDto farm) {
    Farm savedFarm = farmService.createFarm(farm.toEntity());
    FarmDto savedFarmDto = FarmDto.fromEntity(savedFarm);
    return ResponseEntity.status(HttpStatus.CREATED).body(savedFarmDto);
  }

  /**
   * Cria uma plantação.
   */
  @PostMapping("/{farmId}/crops")
  @Secured("ADMIN")
  public ResponseEntity<CropDto> createCrop(@RequestBody CropCreationDto cropCreationDto,
      @PathVariable("farmId") Long farmId) throws FarmNotFoundException {

    Crop createdCrop = farmService.createCrop(cropCreationDto.toEntity(), farmId);
    CropDto responseDto = CropDto.fromEntity(createdCrop);

    return ResponseEntity.status(HttpStatus.CREATED).body(responseDto);
  }

  /**
   * Retorna todas as plantações de uma fazenda.
   */
  @GetMapping("/{id}/crops")
  @Secured("ADMIN")
  public ResponseEntity<List<CropDto>> getFarmCrop(@PathVariable("id") Long id)
      throws FarmNotFoundException, CropNotFoundException {
    List<Crop> crops = farmService.getFarmCrop(id);
    List<CropDto> data = crops.stream()
        .map(CropDto::fromEntity)
        .toList();
    return ResponseEntity.ok(data);
  }
}
