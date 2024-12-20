package com.informatica.controle_veiculos.data.exception;

public class BadRequestException extends RuntimeException {

  public BadRequestException() {
    super("Ocorreu um erro inesperado");
  }

  public BadRequestException(String message) {
    super(message);
  }

}
