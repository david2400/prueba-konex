package com.drogeria.backend.service;

import com.drogeria.backend.dto.MedicamentoDTO;

import java.io.IOException;
import java.util.List;


public interface MedicationService {

    MedicamentoDTO saveMedicamento(MedicamentoDTO medicamento)throws IOException;

    MedicamentoDTO getMedicamento(Long id)throws IOException;

    MedicamentoDTO getMedicamento(String name,String laboratory)throws IOException;

    List<MedicamentoDTO> getMedicamentos();

    String deleteMedicamento(String name,String laboratory)throws IOException;

    MedicamentoDTO updateMedicamento(MedicamentoDTO medicamentoDTO) throws IOException;



}
