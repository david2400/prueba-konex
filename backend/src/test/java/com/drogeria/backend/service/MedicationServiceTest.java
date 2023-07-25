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
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

public class MedicationServiceTest {
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

        venta= Sale.builder()
                .id(10L).createdate(LocalDateTime.of(2023,05,26,10,30,0))
                .total(new BigDecimal(10)).quantity(10).unitvalue(new BigDecimal(400))
                .medications(medicamento).build();

        ventaTwo=Sale.builder()
                .id(11L).createdate(LocalDateTime.of(2023,05,26,10,30,0))
                .total(new BigDecimal(10)).quantity(10).unitvalue(new BigDecimal(40))
                .medications(medicamento).build();



        ventaDTO= SaleDTO.builder().medications(medicamentoDTO).createdate(LocalDateTime.of(2023,05,26,10,30,0))
                .quantity(10).unitvalue(new BigDecimal(20)).total(new BigDecimal(200))
                .build();

        ventaDTOTwo= SaleDTO.builder().medications(medicamentoDTO).createdate(LocalDateTime.of(2023,05,26,10,30,0))
                .quantity(10).unitvalue(new BigDecimal(20)).total(new BigDecimal(200))
                .build();


    }

    @Test
    void saveVenta_succes() throws IOException {

        when(medicamentoRepository.findByNameAndLaboratory(medicamento.getName(), medicamento.getLaboratory()))
                .thenReturn(Optional.of(medicamento));

        Sale venta= VentaMapper.INSTANCE.DtoToEntity(ventaDTO);
        when(ventaRepository.save(any())).thenReturn(venta);

        SaleDTO result = ventaService.saveVenta(ventaDTO);

        assertNotNull(result);
        assertEquals(result, VentaMapper.INSTANCE.mapToDto(venta));
    }

    @Test
    void saveVenta_exception() throws IOException  {
        when(medicamentoRepository.findByNameAndLaboratory(anyString(), anyString())).thenReturn(Optional.empty());
        assertThrows(MedicamentoNotFoundNameAndLaboratoryException.class, () -> ventaService.saveVenta(ventaDTO));
    }

    @Test
    void getVentas() {
        List<SaleDTO> sale = new ArrayList<>(Arrays.asList(ventaDTO,ventaDTOTwo));
        List<Sale> ventas = new ArrayList<>(Arrays.asList(venta,ventaTwo));
        when(ventaRepository.findAll()).thenReturn(ventas);
        assertEquals(2,ventaService.getVentas().size());
    }
}
