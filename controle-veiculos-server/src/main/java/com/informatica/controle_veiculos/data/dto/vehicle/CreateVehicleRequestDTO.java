package com.informatica.controle_veiculos.data.dto.vehicle;

import com.informatica.controle_veiculos.domain.model.User;
import com.informatica.controle_veiculos.domain.model.Vehicle;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record CreateVehicleRequestDTO(
    @NotNull(message = "O dono do veículo deve ser informado.") Long userId,
    @NotBlank(message = "O modelo do veículo deve ser informado.") String model,
    @NotBlank(message = "A placa do veículo deve ser informada.") String plate,
    @NotBlank(message = "A cor do veículo deve ser informada.") String color,
    @NotBlank(message = "O tipo do veículo deve ser informado.") String type,
    @NotNull(message = "A data do licenciamento do veículo deve ser informada.") Integer licensing) {

  public Vehicle toModel(User user) {
    Vehicle vehicle = new Vehicle();
    vehicle.setModel(model);
    vehicle.setPlate(plate);
    vehicle.setColor(color);
    vehicle.setType(type);
    vehicle.setLicensing(licensing);
    vehicle.setUser(user);
    return vehicle;
  }
}
