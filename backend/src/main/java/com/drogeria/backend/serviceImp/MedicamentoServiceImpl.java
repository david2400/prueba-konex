package com.drogeria.backend.serviceImp;

import com.drogeria.backend.dto.MedicamentoDTO;
import com.drogeria.backend.entity.Medications;
import com.drogeria.backend.exceptions.global.GlobalDataRequiredException;
import com.drogeria.backend.exceptions.medicamento.MedicamentoNotFoundNameAndLaboratoryException;
import com.drogeria.backend.exceptions.medicamento.MedicamentoNotFoundException;
import com.drogeria.backend.mapper.MedicamentoMapper;
import com.drogeria.backend.repository.MedicamentoRepository;
import com.drogeria.backend.service.MedicationService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.io.IOException;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
@AllArgsConstructor
public class MedicamentoServiceImpl implements MedicationService {

    @Autowired
    private MedicamentoRepository medicamentoRepository;

    @Override
    @Transactional
    public MedicamentoDTO saveMedicamento(MedicamentoDTO medicamentoDTO) throws IOException {
        if(Objects.isNull(medicamentoDTO)){
            throw  new GlobalDataRequiredException();
        }
        return MedicamentoMapper.INSTANCE.mapToDto(medicamentoRepository.
                save(MedicamentoMapper.INSTANCE.DtoToEntity(medicamentoDTO)));
    }

    @Override
    public MedicamentoDTO getMedicamento(Long id)throws IOException{
        Optional<Medications> medicamento =medicamentoRepository.findById(id);
        if(!medicamento.isPresent()){
            throw  new MedicamentoNotFoundException(id);
        }
        return MedicamentoMapper.INSTANCE.mapToDto(medicamento.get());
    }

    public MedicamentoDTO getMedicamento(String nombre,String laboratory)throws IOException{
        Optional<Medications> medicamento =medicamentoRepository.findByNameAndLaboratory(nombre,laboratory);
        if(!medicamento.isPresent()){
            throw  new MedicamentoNotFoundNameAndLaboratoryException(nombre,laboratory);
        }
        return MedicamentoMapper.INSTANCE.mapToDto(medicamento.get());
    }

    @Override
    public List<MedicamentoDTO> getMedicamentos(){
        return   MedicamentoMapper.INSTANCE.mapToDto(medicamentoRepository.findAll());
    }

    @Override
    @Transactional
    public String deleteMedicamento(String nombre,String laboratory) {
        Optional<Medications> medicamento=medicamentoRepository.findByNameAndLaboratory(nombre,laboratory);
        if(!medicamento.isPresent()){
            throw new MedicamentoNotFoundNameAndLaboratoryException(nombre,laboratory);
        }
        medicamento.get().setState(medicamento.get().getState()==0?1:0);
        medicamentoRepository.save(medicamento.get());
        return "Medicamento eliminado";
    }

    @Override
    @Transactional
    public MedicamentoDTO updateMedicamento(MedicamentoDTO medicamentoDTO) throws IOException {
        Optional<Medications> medicamentoExistente = medicamentoRepository.findByNameAndLaboratory(medicamentoDTO.getName(),medicamentoDTO.getLaboratory());

        if (!medicamentoExistente.isPresent()) {
            throw new MedicamentoNotFoundNameAndLaboratoryException(medicamentoDTO.getName(),medicamentoDTO.getLaboratory());
        }

        //LOMBOK NO FUNCIONO Y EL BUILDER PAILAS
        Medications medicamentoActualizado= MedicamentoMapper.INSTANCE.DtoToEntity(medicamentoDTO);
        medicamentoActualizado.setId(medicamentoExistente.get().getId());

        return MedicamentoMapper.INSTANCE.mapToDto(medicamentoRepository.save(medicamentoActualizado));
    }
}
