package com.drogeria.backend.exceptions.global;

public class GlobalDataRequiredException extends  RuntimeException {

    public GlobalDataRequiredException (){
        super(String.format("enter all the required data"));
    }
}
