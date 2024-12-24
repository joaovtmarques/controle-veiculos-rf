package com.informatica.controle_veiculos.infra.handler;

import java.util.ArrayList;
import java.util.List;

import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
@Order(1)
public class RestControllerExceptionHandler {

  @ExceptionHandler(MethodArgumentNotValidException.class)
  public ResponseEntity<String> handleValidationExceptions(MethodArgumentNotValidException ex) {
    List<String> errorMessages = new ArrayList<>();

    ex.getBindingResult().getFieldErrors().forEach(error -> {
      String errorMessage = error.getDefaultMessage();
      errorMessages.add(errorMessage);
    });

    String errorToResponse = String.join(", ", errorMessages);

    return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorToResponse);
  }

}
