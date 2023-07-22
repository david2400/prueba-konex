package com.drogeria.backend.mapper;

import com.drogeria.backend.dto.SaleDTO;
import com.drogeria.backend.entity.Sale;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface VentaMapper {

    VentaMapper INSTANCE= Mappers.getMapper(VentaMapper.class);

    Sale DtoToEntity(SaleDTO ventaDTO);

    SaleDTO mapToDto(Sale venta);

    List<SaleDTO> mapToDto(List<Sale> ventas);
}
