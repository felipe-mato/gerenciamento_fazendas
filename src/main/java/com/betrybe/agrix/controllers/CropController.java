package com.betrybe.agrix.controllers;

import com.betrybe.agrix.controllers.dto.CropDto;
import com.betrybe.agrix.controllers.dto.FertilizerDto;
import com.betrybe.agrix.entities.Crop;
import com.betrybe.agrix.entities.DateRangeRequest;
import com.betrybe.agrix.entities.Fertilizer;
import com.betrybe.agrix.services.FarmService;
import com.betrybe.agrix.services.exceptions.CropNotFoundException;
import com.betrybe.agrix.services.exceptions.FertilizerNotFoundException;
import java.time.LocalDate;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


/**
 * Classe.
 */
@RestController
@RequestMapping("/crops")
public class CropController {

  private FarmService farmService;

  @Autowired
  public CropController(FarmService farmService) {
    this.farmService = farmService;
  }

  /**
   * Retorna todas as plantações.
   */
  @GetMapping
  @Secured("ADMIN")
  public List<CropDto> findAll() {
    List<Crop> allCrops = farmService.getCrops();
    List<CropDto> response = allCrops.stream()
        .map(CropDto::fromEntity)
        .toList();
    return response;
  }

  /**
   * Retorna plantações por periodo de tempo (GET).
   */
  @GetMapping("/search")
  @Secured("ADMIN")
  public ResponseEntity<List<CropDto>> getCropsByDateInterval(
      @RequestParam LocalDate start,
      @RequestParam LocalDate end)
      throws CropNotFoundException {
    List<Crop> allCrops = farmService.getCropsByDateInterval(start, end);
    List<CropDto> data = allCrops.stream()
        .map(CropDto::fromEntity)
        .toList();
    return ResponseEntity.ok(data);
  }

  /**
   * Retorna plantações por periodo de tempo (POST).
   */
  @PostMapping("/search")
  @Secured("ADMIN")
  public ResponseEntity<List<CropDto>> getHarvestPeriodByRequestBody(
      @RequestBody DateRangeRequest dateRangeRequest) throws CropNotFoundException {
    LocalDate start = dateRangeRequest.getStart();
    LocalDate end = dateRangeRequest.getEnd();

    List<Crop> crops = farmService.getCropsByDateIntervalPost(start, end);
    List<CropDto> response = crops.stream()
        .map(CropDto::fromEntity)
        .toList();
    return ResponseEntity.ok(response);
  }

  /**
   * Retorna plantação por "id".
   */
  @GetMapping("/{id}")
  @Secured("ADMIN")
  public CropDto getCropById(@PathVariable("id") long id) throws CropNotFoundException {
    Crop crop = farmService.findCropById(id);
    return CropDto.fromEntity(crop);
  }

  /**
   * Retorna todos Fertilizers de uma crop.
   */
  @GetMapping("/{cropId}/fertilizers")
  @Secured("ADMIN")
  public ResponseEntity<List<FertilizerDto>> getCropFertilizers(@PathVariable Long cropId)
      throws CropNotFoundException {
    Crop crop = farmService.findCropById(cropId);
    List<Fertilizer> fertilizers = crop.getFertilizers();

    List<FertilizerDto> fertilizersDto = fertilizers.stream()
        .map(FertilizerDto::fromEntity)
        .toList();

    return ResponseEntity.ok(fertilizersDto);
  }

  /**
   * associateCropAndFertilizer.
   */
  @PostMapping("/{cropId}/fertilizers/{fertilizerId}")
  @Secured("ADMIN")
  public ResponseEntity<String> associateCropAndFertilizer(
      @PathVariable long cropId, @PathVariable long fertilizerId
  )
      throws CropNotFoundException, FertilizerNotFoundException {
    String retorno = farmService.associatePostAndTag(fertilizerId, cropId);

    return ResponseEntity.status(HttpStatus.CREATED).body(retorno);
  }
}