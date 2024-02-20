package com.betrybe.agrix.service;

import com.betrybe.agrix.entities.Fertilizer;
import com.betrybe.agrix.repositorys.FertilizerRepository;
import com.betrybe.agrix.services.exceptions.FertilizerNotFoundException;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Classe.
 */
@Service
public class FertilizerService {

  private final FertilizerRepository fertilizerRepository;

  @Autowired
  public FertilizerService(FertilizerRepository fertilizerRepository) {
    this.fertilizerRepository = fertilizerRepository;
  }

  /**
   * Cria um Fertilizer.
   */
  public Fertilizer create(Fertilizer fertilizer) {
    return fertilizerRepository.save(fertilizer);
  }

  ;

  /**
   * Retorna todos Fertilizers.
   */
  public List<Fertilizer> findAll() {
    return fertilizerRepository.findAll();
  }

  /**
   * Retorna Fetilizer por "id".
   */
  public Fertilizer findById(Long id) throws FertilizerNotFoundException {
    return fertilizerRepository.findById(id).orElseThrow(FertilizerNotFoundException::new);
  }
}