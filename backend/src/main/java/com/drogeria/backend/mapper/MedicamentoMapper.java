package com.drogeria.backend.mapper;

import com.drogeria.backend.dto.MedicamentoDTO;
import com.drogeria.backend.entity.Medications;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface MedicamentoMapper {

    MedicamentoMapper INSTANCE= Mappers.getMapper(MedicamentoMapper.class);

    Medications DtoToEntity(MedicamentoDTO medicamentoDTO);

    MedicamentoDTO mapToDto(Medications medicamento);

    List<MedicamentoDTO> mapToDto(List<Medications> medicamentos);

}
