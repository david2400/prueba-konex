package com.drogeria.backend.serviceImp;

import com.drogeria.backend.dto.MedicationDTO;
import com.drogeria.backend.entity.Medications;
import com.drogeria.backend.exceptions.global.GlobalDataRequiredException;
import com.drogeria.backend.exceptions.medicamento.MedicamentoNotFoundNameAndLaboratoryException;
import com.drogeria.backend.exceptions.medicamento.MedicamentoNotFoundException;
import com.drogeria.backend.exceptions.medicamento.MedicamentoRepeatException;
import com.drogeria.backend.mapper.MedicamentoMapper;
import com.drogeria.backend.repository.MedicationRepository;
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
    private MedicationRepository medicamentoRepository;

    @Override
    @Transactional
    public MedicationDTO saveMedication(MedicationDTO medication) throws IOException {
        if(Objects.isNull(medication)){
            throw  new GlobalDataRequiredException();
        }
        Optional<Medications> medicamento =medicamentoRepository.findByNameAndLaboratory(medication.getName(),medication.getLaboratory());
        if(medicamento.isPresent()){
            throw  new MedicamentoRepeatException(medication.getName(),medication.getLaboratory());
        }

        medication.setState(1);
        return MedicamentoMapper.INSTANCE.mapToDto(medicamentoRepository.
                save(MedicamentoMapper.INSTANCE.DtoToEntity(medication)));
    }

    @Override
    public MedicationDTO getMedication(Long id)throws IOException{
        Optional<Medications> medicamento =medicamentoRepository.findById(id);
        if(!medicamento.isPresent()){
            throw  new MedicamentoNotFoundException(id);
        }
        return MedicamentoMapper.INSTANCE.mapToDto(medicamento.get());
    }

    public MedicationDTO getMedication(String nombre,String laboratory)throws IOException{
        Optional<Medications> medicamento =medicamentoRepository.findByNameAndLaboratory(nombre,laboratory);
        if(!medicamento.isPresent()){
            throw  new MedicamentoNotFoundNameAndLaboratoryException(nombre,laboratory);
        }
        return MedicamentoMapper.INSTANCE.mapToDto(medicamento.get());
    }

    @Override
    public List<MedicationDTO> getMedications(){
        return MedicamentoMapper.INSTANCE.mapToDto(medicamentoRepository.findAll());
    }

    @Override
    @Transactional
    public Medications deleteMedication(Long id) {
        Medications medicamento=medicamentoRepository.findById(id).orElseThrow(()-> new MedicamentoNotFoundException(id));

        medicamento.setState(medicamento.getState()==0?1:0);
        medicamentoRepository.save(medicamento);
        return medicamento;
    }

    @Override
    @Transactional
    public MedicationDTO updateMedicamento(MedicationDTO medicamentoDTO) throws IOException {
        Optional<Medications> medicamentoExistente = medicamentoRepository.findById(medicamentoDTO.getId());

        if (!medicamentoExistente.isPresent()) {
            throw new MedicamentoNotFoundNameAndLaboratoryException(medicamentoDTO.getName(),medicamentoDTO.getLaboratory());
        }

        Medications medicamentoActualizado= MedicamentoMapper.INSTANCE.DtoToEntity(medicamentoDTO);
        medicamentoActualizado.setId(medicamentoExistente.get().getId());
        medicamentoActualizado.setState(medicamentoExistente.get().getState());

        return MedicamentoMapper.INSTANCE.mapToDto(medicamentoRepository.save(medicamentoActualizado));
    }
}
