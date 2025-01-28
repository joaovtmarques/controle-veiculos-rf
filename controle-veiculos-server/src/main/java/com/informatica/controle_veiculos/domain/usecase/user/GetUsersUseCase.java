package com.informatica.controle_veiculos.domain.usecase.user;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.informatica.controle_veiculos.domain.model.User;

public interface GetUsersUseCase {

  Page<User> execute(Pageable pageable);

}
