package com.betrybe.agrix.repositorys;

import com.betrybe.agrix.entities.Farm;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Class.
 */
@Repository
public interface FarmRepository extends JpaRepository<Farm, Long> {

}
