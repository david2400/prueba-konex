package com.drogeria.backend.dto;

import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.math.BigDecimal;
import java.time.LocalDateTime;


@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class SaleDTO {

    @DateTimeFormat(pattern ="yyyy-MM-dd'T'HH:mm:ss.SSSZ")
    private LocalDateTime createdate;

    private MedicationDTO medication;

    private Integer quantity;

    private BigDecimal unitvalue;

    private BigDecimal total;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SaleDTO ventaDTO = (SaleDTO) o;
        return createdate.equals(ventaDTO.createdate) && medication.equals(ventaDTO.medication) && quantity.equals(ventaDTO.quantity) && unitvalue.equals(ventaDTO.unitvalue) && total.equals(ventaDTO.total);
    }
}
