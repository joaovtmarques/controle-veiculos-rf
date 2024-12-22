package com.informatica.controle_veiculos.presentation.protocol;

public interface ControllerProtocol<B, R> {

  R handle(B b);

}
