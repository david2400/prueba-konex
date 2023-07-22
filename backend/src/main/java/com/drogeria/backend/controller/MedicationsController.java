package com.drogeria.backend.controller;

import com.drogeria.backend.dto.MedicamentoDTO;
import com.drogeria.backend.service.MedicationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.io.IOException;
import java.util.List;

@RestController
@CrossOrigin(origins = "*", methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.DELETE, RequestMethod.PUT})
@RequestMapping("/api/medicamentos")
public class MedicationsController {

    @Autowired
    private MedicationService medicamentoService;

    @GetMapping("/")
    @ResponseBody
    public ResponseEntity<List<MedicamentoDTO>> getAllMedicamentos(){
        return ResponseEntity.status(HttpStatus.OK).body( medicamentoService.getMedicamentos());
    }

    @GetMapping("/{id}")
    @ResponseBody
    public ResponseEntity<MedicamentoDTO> getMedicamentoById(@PathVariable("id") Long id) throws IOException {
        return ResponseEntity.status(HttpStatus.OK).body(medicamentoService.getMedicamento(id));

    }

    @GetMapping("/{nombre}/{laboratorio}")
    @ResponseBody
    public ResponseEntity<MedicamentoDTO> getMedicamentoById(@PathVariable("nombre") String nombre,@PathVariable("laboratorio") String laboratorio) throws IOException {
        return ResponseEntity.status(HttpStatus.OK).body(medicamentoService.getMedicamento(nombre,laboratorio));
    }


    @PostMapping("/create")
    @ResponseBody
    public ResponseEntity<MedicamentoDTO> createMedicamento(@RequestBody MedicamentoDTO medicamentoDTO) throws IOException {
        return ResponseEntity.status(HttpStatus.CREATED).body(medicamentoService.saveMedicamento(medicamentoDTO));
    }


    @PutMapping("/update")
    @ResponseBody
    public ResponseEntity<MedicamentoDTO> updateMedicamento( @RequestBody MedicamentoDTO medicamentoDto) throws IOException {
        return ResponseEntity.status(HttpStatus.OK).body(medicamentoService.updateMedicamento(medicamentoDto));
    }

    @DeleteMapping("/{nombre}/{laboratorio}")
    @ResponseBody
    public ResponseEntity<String> deleteMedicamento(@PathVariable("nombre") String nombre,@PathVariable("laboratorio")String laboratorio ) throws IOException {
        return ResponseEntity.status(HttpStatus.OK).body(medicamentoService.deleteMedicamento(nombre,laboratorio));
    }
}
