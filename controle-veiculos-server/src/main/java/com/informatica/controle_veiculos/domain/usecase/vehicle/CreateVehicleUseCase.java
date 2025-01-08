package com.informatica.controle_veiculos.domain.usecase.vehicle;

import com.informatica.controle_veiculos.data.dto.vehicle.CreateVehicleRequestDTO;
import com.informatica.controle_veiculos.domain.model.Vehicle;

public interface CreateVehicleUseCase {

  Vehicle execute(CreateVehicleRequestDTO vehicleDTO);

}
