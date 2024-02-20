package com.betrybe.agrix.repositorys;

import com.betrybe.agrix.entities.Crop;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Class.
 */
@Repository
public interface CropRepository extends JpaRepository<Crop, Long> {

  Optional<List<Crop>> findByHarvestDateBetween(LocalDate start, LocalDate end);
}
