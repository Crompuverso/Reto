package com.kruger.vaccination.Exception;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.kruger.vaccination.DTO.ErrorHandlerDTO;

@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(BadCredentialsException.class)
    public ResponseEntity<ErrorHandlerDTO> badCredentialsExceptionHandler(BadCredentialsExceptionHandler exception,
            WebRequest webRequest) {
        return new ResponseEntity<>(new ErrorHandlerDTO(webRequest.getDescription(false),
                "Credenciales erróneas", exception.getMessage()), HttpStatus.UNAUTHORIZED);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorHandlerDTO> exceptionHandler(Exception exception, WebRequest webRequest) {
        return new ResponseEntity<>(new ErrorHandlerDTO(webRequest.getDescription(false),
                "Error", exception.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
            HttpHeaders headers, HttpStatus status, WebRequest request) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach(error -> {
            String fieldString = ((FieldError) error).getField();
            String message = error.getDefaultMessage();
            errors.put(fieldString, message);
        });
        return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(NotFoundExceptionHandler.class)
    public ResponseEntity<ErrorHandlerDTO> notFoundExceptionHandler(NotFoundExceptionHandler notFoundException,
            WebRequest webRequest) {
        return new ResponseEntity<>(new ErrorHandlerDTO(webRequest.getDescription(false),
                "Recurso no encontrado", notFoundException.getMessage()), HttpStatus.NOT_FOUND);
    }
}