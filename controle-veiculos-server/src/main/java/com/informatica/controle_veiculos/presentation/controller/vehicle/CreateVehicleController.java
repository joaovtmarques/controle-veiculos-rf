package com.informatica.controle_veiculos.presentation.controller.vehicle;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.informatica.controle_veiculos.data.dto.vehicle.CreateVehicleRequestDTO;
import com.informatica.controle_veiculos.domain.model.Vehicle;
import com.informatica.controle_veiculos.domain.usecase.vehicle.CreateVehicleUseCase;
import com.informatica.controle_veiculos.presentation.protocol.ControllerProtocol;

import jakarta.validation.Valid;

@CrossOrigin("*")
@RestController
@RequestMapping("/api/v1/vehicles")
public class CreateVehicleController implements ControllerProtocol<CreateVehicleRequestDTO, ResponseEntity<Vehicle>> {

  @Autowired
  private CreateVehicleUseCase createVehicle;

  @Override
  @PostMapping
  public ResponseEntity<Vehicle> handle(@RequestBody @Valid CreateVehicleRequestDTO vehicleRequestDTO) {
    return ResponseEntity.status(201).body(createVehicle.execute(vehicleRequestDTO));
  }
}