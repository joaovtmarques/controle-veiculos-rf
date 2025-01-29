package com.informatica.controle_veiculos.domain.usecase.user;

import java.util.List;

import com.informatica.controle_veiculos.domain.model.Vehicle;

public interface GetUserVehiclesUseCase {

  List<Vehicle> execute(Long userId);

}
