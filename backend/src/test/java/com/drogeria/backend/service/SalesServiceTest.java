package com.drogeria.backend.service;

import com.drogeria.backend.data.MedicationData;
import com.drogeria.backend.dto.MedicationDTO;
import com.drogeria.backend.dto.SaleDTO;
import com.drogeria.backend.entity.Medications;
import com.drogeria.backend.entity.Sale;
import com.drogeria.backend.exceptions.medicamento.MedicamentoNotFoundNameAndLaboratoryException;
import com.drogeria.backend.mapper.MedicamentoMapper;
import com.drogeria.backend.mapper.VentaMapper;
import com.drogeria.backend.repository.MedicationRepository;
import com.drogeria.backend.repository.SaleRepository;
import com.drogeria.backend.serviceImp.SaleServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.io.IOException;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Optional;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

public class SalesServiceTest {

    @Mock
    private SaleRepository ventaRepository;

    @Mock
    private MedicationRepository medicamentoRepository;

    @InjectMocks
    private SaleServiceImpl ventaService;

    private Medications medicamento;
    private MedicationDTO medicamentoDTO;
    private Sale venta, ventaTwo;
    private SaleDTO ventaDTO,ventaDTOTwo;


    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        medicamento= MedicationData.medicamentoA;
        medicamentoDTO= MedicamentoMapper.INSTANCE.mapToDto(medicamento);

        venta= Sale.builder().id(10L).createdate(LocalDateTime.of(2023,05,26,10,30,0))
                .quantity(10).unitvalue(new BigDecimal(10)).medications(medicamento)
                .total(new BigDecimal(10)).build();

        ventaTwo= Sale.builder().id(10L).createdate(LocalDateTime.of(2023,05,26,10,30,0))
                .quantity(10).unitvalue(new BigDecimal(10)).medications(medicamento)
                .total(new BigDecimal(10)).build();

        ventaDTO= SaleDTO.builder().createdate(LocalDateTime.of(2023,05,26,10,30,0))
                .medications(medicamentoDTO).quantity(10).unitvalue(new BigDecimal(10))
                .total(new BigDecimal(20)).build();

        ventaDTOTwo= SaleDTO.builder().createdate(LocalDateTime.of(2023,05,26,10,30,0))
                .medications(medicamentoDTO).quantity(10).unitvalue(new BigDecimal(10))
                .total(new BigDecimal(20)).build();
    }

    @Test
    void saveVenta_succes() throws IOException {

        // Arrange
        when(medicamentoRepository.findByNameAndLaboratory(medicamento.getName(), medicamento.getLaboratory()))
                .thenReturn(Optional.of(medicamento));

        Sale venta= VentaMapper.INSTANCE.DtoToEntity(ventaDTO);
        when(ventaRepository.save(any())).thenReturn(venta);

        // Act
        SaleDTO result = ventaService.saveVenta(ventaDTO);

        // Assert
        assertNotNull(result);
        assertEquals(result,VentaMapper.INSTANCE.mapToDto(venta));
    }

    @Test
    void saveVenta_exception() throws IOException  {
        when(medicamentoRepository.findByNameAndLaboratory(anyString(), anyString())).thenReturn(Optional.empty());
        // Act & Assert
        assertThrows(MedicamentoNotFoundNameAndLaboratoryException.class, () -> ventaService.saveVenta(ventaDTO));
    }


    @Test
    void getVentas() {
        List<SaleDTO> ventasDTO= new ArrayList<>(Arrays.asList(ventaDTO,ventaDTOTwo));
        List<Sale> ventas= new ArrayList<>(Arrays.asList(venta,ventaTwo));
        when(ventaRepository.findAll()).thenReturn(ventas);
        assertEquals(2,ventaService.getVentas().size());
    }
}
