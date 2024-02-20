package com.betrybe.agrix.controllers;

import com.betrybe.agrix.controllers.dto.FertilizerDto;
import com.betrybe.agrix.entities.Fertilizer;
import com.betrybe.agrix.service.FertilizerService;
import com.betrybe.agrix.services.exceptions.FertilizerNotFoundException;
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
import org.springframework.web.bind.annotation.RestController;

/**
 * Classe.
 */
@RestController
@RequestMapping("/fertilizers")
public class FertilizerController {

  private final FertilizerService fertilizerService;

  @Autowired
  public FertilizerController(FertilizerService fertilizerService) {
    this.fertilizerService = fertilizerService;
  }

  /**
   * Cria um Fertilizer.
   */
  @PostMapping
  @Secured("ADMIN")
  public ResponseEntity<FertilizerDto> create(@RequestBody FertilizerDto fertilizer) {
    Fertilizer savedFertilizer = fertilizerService.create(fertilizer.toEntity());
    return ResponseEntity.status(HttpStatus.CREATED)
        .body(FertilizerDto.fromEntity(savedFertilizer));
  }

  /**
   * Retorna todos os Fertilizers.
   */
  @GetMapping
  @Secured("ADMIN")
  public ResponseEntity<List<FertilizerDto>> findAll() {
    List<Fertilizer> allFertilizers = fertilizerService.findAll();
    List<FertilizerDto> data = allFertilizers.stream()
        .map(FertilizerDto::fromEntity)
        .toList();
    return ResponseEntity.ok(data);
  }

  /**
   * Retorna um Frtilizer or 'id'.
   */
  @GetMapping("/{fertilizerId}")
  @Secured("ADMIN")
  public ResponseEntity<FertilizerDto> findById(@PathVariable("fertilizerId") Long id)
      throws FertilizerNotFoundException {
    Fertilizer fertilizer = fertilizerService.findById(id);
    return ResponseEntity.ok(FertilizerDto.fromEntity(fertilizer));
  }
}