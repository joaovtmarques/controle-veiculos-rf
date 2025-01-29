package com.informatica.controle_veiculos.domain.usecase.vehicle;

import java.util.List;

import org.springframework.data.domain.Pageable;

import com.informatica.controle_veiculos.domain.model.Vehicle;

public interface GetVehiclesUseCase {

  List<Vehicle> execute(Pageable pageable);

}
