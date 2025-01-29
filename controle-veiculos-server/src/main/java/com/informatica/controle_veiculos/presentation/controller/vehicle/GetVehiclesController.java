package com.informatica.controle_veiculos.presentation.controller.vehicle;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.informatica.controle_veiculos.data.dto.vehicle.GetVehiclesRequestDTO;
import com.informatica.controle_veiculos.domain.model.Vehicle;
import com.informatica.controle_veiculos.domain.usecase.vehicle.GetVehiclesUseCase;
import com.informatica.controle_veiculos.presentation.protocol.ControllerProtocol;

import jakarta.validation.Valid;

@CrossOrigin("*")
@RestController
@RequestMapping("/api/v1/vehicles")
public class GetVehiclesController implements ControllerProtocol<GetVehiclesRequestDTO, ResponseEntity<List<Vehicle>>> {

  @Autowired
  private GetVehiclesUseCase getVehicles;

  @Override
  @GetMapping
  public ResponseEntity<List<Vehicle>> handle(@RequestBody @Valid GetVehiclesRequestDTO getVehiclesDTO) {
    Pageable pageable = PageRequest.of(getVehiclesDTO.page(), getVehiclesDTO.size());
    return ResponseEntity.ok(getVehicles.execute(pageable));
  }

}
