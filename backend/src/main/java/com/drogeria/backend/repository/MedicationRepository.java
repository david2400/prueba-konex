package com.drogeria.backend.repository;

import com.drogeria.backend.entity.Medications;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface MedicationRepository extends JpaRepository<Medications, Long> {

    Optional<Medications> findByNameAndLaboratory(String name, String laboratory);


}
