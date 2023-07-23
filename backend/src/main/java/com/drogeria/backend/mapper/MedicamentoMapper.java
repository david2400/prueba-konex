package com.drogeria.backend.mapper;

import com.drogeria.backend.dto.MedicationDTO;
import com.drogeria.backend.entity.Medications;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface MedicamentoMapper {

    MedicamentoMapper INSTANCE= Mappers.getMapper(MedicamentoMapper.class);

    Medications DtoToEntity(MedicationDTO medicamentoDTO);

    MedicationDTO mapToDto(Medications medicamento);

    List<MedicationDTO> mapToDto(List<Medications> medicamentos);

}
