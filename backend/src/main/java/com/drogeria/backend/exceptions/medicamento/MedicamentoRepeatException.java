package com.drogeria.backend.exceptions.medicamento;

public class MedicamentoRepeatException extends RuntimeException {
    public MedicamentoRepeatException(String name, String laboratory){
        super(String.format("Ya se encuentra registrado un medicamento con el nombre: %s y laboratory: %s.", name,laboratory));
    }

}
