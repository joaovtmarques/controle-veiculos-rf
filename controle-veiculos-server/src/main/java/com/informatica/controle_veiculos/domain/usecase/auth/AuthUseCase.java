package com.informatica.controle_veiculos.domain.usecase.auth;

import com.informatica.controle_veiculos.data.dto.auth.AuthRequestDTO;
import com.informatica.controle_veiculos.data.dto.auth.AuthResponseDTO;

public interface AuthUseCase {

  AuthResponseDTO execute(AuthRequestDTO authRequestDTO);

}
