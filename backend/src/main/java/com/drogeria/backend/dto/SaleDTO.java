package com.drogeria.backend.dto;

import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.math.BigDecimal;
import java.time.LocalDateTime;


@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class SaleDTO {

    @DateTimeFormat(pattern ="yyyy-MM-dd'T'HH:mm:ss.SSSZ")
    private LocalDateTime date;

    private MedicationDTO medication;

    private Integer quantity;

    private BigDecimal unitValue;

    private BigDecimal total;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SaleDTO ventaDTO = (SaleDTO) o;
        return date.equals(ventaDTO.date) && medication.equals(ventaDTO.medication) && quantity.equals(ventaDTO.quantity) && unitValue.equals(ventaDTO.unitValue) && total.equals(ventaDTO.total);
    }
}
