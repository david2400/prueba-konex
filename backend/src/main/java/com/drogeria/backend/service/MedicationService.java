package com.drogeria.backend.service;

import com.drogeria.backend.dto.MedicationDTO;
import com.drogeria.backend.entity.Medications;

import java.io.IOException;
import java.util.List;


public interface MedicationService {

    MedicationDTO saveMedication(MedicationDTO medication)throws IOException;

    MedicationDTO getMedication(Long id)throws IOException;

    MedicationDTO getMedication(String name,String laboratory)throws IOException;

    List<MedicationDTO> getMedications();

    Medications deleteMedication(Long id)throws IOException;

    MedicationDTO updateMedicamento(MedicationDTO medicamentoDTO) throws IOException;



}
