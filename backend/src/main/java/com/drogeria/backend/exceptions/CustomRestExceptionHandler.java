package com.drogeria.backend.exceptions;

import com.drogeria.backend.exceptions.global.GlobalDataRequiredException;
import com.drogeria.backend.exceptions.medicamento.MedicamentoNotFoundException;
import com.drogeria.backend.exceptions.medicamento.MedicamentoNotFoundNameAndLaboratoryException;
import org.hibernate.exception.ConstraintViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.concurrent.ConcurrentHashMap;

@ControllerAdvice
public class CustomRestExceptionHandler extends ResponseEntityExceptionHandler {
    private static final String ERROR_OCCURRED_CONTACT_ADMIN = "An error occurred, please contact administrator.";
    private static final ConcurrentHashMap<String, Integer> STATUS_CODE = new ConcurrentHashMap<>();

    public  CustomRestExceptionHandler() {
        STATUS_CODE.put(ApiRequestException.class.getSimpleName(), HttpStatus.BAD_REQUEST.value());
        STATUS_CODE.put(MedicamentoNotFoundException.class.getSimpleName(),HttpStatus.NOT_FOUND.value());
        STATUS_CODE.put(MedicamentoNotFoundNameAndLaboratoryException.class.getSimpleName(),HttpStatus.NOT_FOUND.value());
        STATUS_CODE.put(GlobalDataRequiredException.class.getSimpleName(),HttpStatus.CONFLICT.value());
    }

    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<ApiError> handleConstraintViolationException(ConstraintViolationException exception) {
        String exceptionName = exception.getClass().getSimpleName();
        String message= exception.getMessage();
        ApiError error = new ApiError(exceptionName,message);
        return new ResponseEntity<>(error,HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ApiError> handleApiRequestException(Exception e) {

        ResponseEntity<ApiError> result;
        String exceptionName = e.getClass().getSimpleName();
        String message = e.getMessage();
        Integer code = STATUS_CODE.get(exceptionName);
        //e.printStackTrace();
        if(code !=null){
            ApiError error = new ApiError(exceptionName,message);
            result = new ResponseEntity<>(error, HttpStatus.valueOf(code));
            e.printStackTrace();
        }else{
            ApiError error = new ApiError(exceptionName,ERROR_OCCURRED_CONTACT_ADMIN);
            result = new ResponseEntity<>(error,HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return result;
    }
}
