package com.drogeria.backend.serviceImp;

import com.drogeria.backend.dto.SaleDTO;
import com.drogeria.backend.entity.Medications;
import com.drogeria.backend.entity.Sale;
import com.drogeria.backend.exceptions.medicamento.MedicamentoNotFoundNameAndLaboratoryException;
import com.drogeria.backend.mapper.VentaMapper;
import com.drogeria.backend.repository.MedicamentoRepository;
import com.drogeria.backend.repository.SaleRepository;
import com.drogeria.backend.service.SaleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@Service
public class SaleServiceImpl implements SaleService {

    @Autowired
    private SaleRepository saleRepository;

    @Autowired
    private MedicamentoRepository medicamentoRepository;

    public SaleDTO saveVenta(SaleDTO ventaDTO) throws IOException {
        Optional<Medications> medicamento=medicamentoRepository.findByNameAndLaboratory(ventaDTO.getMedication().getName(), ventaDTO.getMedication().getLaboratory());
        if(!medicamento.isPresent()){
            throw new MedicamentoNotFoundNameAndLaboratoryException(ventaDTO.getMedication().getName(), ventaDTO.getMedication().getLaboratory());}


        medicamento.get().setStock(medicamento.get().getStock()-ventaDTO.getQuantity());
        medicamentoRepository.save(medicamento.get());

        Sale sale=VentaMapper.INSTANCE.DtoToEntity(ventaDTO);
        sale.setMedications(medicamento.get());
        saleRepository.save(sale);

        return ventaDTO;
    }


    public List<SaleDTO> getVentas(){
        return  VentaMapper.INSTANCE.mapToDto(saleRepository.findAll());
    }
}
