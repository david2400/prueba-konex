package com.drogeria.backend.data;

import com.drogeria.backend.entity.Medications;

import java.time.LocalDateTime;

public class MedicationData {
    public static  final Medications medicamentoA = Medications.builder().id(1l).name("acetaminofen").laboratory("MK")
            .dateExpiration(LocalDateTime.of(2023,05,26,10,30,0))
            .dateProduction(LocalDateTime.of(2023,05,26,10,30,0))
            .stock(10).unitvalue(10000.0).state(1).build();
    public static  final Medications medicamentoB = Medications.builder().id(2l).name("ibuprofeno").laboratory("Npi")
            .dateExpiration(LocalDateTime.of(2023,05,26,10,30,0))
            .dateProduction(LocalDateTime.of(2023,05,26,10,30,0))
            .stock(10).unitvalue(10000.0).state(1).build();
    public static  final Medications medicamentoC = Medications.builder().id(3l).name("loracepan").laboratory("Lab")
            .dateExpiration(LocalDateTime.of(2023,05,26,10,30,0))
            .dateProduction(LocalDateTime.of(2023,05,26,10,30,0))
            .stock(10).unitvalue(10000.0).state(1).build();
    public static  final Medications medicamentoD = Medications.builder().id(4l).name("dolex").laboratory("Medimax")
            .dateExpiration(LocalDateTime.of(2023,05,26,10,30,0))
            .dateProduction(LocalDateTime.of(2023,05,26,10,30,0))
            .stock(10).unitvalue(10000.0).state(1).build();

}
