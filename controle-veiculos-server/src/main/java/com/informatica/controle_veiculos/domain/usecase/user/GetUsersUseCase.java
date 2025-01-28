package com.informatica.controle_veiculos.domain.usecase.user;

import java.util.List;

import org.springframework.data.domain.Pageable;

import com.informatica.controle_veiculos.domain.model.User;

public interface GetUsersUseCase {

  List<User> execute(Pageable pageable);

}
