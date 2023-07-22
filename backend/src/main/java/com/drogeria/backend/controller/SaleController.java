package com.drogeria.backend.controller;

import com.drogeria.backend.dto.SaleDTO;
import com.drogeria.backend.service.SaleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@RestController
@CrossOrigin(origins = "*", methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.DELETE, RequestMethod.PUT})
@RequestMapping("/api/venta")
@Validated
public class SaleController {

    @Autowired
    private SaleService ventaService;

    @GetMapping("/")
    @ResponseBody
    public ResponseEntity<List<SaleDTO>> getVentas() {
        return ResponseEntity.status(HttpStatus.OK).body( ventaService.getVentas());
    }

    @PostMapping("/create")
    @ResponseBody
    public ResponseEntity<SaleDTO> createVenta(@RequestBody SaleDTO saleDTO) throws IOException {
        return ResponseEntity.status(HttpStatus.CREATED).body(ventaService.saveVenta(saleDTO));
    }
}
