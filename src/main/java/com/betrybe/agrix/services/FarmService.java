package com.betrybe.agrix.services;

import com.betrybe.agrix.entities.Crop;
import com.betrybe.agrix.entities.Farm;
import com.betrybe.agrix.entities.Fertilizer;
import com.betrybe.agrix.repositorys.CropRepository;
import com.betrybe.agrix.repositorys.FarmRepository;
import com.betrybe.agrix.services.exceptions.CropNotFoundException;
import com.betrybe.agrix.services.exceptions.FarmNotFoundException;
import com.betrybe.agrix.services.exceptions.FertilizerNotFoundException;
import java.time.LocalDate;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Classe.
 */
@Service
public class FarmService {

  private final FarmRepository farmRepository;
  private final CropRepository cropRepository;
  private final com.betrybe.agrix.service.FertilizerService fertilizerService;

  /**
   * Autowired.
   */
  @Autowired
  public FarmService(
      FarmRepository farmRepository,
      CropRepository cropRepository,
      com.betrybe.agrix.service.FertilizerService fertilizerService
  ) {
    this.farmRepository = farmRepository;
    this.cropRepository = cropRepository;
    this.fertilizerService = fertilizerService;
  }

  /**
   * Cria uma fazenda.
   */
  public Farm createFarm(Farm farm) {
    return farmRepository.save(farm);
  }

  /**
   * Retorna todas as fazendas.
   */
  public List<Farm> findAll() {
    return farmRepository.findAll();
  }

  /**
   * Retorna uma fazenda pelo 'id'.
   */
  public Farm findById(long id) throws FarmNotFoundException {
    return farmRepository.findById(id)
        .orElseThrow(FarmNotFoundException::new);
  }

  /**
   * updateFarm.
   */
  public Farm updateFarm(Long id, Farm farm) throws FarmNotFoundException {
    Farm farmFromDb = farmRepository.findById(id)
        .orElseThrow(FarmNotFoundException::new);

    farmFromDb.setName(farm.getName());
    farmFromDb.setSize(farm.getSize());

    return farmRepository.save(farmFromDb);
  }

  /**
   * Cria uma plantação.
   */
  public Crop createCrop(Crop crop, Long farmId) throws FarmNotFoundException {
    Farm farm = findById(farmId);
    crop.setFarm(farm);
    return cropRepository.save(crop);
  }

  /**
   * Retorna todas as plantações de uma fazenda.
   */
  public List<Crop> getFarmCrop(long farmId) throws FarmNotFoundException, CropNotFoundException {
    Farm farm = farmRepository.findById(farmId)
        .orElseThrow(FarmNotFoundException::new);

    List<Crop> crop = farm.getCrops();
    if (crop == null) {
      throw new CropNotFoundException();
    }
    return crop;
  }

  public List<Crop> getCrops() {
    return cropRepository.findAll();
  }

  /**
   * Retorna uma plantação por 'id'.
   */
  public List<Crop> getCropsById(Long farmId) throws FarmNotFoundException {
    Farm farm = findById(farmId);
    return farm.getCrops();
  }

  /**
   * Retorna uma plantação pelo 'id'.
   */
  public Crop findCropById(long id) throws CropNotFoundException {
    return cropRepository.findById(id)
        .orElseThrow(CropNotFoundException::new);
  }

  /**
   * Retorna as plantações de um determinado priodo de tempo.
   */
  public List<Crop> getCropsByDateInterval(LocalDate start, LocalDate end)
      throws CropNotFoundException {
    return cropRepository.findByHarvestDateBetween(start, end)
        .orElseThrow(CropNotFoundException::new);
  }

  /**
   * Retorna as plantações de um determinado período POST.
   */
  public List<Crop> getCropsByDateIntervalPost(LocalDate start, LocalDate end) {
    return cropRepository.findByHarvestDateBetween(start, end)
        .orElse(Collections.emptyList());
  }


  /**
   * Associa.
   */
  public String associatePostAndTag(long fertilizerId, long cropId)
      throws FertilizerNotFoundException, CropNotFoundException {
    Fertilizer fertilizer = fertilizerService.findById(fertilizerId);
    Crop crop = findCropById(cropId);

    crop.getFertilizers().add(fertilizer);

    cropRepository.save(crop);
    return "Fertilizante e plantação associados com sucesso!";
  }
}

