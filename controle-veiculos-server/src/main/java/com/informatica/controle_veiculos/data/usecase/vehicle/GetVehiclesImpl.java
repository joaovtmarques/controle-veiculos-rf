package com.informatica.controle_veiculos.data.usecase.vehicle;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.informatica.controle_veiculos.domain.model.Vehicle;
import com.informatica.controle_veiculos.domain.usecase.vehicle.GetVehiclesUseCase;
import com.informatica.controle_veiculos.infra.repository.VehicleRepository;

@Service
public class GetVehiclesImpl implements GetVehiclesUseCase {

  @Autowired
  private VehicleRepository vehicleRepository;

  @Override
  public List<Vehicle> execute(Pageable pageable) {
    return vehicleRepository.findAll(pageable).getContent();
  }

}
