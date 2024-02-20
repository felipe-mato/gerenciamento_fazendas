package com.betrybe.agrix.repositorys;

import com.betrybe.agrix.entities.Fertilizer;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Classe.
 */
public interface FertilizerRepository extends JpaRepository<Fertilizer, Long> {

}