package com.informatica.controle_veiculos.data.exception;

public class AlreadyExistsException extends RuntimeException {

  public AlreadyExistsException() {
    super("Um veículo com está placa já está cadastrado");
  }

  public AlreadyExistsException(String message) {
    super(message);
  }

}
