package com.informatica.controle_veiculos.data.dto.stamp;

import java.time.LocalDate;

import com.informatica.controle_veiculos.domain.model.Stamp;
import com.informatica.controle_veiculos.domain.model.User;
import com.informatica.controle_veiculos.domain.model.Vehicle;

import jakarta.validation.constraints.NotNull;

public record CreateStampRequestDTO(
    @NotNull(message = "O veículo deve ser informado.") Long vehicleId,
    @NotNull(message = "O dono do selo deve ser informado.") Long userId) {

  public Stamp toModel(Vehicle vehicle, User user, Long number, LocalDate expiration, String status) {
    Stamp stamp = new Stamp();
    stamp.setNumber(number);
    stamp.setVehicle(vehicle);
    stamp.setUser(user);
    stamp.setStatus(status);
    stamp.setExpiration(expiration);
    return stamp;
  }

}
