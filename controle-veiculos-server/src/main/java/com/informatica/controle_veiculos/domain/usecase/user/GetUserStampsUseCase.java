package com.informatica.controle_veiculos.domain.usecase.user;

import java.util.List;

import com.informatica.controle_veiculos.domain.model.Stamp;

public interface GetUserStampsUseCase {

  List<Stamp> execute(Long userId);

}
