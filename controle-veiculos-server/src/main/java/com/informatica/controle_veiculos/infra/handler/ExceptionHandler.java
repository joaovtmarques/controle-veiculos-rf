package com.informatica.controle_veiculos.infra.handler;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.informatica.controle_veiculos.data.exception.BadRequestException;
import com.informatica.controle_veiculos.data.exception.AlreadyExistsException;
import com.informatica.controle_veiculos.data.exception.NotFoundException;
import com.informatica.controle_veiculos.data.exception.UnauthorizedException;

@ControllerAdvice
public class ExceptionHandler extends ResponseEntityExceptionHandler {

  @org.springframework.web.bind.annotation.ExceptionHandler(AlreadyExistsException.class)
  private ResponseEntity<String> alreadyExists(AlreadyExistsException exception) {
    return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body(exception.getMessage());
  }

  @org.springframework.web.bind.annotation.ExceptionHandler(BadRequestException.class)
  private ResponseEntity<String> badRequest(BadRequestException exception) {
    return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(exception.getMessage());
  }

  @org.springframework.web.bind.annotation.ExceptionHandler(NotFoundException.class)
  private ResponseEntity<String> notFound(NotFoundException exception) {
    return ResponseEntity.status(HttpStatus.NOT_FOUND).body(exception.getMessage());
  }

  @org.springframework.web.bind.annotation.ExceptionHandler(UnauthorizedException.class)
  private ResponseEntity<String> unauthorized(UnauthorizedException exception) {
    return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(exception.getMessage());
  }

}