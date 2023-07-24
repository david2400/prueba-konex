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
@RequestMapping("/api/sales")
@Validated
public class SaleController {

    @Autowired
    private SaleService salesService;

    @PostMapping("/register")
    @ResponseBody
    public ResponseEntity<SaleDTO> registerSale(@RequestBody SaleDTO saleDTO) throws IOException {
        return ResponseEntity.status(HttpStatus.CREATED).body(salesService.saveVenta(saleDTO));
    }

    @GetMapping("/")
    @ResponseBody
    public ResponseEntity<List<SaleDTO>> getSales() {
        return ResponseEntity.status(HttpStatus.OK).body( salesService.getVentas());
    }
}
