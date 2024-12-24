package com.informatica.controle_veiculos.domain.usecase.user;

import com.informatica.controle_veiculos.data.dto.user.CreateUserRequestDTO;
import com.informatica.controle_veiculos.domain.model.User;

public interface CreateUserUseCase {

  User execute(CreateUserRequestDTO userDTO);

}
