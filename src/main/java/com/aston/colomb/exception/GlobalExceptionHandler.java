package com.aston.colomb.exception;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.LocalDateTime;

@RestController
@ControllerAdvice // Apply to all controllers
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    // Globals exception handler for all errors
    @ExceptionHandler(Exception.class)
    public final ResponseEntity<Object> handleAllExceptions(Exception ex, WebRequest request) throws Exception {
        ExceptionResponse exceptionResponse = new ExceptionResponse(LocalDateTime.now(), ex.getMessage(), request.getDescription(false));
        return new ResponseEntity(exceptionResponse, HttpStatus.INTERNAL_SERVER_ERROR); // =========>  500 <=========
    }

    // UserNotFoundException
    @ExceptionHandler(UserNotFoundException.class)
    public final ResponseEntity<Object> handleUserNotFoundException(Exception ex, WebRequest request) throws Exception {
        ExceptionResponse exceptionResponse = new ExceptionResponse(LocalDateTime.now(), ex.getMessage(), request.getDescription(false));
        return new ResponseEntity(exceptionResponse, HttpStatus.NOT_FOUND);   // =========> 404 <=========
    }

    // EvenementNotFoundException
    @ExceptionHandler(EvenementNotFoundException.class)
    public final ResponseEntity<Object> handleEvenementNotFoundException(Exception ex, WebRequest request) throws Exception {
        ExceptionResponse exceptionResponse = new ExceptionResponse(LocalDateTime.now(), ex.getMessage(), request.getDescription(false));
        return new ResponseEntity(exceptionResponse, HttpStatus.NOT_FOUND);   // =========> 404 <=========
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(
            MethodArgumentNotValidException ex,
            HttpHeaders headers,
            HttpStatus status,
            WebRequest request
    ) {
        System.out.println("EROARE");
        System.out.println(ex.getBindingResult().toString());

        ExceptionResponse exceptionResponse = new ExceptionResponse(
                LocalDateTime.now(),
                "Les données envoyées sont invalides.",
//                ex.getBindingResult().toString() // all validation errors
                ex.getBindingResult().getFieldError().toString() // validation text
        );
        return new ResponseEntity(exceptionResponse, HttpStatus.BAD_REQUEST); // =========> 400 <=========
    }
}
