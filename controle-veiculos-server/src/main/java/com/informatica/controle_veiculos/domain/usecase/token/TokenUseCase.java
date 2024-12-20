package com.informatica.controle_veiculos.domain.usecase.token;

import com.informatica.controle_veiculos.data.dto.auth.AuthResponseDTO;
import com.informatica.controle_veiculos.domain.model.Admin;

public interface TokenUseCase {
  AuthResponseDTO generateToken(Admin admin);

  String validateToken(String token);

}
