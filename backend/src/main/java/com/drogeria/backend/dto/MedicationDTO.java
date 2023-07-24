package com.drogeria.backend.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.*;

import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;
import java.util.Objects;


@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class MedicationDTO {

    private Long id;

    @NotBlank(message = "Nombre cannot be null")
    @Pattern(regexp = "^[a-zA-Z\\s-]*$", message = "Nombre cannot be null")
    private String name;

    @NotBlank(message = "Laboratorio cannot be null")
    private String laboratory;

    @NotBlank(message = "Fecha Fabricacion cannot be null")
    @DateTimeFormat(pattern ="yyyy-MM-dd'T'HH:mm:ss.SSSZ")
    private LocalDateTime dateProduction;

    @NotBlank(message = "Fecha Vencimiento cannot be null")
    @DateTimeFormat(pattern ="yyyy-MM-dd'T'HH:mm:ss.SSSZ")
    private LocalDateTime dateExpiration;

    @Min(value = 1, message = "La cantidad mínima permitida es 1")
    private Integer stock;

    @PositiveOrZero(message = "El valor debe ser positivo o cero")
    private Double unitvalue;

    private Integer state;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MedicationDTO that = (MedicationDTO) o;
        return name.equals(that.name) && laboratory.equals(that.laboratory) && dateProduction.equals(that.dateProduction) && dateExpiration.equals(that.dateExpiration) && stock.equals(that.stock) && unitvalue.equals(that.unitvalue) && state.equals(that.state);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, laboratory, dateProduction,dateExpiration,stock,unitvalue, state);
    }
}