package com.informatica.controle_veiculos.data.usecase.vehicle;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.informatica.controle_veiculos.data.dto.vehicle.CreateVehicleRequestDTO;
import com.informatica.controle_veiculos.data.exception.AlreadyExistsException;
import com.informatica.controle_veiculos.data.exception.NotFoundException;
import com.informatica.controle_veiculos.domain.model.User;
import com.informatica.controle_veiculos.domain.model.Vehicle;
import com.informatica.controle_veiculos.domain.usecase.vehicle.CreateVehicleUseCase;
import com.informatica.controle_veiculos.infra.repository.UserRepository;
import com.informatica.controle_veiculos.infra.repository.VehicleRepository;

@Service
public class CreateVehicleImpl implements CreateVehicleUseCase {

  @Autowired
  private VehicleRepository vehicleRepository;

  @Autowired
  private UserRepository userRepository;

  @Transactional
  @Override
  public Vehicle execute(CreateVehicleRequestDTO vehicleRequestDTO) {
    Optional<User> userExists = userRepository.findById(vehicleRequestDTO.userId());

    if (userExists.isEmpty()) {
      throw new NotFoundException("O dono do veículo informado não existe.");
    }

    if (vehicleRepository.findByPlate(vehicleRequestDTO.plate()).isPresent()) {
      throw new AlreadyExistsException("A placa informada já possui um veículo cadastrado.");
    }

    return vehicleRepository.save(vehicleRequestDTO.toModel(userExists.get()));
  }

}
