package com.guilhermecastro.amafloraSpringApi.repositories;

import com.guilhermecastro.amafloraSpringApi.entities.Plant;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PlantRepository extends JpaRepository<Plant, Long> {


    Optional<Plant> findAllByOwnerId(Long id);
}
