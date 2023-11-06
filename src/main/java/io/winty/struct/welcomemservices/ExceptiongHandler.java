package io.winty.struct.welcomemservices;

import java.util.NoSuchElementException;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ExceptiongHandler {
    
    @ExceptionHandler(NoSuchElementException.class)
    public ResponseEntity<String> handleNotFound(){
        return ResponseEntity.notFound().build();
    }
    
    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<ErrorResponse> handleDataIntegrity(DataIntegrityViolationException exception){
        return ResponseEntity.badRequest().body(new ErrorResponse(exception.getMessage()));
    }
    
    public record ErrorResponse(String message){}
}
