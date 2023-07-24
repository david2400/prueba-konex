package com.drogeria.backend.controller;

import com.drogeria.backend.dto.MedicationDTO;
import com.drogeria.backend.entity.Medications;
import com.drogeria.backend.service.MedicationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.io.IOException;
import java.util.List;

@RestController
@CrossOrigin(origins = "*", methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.DELETE, RequestMethod.PUT})
@RequestMapping("api/medication")
public class MedicationsController {

    @Autowired
    private MedicationService medicamentoService;

    @PostMapping("register")
    @ResponseBody
    public ResponseEntity<MedicationDTO> register(@RequestBody MedicationDTO medicamentoDTO) throws IOException {
        return ResponseEntity.status(HttpStatus.CREATED).body(medicamentoService.saveMedication(medicamentoDTO));
    }

    @GetMapping("/")
    @ResponseBody
    public ResponseEntity<List<MedicationDTO>> getAllMedicamentos(){
        return ResponseEntity.status(HttpStatus.OK).body( medicamentoService.getMedications());
    }

    @GetMapping("/{id}")
    @ResponseBody
    public ResponseEntity<MedicationDTO> getMedicamentoById(@PathVariable("id") Long id) throws IOException {
        return ResponseEntity.status(HttpStatus.OK).body(medicamentoService.getMedication(id));

    }

    @GetMapping("/{nombre}/{laboratorio}")
    @ResponseBody
    public ResponseEntity<MedicationDTO> getMedicationByNameAndLaboratory(@PathVariable("nombre") String nombre,@PathVariable("laboratorio") String laboratorio) throws IOException {
        return ResponseEntity.status(HttpStatus.OK).body(medicamentoService.getMedication(nombre,laboratorio));
    }

    @PutMapping("update")
    @ResponseBody
    public ResponseEntity<MedicationDTO> update( @RequestBody MedicationDTO medicamentoDto) throws IOException {
        return ResponseEntity.status(HttpStatus.OK).body(medicamentoService.updateMedicamento(medicamentoDto));
    }

    @DeleteMapping("delete/{id}")
    @ResponseBody
    public ResponseEntity<Medications> delete(@PathVariable("id") Long id ) throws IOException {
        return ResponseEntity.status(HttpStatus.OK).body(medicamentoService.deleteMedication(id));
    }
}
