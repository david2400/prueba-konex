package com.drogeria.backend.exceptions.venta;

public class VentaNotFoundException extends RuntimeException{

    public VentaNotFoundException(Long id){
        super(String.format("Venta with ID: %s not found.", id));
    }
}
