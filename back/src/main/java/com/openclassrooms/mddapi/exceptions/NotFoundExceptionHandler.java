package com.openclassrooms.mddapi.exceptions;

import com.openclassrooms.mddapi.responses.MyResponseExceptionObject;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class NotFoundExceptionHandler {

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<?> handleValidationExceptions(NotFoundException ex) {
        MyResponseExceptionObject myResponseExceptionObject = new MyResponseExceptionObject(ex.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(myResponseExceptionObject);
    }
}
