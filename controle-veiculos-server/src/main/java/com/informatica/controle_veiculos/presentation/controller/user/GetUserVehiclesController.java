package com.informatica.controle_veiculos.presentation.controller.user;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.informatica.controle_veiculos.domain.model.Vehicle;
import com.informatica.controle_veiculos.domain.usecase.user.GetUserVehiclesUseCase;
import com.informatica.controle_veiculos.presentation.protocol.ControllerProtocol;

@CrossOrigin("*")
@RestController
@RequestMapping("/api/v1/users/vehicles")
public class GetUserVehiclesController implements ControllerProtocol<Long, ResponseEntity<List<Vehicle>>> {

  @Autowired
  private GetUserVehiclesUseCase getVehicles;

  @Override
  @GetMapping("/{userId}")
  public ResponseEntity<List<Vehicle>> handle(@PathVariable Long userId) {
    return ResponseEntity.ok(getVehicles.execute(userId));
  }

}
